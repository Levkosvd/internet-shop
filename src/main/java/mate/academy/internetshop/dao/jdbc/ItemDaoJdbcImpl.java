package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.libr.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Item entity) {
        PreparedStatement createPrepStatement = null;
        String query = "INSERT INTO internet_shop.items(name, price) VALUES (?, ?);";
        try{
            createPrepStatement = connection.prepareStatement(query);

            createPrepStatement.setString(1, entity.getName());
            createPrepStatement.setDouble(2, entity.getPrice());
            createPrepStatement.execute();
        } catch (SQLException e) {
            logger.warn("Can't add item by id - " + entity.getId(), e);
        } finally {
            closeResources(createPrepStatement);
        }
    }

    @Override
    public Optional<Item> get(Long id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM internet_shop.items WHERE item_id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item getItem = new Item(resultSet.getString("name"),
                        resultSet.getDouble("price"));
                getItem.setId(resultSet.getLong("item_id"));
                return Optional.of(getItem);
            }
        } catch (SQLException e) {
            logger.warn("Can't find item by id - " + id, e);
        } finally {
            closeResources(preparedStatement, resultSet);
        }
        return Optional.empty();
    }

    @Override
    public void update(Item entity) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE internet_shop.items SET name = ?, price = ? WHERE item_id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDouble(2, entity.getPrice());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't update item by id - " + entity.getId(), e);
        } finally {
            closeResources(preparedStatement);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM internet_shop.items WHERE item_id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,id);
            if (preparedStatement.executeUpdate() == 1) {
                logger.info("Success delete");
                return true;
            }
        } catch (SQLException e) {
            logger.warn("Can't delete item by id - " + id, e);
        } closeResources(preparedStatement);
        return false;
    }

    @Override
    public List<Item> getAll() {
        PreparedStatement showPrepStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM internet_shop.items;";
        List<Item> itemList = new ArrayList<>();
        try{
            showPrepStatement = connection.prepareStatement(query);
            resultSet = showPrepStatement.executeQuery();
            while (resultSet.next()) {
                Item getItem = new Item(resultSet.getString("name"),
                        resultSet.getDouble("price"));
                getItem.setId(resultSet.getLong("item_id"));
                itemList.add(getItem);
            }
        } catch (SQLException e) {
            logger.warn("Can't do action", e);
        } finally {
            closeResources(showPrepStatement, resultSet);
        }
        return itemList;
    }
}
