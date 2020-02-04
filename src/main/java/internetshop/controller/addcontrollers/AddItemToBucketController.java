package internetshop.controller.addcontrollers;

import internetshop.exeptions.DataProcessingException;
import internetshop.lib.Inject;
import internetshop.model.Bucket;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddItemToBucketController extends HttpServlet {
    @Inject
    private static ItemService itemService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Bucket bucket = bucketService.getByUser((Long) req.getSession().getAttribute("userId"));
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            bucketService.addItem(bucket,
                    itemService.get(Long.valueOf(req.getParameter("itemId"))));
        } catch (DataProcessingException e) {
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
