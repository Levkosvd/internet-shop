package internetshop.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import internetshop.exeptions.DataProcessingException;
import internetshop.lib.Inject;
import internetshop.model.Role;
import internetshop.model.User;
import internetshop.service.UserService;

public class AuthorizationFilter implements Filter {
    private static final String EMPTY_STRING = "";
    @Inject
    private  static UserService userService;
    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        protectedUrls.put("/servlet/getAllUsers", Role.RoleName.ADMIN);
        protectedUrls.put("/servlet/deleteUser", Role.RoleName.ADMIN);
        protectedUrls.put("/servlet/addItem", Role.RoleName.ADMIN);
        protectedUrls.put("/servlet/deleteItemFromList", Role.RoleName.ADMIN);

        protectedUrls.put("/servlet/getAllOrders", Role.RoleName.USER);
        protectedUrls.put("/servlet/deleteItemFromBucket", Role.RoleName.USER);
        protectedUrls.put("/servlet/deleteOrders", Role.RoleName.USER);
        protectedUrls.put("/servlet/checkoutOrder", Role.RoleName.USER);
        protectedUrls.put("/servlet/bucket", Role.RoleName.USER);
        protectedUrls.put("/servlet/addItemToBucket", Role.RoleName.USER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String reqUrl = req.getRequestURI().replace(req.getContextPath(), EMPTY_STRING);
        Role.RoleName roleNamePage = protectedUrls.get(reqUrl);
        if (roleNamePage == null) {
            processAuthenticated(req, resp, filterChain);
            return;
        }
        User user = null;
        try {
            user = userService.get((Long) req.getSession().getAttribute("userId"));
        } catch (DataProcessingException e) {
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        if (verifyRole(user, roleNamePage)) {
            processAuthenticated(req, resp, filterChain);
            return;
        }
        processDenied(req, resp);

    }

    private boolean verifyRole(User user, Role.RoleName roleNameUser) {
        return user.getRoles().stream().anyMatch(role -> role.getRoleName().equals(roleNameUser));
    }

    private void processAuthenticated(HttpServletRequest req,
                                      HttpServletResponse resp,
                                      FilterChain filterChain)
            throws IOException, ServletException {
        filterChain.doFilter(req, resp);
    }

    private void processDenied(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/servlet/denied");
    }

    @Override
    public void destroy() {
    }
}
