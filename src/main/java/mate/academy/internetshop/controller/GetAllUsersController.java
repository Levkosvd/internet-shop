package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class GetAllUsersController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> userList = null;
        try {
            userList = userService.getAll();
        } catch (DataProcessingException e) {
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.setAttribute("greeting", "Mates");
        req.setAttribute("usersList", userList);
        req.getRequestDispatcher("/WEB-INF/views/getAllUsers.jsp").forward(req, resp);
    }
}
