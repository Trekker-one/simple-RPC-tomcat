package yuzhe.com.proxy;

import yuzhe.com.common.Invocation;
import yuzhe.com.common.URL;
import yuzhe.com.loadbalance.LoadBalancer;
import yuzhe.com.protocol.HttpClient;
import yuzhe.com.register.MapRemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProxyFactory {

    public static <T> T getProxy(Class interfaceClass) {


        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(),
                        method.getParameterTypes(), args);

                HttpClient httpClient = new HttpClient();

                // 服务发现
                List<URL> list = MapRemoteRegister.get(interfaceClass.getName());

                // 服务调用
                String res = null;
                List<URL> invokedUrls = new ArrayList<>();

                int maxError = 3;
                while(maxError > 0) {

                    list.removeAll(invokedUrls);

                    // 负载均衡
                    URL url = LoadBalancer.random(list);
                    invokedUrls.add(url);

                    try {
                        res = httpClient.send(url.getHostname(), url.getPort(), invocation);
                        break;
                    } catch (Exception e) {
                        if(--maxError != 0) continue;

                        // error-callback = yuzhe.com.HelloServiceErrorCallback
                        // 容错逻辑
                        return "Error !!!";
                    }
                }
                return res;

            }
        });


        return (T) proxyInstance;
    }
}
