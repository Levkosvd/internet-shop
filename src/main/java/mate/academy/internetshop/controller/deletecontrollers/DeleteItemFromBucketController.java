package mate.academy.internetshop.controller.deletecontrollers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.service.BucketService;

public class DeleteItemFromBucketController extends HttpServlet {
    private static final Long USER_ID = 1L;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        bucketService.deleteItem(bucketService.getByUser(USER_ID),
                Long.valueOf(req.getParameter("itemId")));
        resp.sendRedirect(req.getContextPath() + "/servlet/bucket");
    }
}
