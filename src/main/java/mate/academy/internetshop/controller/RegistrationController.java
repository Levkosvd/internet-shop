package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

public class RegistrationController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp")
            .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User newUser = new User();
        newUser.setLogin(req.getParameter("login"));
        newUser.setPassword(req.getParameter("psw"));
        newUser.setFirstName(req.getParameter("user_first_name"));
        newUser.setSurname(req.getParameter("user_surname"));
        newUser.setAccountBalance(Double.parseDouble(req.getParameter("balance")));
        byte [] s = HashUtil.getRandomSalt();
        newUser.setSalt(s);

        try {
            userService.create(newUser);
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("userId",newUser.getId());
            bucketService.create(new Bucket(newUser.getId()));
        } catch (DataProcessingException e) {
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
