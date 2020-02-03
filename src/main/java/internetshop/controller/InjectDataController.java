package internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import internetshop.exeptions.DataProcessingException;
import internetshop.libr.Inject;
import internetshop.model.Item;
import internetshop.model.Role;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.UserService;

public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Inject
    private static ItemService itemService;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User newAdmin = new User();
        newAdmin.addRole(Role.of("ADMIN"));
        newAdmin.setPassword("1234");
        newAdmin.setLogin("admin");
        newAdmin.setFirstName("Donald");
        newAdmin.setSurname("Trump");
        newAdmin.setAccountBalance(50000.0);

        User newUser = new User();
        newUser.addRole(Role.of("USER"));
        newUser.setPassword("1234");
        newUser.setLogin("user");
        newUser.setFirstName("Joe ");
        newUser.setSurname("Biden");
        newUser.setAccountBalance(12000.0);

        try {
            userService.create(newAdmin);
            userService.create(newUser);
            itemService.create(new Item("Samsung galaxy s10", 14000.0));
            itemService.create(new Item("Samsung galaxy a50", 8500.0));
            itemService.create(new Item("ASUS X75VD", 9500.0));
            itemService.create(new Item("Apple iPhone 11 Pro Max", 39400.0));
        } catch (DataProcessingException e) {
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }




        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
