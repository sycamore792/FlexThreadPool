package org.sycamore.dtp.rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.sycamore.dtp.rpc.serialization.JsonServerDecoder;
import org.sycamore.dtp.rpc.serialization.JsonServerEncoder;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

public class RpcServer implements SmartInitializingSingleton {
    // RPC服务实现容器
    private Map<String, Object> rpcServices = new HashMap<>();


    private int port;
    private ApplicationContext applicationContext;

    public RpcServer(int port,ApplicationContext applicationContext) {
        this.port = port;
        this.applicationContext = applicationContext;
    }

//    @PostConstruct
    private void start() {
        Map<String, Object> services = applicationContext.getBeansWithAnnotation(RpcNativeService.class);
        for (Map.Entry<String, Object> entry : services.entrySet()) {
            Object bean = entry.getValue();
            Class<?>[] interfaces = bean.getClass().getInterfaces();
            for (Class<?> inter : interfaces) {
                rpcServices.put(inter.getName(),  bean);
            }
        }

        //获取所有服务
        new Thread(() -> {
            EventLoopGroup boss = new NioEventLoopGroup(1);
            EventLoopGroup worker = new NioEventLoopGroup();
            try {
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group(boss, worker)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                pipeline.addLast(new IdleStateHandler(0, 0, 60));
                                pipeline.addLast(new JsonServerDecoder());
                                pipeline.addLast(new JsonServerEncoder());
                                pipeline.addLast(new RpcServerInboundHandler(rpcServices));
                            }
                        })
                        .channel(NioServerSocketChannel.class);
                ChannelFuture future = bootstrap.bind(port).sync();
                System.out.println("RPC 服务器启动, 监听端口:" + port);
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
                boss.shutdownGracefully();
                worker.shutdownGracefully();
            }
        }).start();

    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Object> services = applicationContext.getBeansWithAnnotation(RpcNativeService.class);
        for (Map.Entry<String, Object> entry : services.entrySet()) {
            Object bean = entry.getValue();
            Class<?>[] interfaces = bean.getClass().getInterfaces();
            for (Class<?> inter : interfaces) {
                rpcServices.put(inter.getName(),  bean);
            }
        }

        //获取所有服务
        new Thread(() -> {
            EventLoopGroup boss = new NioEventLoopGroup(1);
            EventLoopGroup worker = new NioEventLoopGroup();
            try {
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group(boss, worker)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                pipeline.addLast(new IdleStateHandler(0, 0, 60));
                                pipeline.addLast(new JsonServerDecoder());
                                pipeline.addLast(new JsonServerEncoder());
                                pipeline.addLast(new RpcServerInboundHandler(rpcServices));
                            }
                        })
                        .channel(NioServerSocketChannel.class);
                ChannelFuture future = bootstrap.bind(port).sync();
                System.out.println("RPC 服务器启动, 监听端口:" + port);
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
                boss.shutdownGracefully();
                worker.shutdownGracefully();
            }
        }).start();
    }
}