package internetshop.controller;

import internetshop.exeptions.DataProcessingException;
import internetshop.lib.Inject;
import internetshop.service.OrderService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllOrdersController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("orderList",
                    orderService.getUserOrders((Long) req.getSession()
                            .getAttribute("userId")));
        } catch (DataProcessingException e) {
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/WEB-INF/views/getAllOrdersOfUser.jsp")
                .forward(req, resp);
    }
}
