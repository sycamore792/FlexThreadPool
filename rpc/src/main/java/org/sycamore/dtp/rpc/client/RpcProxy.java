package org.sycamore.dtp.rpc.client;

import lombok.extern.slf4j.Slf4j;
import org.sycamore.dtp.rpc.protocol.RequestMessage;
import org.sycamore.dtp.rpc.protocol.ResponseMessage;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

@Slf4j
public class RpcProxy implements InvocationHandler {

    private RpcClient rpcClient;

    public RpcProxy(RpcClient rpcClient) {
        this.rpcClient = rpcClient;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InterruptedException {
        RequestMessage rpcRequest = new RequestMessage();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        rpcRequest.setParameterTypes(method.getParameterTypes());
        rpcRequest.setRequestId(UUID.randomUUID().toString());
        RpcServerLoader instance = RpcServerLoader.getInstance();

        MessageSendHandler messageSendHandler = instance.getMessageSendHandler();
        if (!messageSendHandler.getChannel().isActive()){
            log.info("channel is not active,wait for 500ms");
            //尝试重连
            instance.reload();
        }
        messageSendHandler = instance.getMessageSendHandler();
        MessageCallBack messageCallBack = messageSendHandler.sendRequest(rpcRequest);
        return messageCallBack.start();
    }

}