package mate.academy.internetshop.controller.deletecontrollers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exeptions.DataProcessingException;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.service.BucketService;

public class DeleteItemFromBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            bucketService.deleteItem(bucketService
                            .getByUser((Long) req.getSession().getAttribute("userId")),
                    Long.valueOf(req.getParameter("itemId")));
        } catch (DataProcessingException e) {
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/bucket");
    }
}
