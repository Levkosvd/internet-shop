package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Item;

import java.util.List;

public interface ItemService extends GenericService<Item, Long> {

    List getAllItems();
}
