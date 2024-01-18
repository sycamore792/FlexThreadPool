package org.sycamore.dtp.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.sycamore.dtp.rpc.serialization.JsonClientDecoder;
import org.sycamore.dtp.rpc.serialization.JsonClientEncoder;

import java.net.InetSocketAddress;
@Slf4j
public class MessageSendInitializeTask implements Runnable {

    private EventLoopGroup eventLoopGroup = null;
    private InetSocketAddress serverAddress = null;
    private RpcServerLoader loader = null;

    MessageSendInitializeTask(EventLoopGroup eventLoopGroup, InetSocketAddress serverAddress, RpcServerLoader loader) {
        this.eventLoopGroup = eventLoopGroup;
        this.serverAddress = serverAddress;
        this.loader = loader;
    }

    public void run() {
        Bootstrap b = new Bootstrap();
        b.group(eventLoopGroup)
                .channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true);
//        b.handler(new MessageSendChannelInitializer());
    b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast(new IdleStateHandler(0, 0, 10));
                pipeline.addLast(new JsonClientDecoder());
                pipeline.addLast(new JsonClientEncoder());
                pipeline.addLast(new MessageSendHandler());
            }
        });
        ChannelFuture channelFuture = b.connect(serverAddress);
        channelFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(final ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    log.info("Initialize task started successfully, server address: {}.", serverAddress.toString());
                    MessageSendHandler handler = channelFuture.channel().pipeline().get(MessageSendHandler.class);
                    MessageSendInitializeTask.this.loader.setMessageSendHandler(handler);
                }
            }
        });
    }
}