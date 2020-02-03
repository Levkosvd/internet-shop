package internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import internetshop.exeptions.DataProcessingException;
import internetshop.libr.Inject;
import internetshop.model.Bucket;
import internetshop.service.BucketService;
import org.apache.log4j.Logger;

public class BucketController extends HttpServlet {
    private static Logger logger = Logger.getLogger(BucketController.class);
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bucket bucket = null;
        try {
            bucket = bucketService.getByUser((Long) req.getSession().getAttribute("userId"));
        } catch (DataProcessingException e) {
            logger.error(e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.setAttribute("bucketItems", bucket.getBucketItems());
        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
