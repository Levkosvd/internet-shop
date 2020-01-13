package mate.academy.internetshop;

import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.libr.Injector;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class Main {
    @Inject
    static ItemService itemService;
    @Inject
    static BucketService bucketService;
    @Inject
    static OrderService orderService;
    @Inject
    static UserService userService;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
