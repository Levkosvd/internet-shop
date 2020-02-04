package internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import internetshop.exeptions.DataProcessingException;
import internetshop.lib.Inject;
import internetshop.service.BucketService;

public class LogoutController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            bucketService.deleteById(bucketService.getByUser((Long) req.getSession()
                    .getAttribute("userId")).getId());
        } catch (DataProcessingException e) {
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.getSession(false).invalidate();
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("MATE")) {
                cookie.setMaxAge(0);
                cookie.setValue(null);
                resp.addCookie(cookie);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
