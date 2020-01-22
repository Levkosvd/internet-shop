package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;

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
        User user1 = new User();
        user1.setFirstName("Donald");
        user1.setSurname("Trump");
        user1.setLogin("mrblond");
        user1.setAccountBalance(50_000);
        user1.setPassword("1");
        user1.addRole(Role.of("ADMIN"));
        userService.create(user1);
        bucketService.create(new Bucket(user1.getId()));

        User user2 = new User();
        user2.setFirstName("Joe");
        user2.setSurname("Biden");
        user2.setLogin("biden");
        user2.setAccountBalance(12_000);
        user2.setPassword("1");
        user2.addRole(Role.of("USER"));
        userService.create(user2);
        bucketService.create(new Bucket(user2.getId()));

        itemService.create(new Item("SamsungA7", 4500.0));
        itemService.create(new Item("AsusX75", 11000.0));
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
