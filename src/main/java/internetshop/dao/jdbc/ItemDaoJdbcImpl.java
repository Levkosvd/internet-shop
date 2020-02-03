package internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import internetshop.dao.ItemDao;
import internetshop.exeptions.DataProcessingException;
import internetshop.libr.Dao;
import internetshop.model.Item;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Item entity) throws DataProcessingException {
        String query = "INSERT INTO internet_shop.items(name, price) VALUES (?, ?);";
        try (PreparedStatement createPrepStatement = connection.prepareStatement(query)) {
            createPrepStatement.setString(1, entity.getName());
            createPrepStatement.setDouble(2, entity.getPrice());
            createPrepStatement.execute();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create", e);
        }
    }

    @Override
    public Optional<Item> get(Long id) throws DataProcessingException {
        String query = "SELECT * FROM internet_shop.items WHERE item_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item getItem = new Item(resultSet.getString("name"),
                        resultSet.getDouble("price"));
                getItem.setId(resultSet.getLong("item_id"));
                return Optional.of(getItem);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get Item", e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Item entity) throws DataProcessingException {
        String query = "UPDATE internet_shop.items SET name = ?, price = ? WHERE item_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDouble(2, entity.getPrice());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update Item", e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        String query = "DELETE FROM internet_shop.items WHERE item_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1,id);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete by Item", e);
        }
        return false;
    }

    @Override
    public List<Item> getAll() throws DataProcessingException {
        String query = "SELECT * FROM internet_shop.items;";
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement showPrepStatement = connection.prepareStatement(query);
                ResultSet resultSet = showPrepStatement.executeQuery()) {
            while (resultSet.next()) {
                Item getItem = new Item(resultSet.getString("name"),
                        resultSet.getDouble("price"));
                getItem.setId(resultSet.getLong("item_id"));
                itemList.add(getItem);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all Item", e);
        }
        return itemList;
    }
}
