package org.sycamore.dtp.rpc.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.sycamore.dtp.rpc.protocol.RequestMessage;
import org.sycamore.dtp.rpc.protocol.ResponseMessage;

import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MessageSendHandler extends ChannelInboundHandlerAdapter {

    private ConcurrentHashMap<String, MessageCallBack> mapCallBack = new ConcurrentHashMap<String, MessageCallBack>();

    private volatile Channel channel;
    private SocketAddress remoteAddr;

    public Channel getChannel() {
        return channel;
    }

    public SocketAddress getRemoteAddr() {
        return remoteAddr;
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接服务器成功");
        super.channelActive(ctx);
        this.remoteAddr = this.channel.remoteAddress();
    }

    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel = ctx.channel();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("收到服务器返回的数据: {}", msg);

        ResponseMessage response = (ResponseMessage) msg;
        String messageId = response.getRequestId();
        MessageCallBack callBack = mapCallBack.get(messageId);
        if (callBack != null) {
            mapCallBack.remove(messageId);
            callBack.over(response);
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public void close() {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public MessageCallBack sendRequest(RequestMessage request) {
        MessageCallBack callBack = new MessageCallBack(request);
        mapCallBack.put(request.getRequestId(), callBack);
        channel.writeAndFlush(request);
        log.info("发送请求:{}", request);
        return callBack;
    }
}