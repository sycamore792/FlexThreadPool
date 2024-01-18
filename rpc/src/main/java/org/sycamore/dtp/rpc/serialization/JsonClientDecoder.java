package org.sycamore.dtp.rpc.serialization;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.sycamore.dtp.rpc.protocol.ResponseMessage;

/**
 * 将响应消息解码成 ResponseMessage
 */
public class JsonClientDecoder extends LengthFieldBasedFrameDecoder {
 
    public JsonClientDecoder(){
        super(Integer.MAX_VALUE, 0, 4, 0, 4);
    }
 
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf msg = (ByteBuf) super.decode(ctx, in);
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        ResponseMessage rpcResponse = JSON.parseObject(bytes, ResponseMessage.class);
        return rpcResponse;
    }
}