package org.sycamore.dtp.rpc.serialization;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.sycamore.dtp.rpc.protocol.RequestMessage;
import org.sycamore.dtp.rpc.protocol.ResponseMessage;

/**
 * 将 ResponseMessage 编码成字节序列发送
 * 消息格式: Length + Content
 * Length使用int存储,标识消息体的长度
 *
 * +--------+----------------+
 * | Length |  Content       |
 * |  4字节 |   Length个字节  |
 * +--------+----------------+
 */
public class JsonServerEncoder extends MessageToByteEncoder<ResponseMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ResponseMessage responseMessage, ByteBuf out){
        byte[] bytes = JSON.toJSONBytes(responseMessage);
        // 将消息体的长度写入消息头部
        out.writeInt(bytes.length);
        // 写入消息体
        out.writeBytes(bytes);
    }
}