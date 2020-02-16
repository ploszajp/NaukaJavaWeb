package io.github.mat3e;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet(name = "hello", urlPatterns = {"/api/*"})
public class HelloServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);
    private static final String NAME_PARAM = "name";

    private HelloService helloService;

    /**
     * Server container needs it.
     */
    @SuppressWarnings("unused")
    public HelloServlet()
    {
        helloService = new HelloService();
    }

    HelloServlet(HelloService service)
    {
        helloService = service;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request with params :" + req.getParameterMap());
        resp.getWriter().write(helloService.prepareGreeting(req.getParameter(NAME_PARAM)));
    }
}
