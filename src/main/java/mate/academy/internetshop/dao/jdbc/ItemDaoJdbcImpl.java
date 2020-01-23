package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.libr.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);
    private static String DB_TABLE_NAME = "internet_shop.items";
    private static Statement statement = null;

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Item entity) {
        String query = String.format(Locale.ROOT,
                "INSERT INTO %s(name, price) VALUES ('%s', %.4f);",
                DB_TABLE_NAME, entity.getName(), entity.getPrice());
        try {
            statement = connection.createStatement();
            if (statement.executeUpdate(query) == 1) {
                logger.info("Success add");
            }
        } catch (SQLException e) {
            logger.warn("Can't find item by id - " + entity.getId());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement!");
                }
            }
        }

    }

    @Override
    public Optional<Item> get(Long id) {
        String query = String.format("SELECT * FROM %s WHERE item_id = %d;", DB_TABLE_NAME, id);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                resultSet.getString("name");
                resultSet.getDouble("price");

                Item getItem = new Item(resultSet.getString("name"),
                        resultSet.getDouble("price"));
                getItem.setId(resultSet.getLong("item_id"));
                return Optional.of(getItem);
            }
        } catch (SQLException e) {
            logger.warn("Can't find item by id - " + id);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement!");
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Item entity) {
        String query = String.format(Locale.ROOT,
                "UPDATE %s SET name = %s, price = %.4f WHERE item_id = %d;",
                DB_TABLE_NAME, entity.getName(),entity.getPrice(), entity.getId());
        try {
            statement = connection.createStatement();
            if (statement.executeUpdate(query) == 1) {
                logger.info("Success update");
            }
        } catch (SQLException e) {
            logger.warn("Can't find item by id - " + entity.getId());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement!");
                }
            }
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = String.format("DELETE FROM %s WHERE item_id = %d;", DB_TABLE_NAME, id);
        try {
            statement = connection.createStatement();
            if (statement.executeUpdate(query) == 1) {
                logger.info("Success delete");
                return true;
            }
        } catch (SQLException e) {
            logger.warn("Can't find item by id - " + id);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement!");
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(Item entity) {
        return false;
    }

    @Override
    public List<Item> getAll() {
        String query = String.format("SELECT * FROM %s;", DB_TABLE_NAME);
        List<Item> itemList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                resultSet.getString("name");
                resultSet.getDouble("price");

                Item getItem = new Item(resultSet.getString("name"),
                        resultSet.getDouble("price"));
                getItem.setId(resultSet.getLong("item_id"));
                itemList.add(getItem);
            }
        } catch (SQLException e) {
            logger.warn("SQL error ");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement!");
                }
            }
        }
        return itemList;
    }
}
