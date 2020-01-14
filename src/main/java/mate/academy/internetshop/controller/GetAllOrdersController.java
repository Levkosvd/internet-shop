package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class GetAllOrdersController extends HttpServlet {
    private static final Long USER_ID = 1L;
    @Inject
    private static OrderService orderService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("orderList", orderService.getUserOrders(USER_ID));
        req.getRequestDispatcher("/WEB-INF/views/getAllOrdersOfUser.jsp").forward(req, resp);
    }
}
