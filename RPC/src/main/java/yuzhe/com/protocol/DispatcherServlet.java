package yuzhe.com.protocol;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            HttpServerHandler httpServerHandler = new HttpServerHandler();
            httpServerHandler.handle(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
