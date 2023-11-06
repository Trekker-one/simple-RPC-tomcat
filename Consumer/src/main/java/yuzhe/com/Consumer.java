package yuzhe.com;

import yuzhe.com.common.Invocation;
import yuzhe.com.protocol.HttpClient;
import yuzhe.com.proxy.ProxyFactory;

public class Consumer {

    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String res = helloService.sayHello("huangyz");
        System.out.println(res);

    }
}
