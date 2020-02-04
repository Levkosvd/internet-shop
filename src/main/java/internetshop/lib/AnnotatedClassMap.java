package internetshop.lib;

import java.util.HashMap;
import java.util.Map;
import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.dao.OrderDao;
import internetshop.dao.RoleDao;
import internetshop.dao.UserDao;
import internetshop.factory.Factory;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.UserService;

public class AnnotatedClassMap {
    private static final Map<Class, Object> classMap = new HashMap<>();

    static  {
        classMap.put(ItemDao.class, Factory.getItemDao());
        classMap.put(BucketDao.class, Factory.getBucketDao());
        classMap.put(OrderDao.class, Factory.getOrderDao());
        classMap.put(UserDao.class, Factory.getUserDao());
        classMap.put(RoleDao.class, Factory.getRoleDao());

        classMap.put(ItemService.class, Factory.getItemService());
        classMap.put(BucketService.class, Factory.getBucketService());
        classMap.put(OrderService.class, Factory.getOrderService());
        classMap.put(UserService.class, Factory.getUserService());

    }

    public static Object getImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }
}
