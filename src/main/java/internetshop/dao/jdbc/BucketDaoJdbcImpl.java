package internetshop.dao.jdbc;

import internetshop.dao.BucketDao;
import internetshop.exeptions.DataProcessingException;
import internetshop.lib.Dao;
import internetshop.model.Bucket;
import internetshop.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Bucket entity) {
        String query = "INSERT INTO internet_shop.buckets(user_id) VALUES (?);";
        try (PreparedStatement createPrepStatement = connection.prepareStatement(query)) {
            createPrepStatement.setLong(1, entity.getIdUser());
            createPrepStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't add item by id - " + entity.getId(), e);
        }
    }

    @Override
    public Optional<Bucket> get(Long id) throws DataProcessingException {
        String query = "SELECT * FROM internet_shop.buckets WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.setLong(1, id);
            if (resultSet.next()) {
                Bucket getBucket = new Bucket(resultSet.getLong("user_id"));
                getBucket.setId(resultSet.getLong("bucket_id"));
                getBucket.getBucketItems().addAll(getAllItemsFromBucket(getBucket.getId()));
                return Optional.of(getBucket);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get bucket by id -" + id, e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Bucket entity) throws DataProcessingException {
        String remove = "DELETE FROM bucket_item WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(remove)) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update bucket" + entity.getId(), e);
        }
        for (Item item : entity.getBucketItems()) {
            addItemToDb(entity,item);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        String query = "DELETE FROM internet_shop.buckets WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete bucket by id" + id, e);
        }
        return false;
    }

    @Override
    public List<Bucket> getAll() throws DataProcessingException {
        String query = "SELECT * FROM internet_shop.buckets;";
        List<Bucket> bucketsList = new ArrayList<>();
        try (PreparedStatement showPrepStatement = connection.prepareStatement(query);
                ResultSet resultSet = showPrepStatement.executeQuery()) {
            while (resultSet.next()) {
                Bucket bucket = new Bucket(resultSet.getLong("user_id"));
                bucket.setId(resultSet.getLong("bucket_id"));
                bucket.setBucketItems(getAllItemsFromBucket(bucket.getId()));
                bucketsList.add(bucket);
            }
            return bucketsList;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all bucket", e);
        }
    }

    @Override
    public void addItemToDb(Bucket bucket, Item item) throws DataProcessingException {
        String query = "INSERT INTO internet_shop.bucket_item(bucket_id, item_id) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucket.getId());
            statement.setLong(2, item.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't add Item to database", ex);
        }
    }

    private List<Item> getAllItemsFromBucket(Long bucketId) throws DataProcessingException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM internet_shop.bucket_item "
                + "JOIN items ON items.item_id = bucket_item.item_id WHERE bucket_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item getItem = new Item(resultSet.getString("name"),
                        resultSet.getDouble("price"));
                getItem.setId(resultSet.getLong("item_id"));
                items.add(getItem);
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't get all Items with bucket", ex);
        }
        return items;
    }
}
