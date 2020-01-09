package mate.academy.internetshop;

import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.libr.Injector;
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
        user();
    }




    public static void item(){

    }
    public static void user(){
        User newUser = new User("Jack", 1000);
        User newUser1 = new User("John", 1200);
        userService.create(newUser);
        userService.create(newUser1);
        System.out.println(userService.get(newUser.getId()));
        System.out.println(userService.get(newUser1.getId()));
        newUser.setAccountBalance(2000);
        userService.update(newUser);
        System.out.println(userService.get(newUser.getId()));
        System.out.println("------------------");
        userService.getAll().forEach(System.out::println);
        System.out.println("------------------");
        userService.delete(newUser1);
        userService.deleteById(newUser.getId());
        userService.getAll().forEach(System.out::println);

        System.out.println(userService);
    }
}

