package internetshop.controller;

import internetshop.exeptions.AuthenticationException;
import internetshop.exeptions.DataProcessingException;
import internetshop.lib.Inject;
import internetshop.model.User;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            User user = userService.login(req.getParameter("login"),
                    req.getParameter("psw"));

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("userId", user.getId());

            resp.sendRedirect(req.getContextPath() + "/servlet/index");
        } catch (AuthenticationException | DataProcessingException e) {
            req.setAttribute("errorMessage", "Incorrect login or password");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
