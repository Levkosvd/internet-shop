package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.service.BucketService;

public class BucketController extends HttpServlet {
    private static final Long USER_ID = 1L;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bucket bucket = bucketService.getByUser(USER_ID);
        req.setAttribute("bucketItems", bucket.getBucketItems());
        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
