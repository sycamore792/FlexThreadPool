package org.sycamore.dtp.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.sycamore.dtp.rpc.protocol.RequestMessage;
import org.sycamore.dtp.rpc.protocol.ResponseMessage;
import org.sycamore.dtp.rpc.serialization.JsonClientEncoder;


import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * RPC远程调用的客户端
 */
public class RpcClient {
    private String remoteIp;

    private int port;
 
    private Bootstrap bootstrap;
 
    // 储存调用结果
    private final Map<String, SynchronousQueue<ResponseMessage>> results = new ConcurrentHashMap<>();
    public RpcClient(String remoteIp,int port){
        this.remoteIp = remoteIp;
        this.port = port;
    }
 
//    @PostConstruct
//    public void init(){
//        bootstrap = new Bootstrap().remoteAddress(remoteIp, port);
//        NioEventLoopGroup worker = new NioEventLoopGroup(1);
//        bootstrap.group(worker)
//                .channel(NioSocketChannel.class)
//                .handler(new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    protected void initChannel(SocketChannel channel) throws Exception {
//                        ChannelPipeline pipeline = channel.pipeline();
//                        pipeline.addLast(new IdleStateHandler(0, 0, 10));
//                        pipeline.addLast(new JsonClientEncoder());
//                        pipeline.addLast(new JsonClientEncoder());
//                        pipeline.addLast(new RpcClientInboundHandler(results));
//                    }
//                });
//    }
 
    public ResponseMessage send(RequestMessage rpcRequest) {
        ResponseMessage rpcResponse = null;
        rpcRequest.setRequestId(UUID.randomUUID().toString());
        Channel channel = null;
        try {
            channel = bootstrap.connect().sync().channel();
            System.out.println("连接建立,发送请求:"+rpcRequest);
            channel.writeAndFlush(rpcRequest);
            SynchronousQueue<ResponseMessage> queue = new SynchronousQueue<>();
            results.put(rpcRequest.getRequestId(), queue);
            // 阻塞等待获取响应
            rpcResponse = queue.take();
            results.remove(rpcRequest.getRequestId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(channel != null && channel.isActive()){
                channel.close();
            }
        }
        return rpcResponse;
    }
}