package yuzhe.com.protocol;

import org.apache.commons.io.IOUtils;
import yuzhe.com.common.Invocation;
import yuzhe.com.register.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//可以使用责任链模式处理请求
public class HttpServerHandler {

    public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException,
            NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // 处理请求 ----> 接口，方法，方法参数
        Invocation invocation =
                (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
        // Throw IOExcepetion | ClassNotFoundException

        String interfaceName = invocation.getInterfaceName();

        Class classImpl = LocalRegister.get(interfaceName, "1.0");

        Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
        // Throw NoSuchMethodException

        String res = (String) method.invoke(classImpl.newInstance(), invocation.getParameters());

        IOUtils.write(res, resp.getOutputStream());
    }
}
