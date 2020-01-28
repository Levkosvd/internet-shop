package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.service.BucketService;

public class LogoutController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        bucketService.deleteById(bucketService.getByUser((Long) req.getSession().getAttribute("userId")).getId());
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
