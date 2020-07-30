package com.tmw.api;

import com.tmw.annotation.RpcInterface;

@RpcInterface
public interface IHelloService {

    String sayHi(String name);

}
