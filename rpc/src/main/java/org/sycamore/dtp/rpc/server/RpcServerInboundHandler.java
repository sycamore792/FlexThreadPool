package org.sycamore.dtp.rpc.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.sycamore.dtp.rpc.protocol.RequestMessage;
import org.sycamore.dtp.rpc.protocol.ResponseMessage;

import java.lang.reflect.Method;
import java.util.Map;

@Slf4j
public class RpcServerInboundHandler extends ChannelInboundHandlerAdapter {
    private Map<String, Object> rpcServices;

    public RpcServerInboundHandler(Map<String, Object> rpcServices) {
        this.rpcServices = rpcServices;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("客户端连接成功: " + ctx.channel().remoteAddress());
    }

    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("客户端断开连接: " + ctx.channel().remoteAddress());
        ctx.channel().close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        RequestMessage rpcRequest = (RequestMessage) msg;
        log.info("接收到客户端请求, 请求接口:"+ rpcRequest.getClassName() +", 请求方法:"+ rpcRequest.getMethodName());
        ResponseMessage response = new ResponseMessage();
        response.setRequestId(rpcRequest.getRequestId());
        Object result = null;
        try {
            result = this.handleRequest(rpcRequest);
            response.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            response.setSuccess(false);
            response.setErrorMessage(e.getMessage());
        }
        log.info("服务器处理完成, 返回结果:"+ response);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("发生异常:{}", cause.getMessage());
        ctx.channel().close();
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.ALL_IDLE) {
                log.error("客户端超时");
                ctx.channel().close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    private Object handleRequest(RequestMessage rpcRequest) throws Exception {
        Object bean = rpcServices.get(rpcRequest.getClassName());
        if (bean == null) {
            throw new RuntimeException("未找到对应的服务: " + rpcRequest.getClassName());
        }
        Method method = bean.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
        method.setAccessible(true);
        return method.invoke(bean, rpcRequest.getParameters());
    }
}