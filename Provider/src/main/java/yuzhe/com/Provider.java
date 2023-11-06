package yuzhe.com;

import yuzhe.com.common.URL;
import yuzhe.com.protocol.HttpServer;
import yuzhe.com.register.LocalRegister;
import yuzhe.com.register.MapRemoteRegister;

public class Provider {

    public static void main(String[] args) {

        LocalRegister.regist(HelloService.class.getName(), "1.0", HelloServiceImpl.class);
        LocalRegister.regist(HelloService.class.getName(), "2.0", HelloServiceImpl2.class);

        //注册中心注册
        URL url = new URL("localhost", 8080);
        MapRemoteRegister.regist(HelloService.class.getName(), url);

        // Tomcat
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }
}
