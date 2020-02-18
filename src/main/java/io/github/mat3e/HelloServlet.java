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
    private final String LANG_PARAM = "lang";

    private HelloService service;

    /**
     * Server container needs it.
     */
    @SuppressWarnings("unused")
    public HelloServlet()
    {
        service = new HelloService();
    }

    HelloServlet(HelloService service)
    {
        service = service;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request with params :" + req.getParameterMap());
        var lang = req.getParameter(LANG_PARAM);
        var name =req.getParameter(NAME_PARAM);

        resp.getWriter().write(service.prepareGreeting(name, lang));
    }
}
