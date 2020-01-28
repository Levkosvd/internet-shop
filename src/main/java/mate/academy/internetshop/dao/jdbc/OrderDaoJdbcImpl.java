package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.libr.Dao;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import org.apache.log4j.Logger;
@Dao
public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);
    @Inject
    private static ItemDao itemDao;

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Order entity) {
        String query = "INSERT INTO internet_shop.orders(user_id) VALUES (?);";
        try (PreparedStatement createPrepStatement = connection
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {

            createPrepStatement.setLong(1, entity.getIdUser());
            createPrepStatement.executeUpdate();
            ResultSet resultSet = createPrepStatement.getGeneratedKeys();
            while(resultSet.next()) {
                entity.setId(resultSet.getLong(1));
            }
            addItemToDb(entity);
        } catch (SQLException e) {
            logger.warn("Can't add item by id - " + entity.getId(), e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM internet_shop.orders WHERE order_id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order getBucket = new Order();
                getBucket.setIdUser(resultSet.getLong("user_id"));
                getBucket.setId(resultSet.getLong("order_id"));
                getBucket.setItems(getAllItemsFromOrder(getBucket.getId()));
                return Optional.of(getBucket);
            }
        } catch (SQLException e) {
            logger.warn("Can't find bucket by id - " + id, e);
        } finally {
            closeResources(preparedStatement, resultSet);
        }
        return Optional.empty();
    }

    @Override
    public void update(Order entity) {
        String remove = "DELETE FROM internet_shop.order_item WHERE order_id = ?;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(remove)) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addItemToDb(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "DELETE FROM internet_shop.orders WHERE order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1,id);
            if (preparedStatement.executeUpdate() == 1) {
                logger.info("Success delete");
                return true;
            }
        } catch (SQLException e) {
            logger.warn("Can't delete item by id - " + id, e);
        }
        return false;
    }

    @Override
    public List<Order> getAll() {
        PreparedStatement showPrepStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM internet_shop.orders;";
        List<Order> ordersList = new ArrayList<>();
        try{
            showPrepStatement = connection.prepareStatement(query);
            resultSet = showPrepStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdUser(resultSet.getLong("user_id"));
                order.setId(resultSet.getLong("order_id"));
                order.setItems(getAllItemsFromOrder(order.getId()));
                ordersList.add(order);
            }
        } catch (SQLException e) {
            logger.warn("Can't do action", e);
        } finally {
            closeResources(showPrepStatement, resultSet);
        }
        return ordersList;
    }
    private void addItemToDb(Order order) {
        List<Item> items = order.getItems();
        String query = "INSERT INTO internet_shop.order_item(order_id, item_id) VALUES (?, ?);";
        try {
            for(Item item : items) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, order.getId());
                statement.setLong(2, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private List<Item> getAllItemsFromOrder(Long orderId) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM internet_shop.order_item "
                + "JOIN items ON items.item_id = order_item.item_id WHERE order_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(itemDao.get(resultSet.getLong("item_id")).get());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }
}
