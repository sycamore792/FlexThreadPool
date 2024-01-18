package org.sycamore.dtp.starter.rpc.client;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sycamore.dtp.rpc.client.RpcProxy;

import java.lang.reflect.Proxy;


public class RpcFactoryBean<T> implements FactoryBean<T> {
    private Class<T> interfaceClass;

    private RpcProxy rpcProxy;
 
    public RpcFactoryBean(Class<T> interfaceClass,RpcProxy rpcProxy){
        this.interfaceClass = interfaceClass;
        this.rpcProxy = rpcProxy;
    }
 
    @Override
    public T getObject(){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, rpcProxy);
    }
 
    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }
}