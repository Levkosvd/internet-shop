package mate.academy.internetshop;

import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.libr.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
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
        User newUser = new User("Jack", 1000);
        User newUser1 = new User("John", 1200);
        userService.create(newUser);
        userService.create(newUser1);
        Item laptop1 = new Item("AsusX75", 600.0);
        itemService.create(laptop1);
        Item laptop2 = new Item("AcerEX2519", 400.0);
        itemService.create(laptop2);
        Bucket bucket = new Bucket(newUser);
        bucketService.clear(bucket);
        bucketService.addItem(bucket,laptop1);
        bucketService.addItem(bucket,laptop2);
        bucketService.getAllItems(bucket).stream().forEach(System.out::println);
        System.out.println("------------------");
        bucketService.getAllItems(bucket).stream().forEach(System.out::println);
        System.out.println("------------------");
        itemService.getAll().stream().forEach(System.out::println);
        System.out.println("------------------");
        orderService.completeOrder(bucketService.getAllItems(bucket),newUser);
        orderService.getUserOrders(newUser).stream().forEach(System.out::println);
    }
}
