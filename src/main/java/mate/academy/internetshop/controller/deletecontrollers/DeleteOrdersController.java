package mate.academy.internetshop.controller.deletecontrollers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.service.OrderService;

public class DeleteOrdersController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        orderService.deleteById(Long.valueOf(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllOrders");
    }
}
