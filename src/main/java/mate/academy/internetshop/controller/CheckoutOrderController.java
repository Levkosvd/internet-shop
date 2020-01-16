package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;

public class CheckoutOrderController extends HttpServlet {
    private static final Long USER_ID = 1L;
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        orderService.completeOrder(bucketService.getByUser(USER_ID)
                .getBucketItems(),USER_ID);
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllOrders");
    }
}
