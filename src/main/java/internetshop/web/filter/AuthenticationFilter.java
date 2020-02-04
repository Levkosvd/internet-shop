package internetshop.web.filter;

import internetshop.exeptions.DataProcessingException;
import internetshop.lib.Inject;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {
    @Inject
    private static UserService userService;
    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getSession() != null && req.getSession().getAttribute("userId") != null) {
            try {
                LOGGER.info("User " + userService.get((Long) req.getSession()
                        .getAttribute("userId")) + " was authenticated");
            } catch (DataProcessingException e) {
                req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            }
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        LOGGER.info("Authentication failed");
        processUnAuthenticated(req,resp);
    }

    private void processUnAuthenticated(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    public void destroy() {

    }
}
