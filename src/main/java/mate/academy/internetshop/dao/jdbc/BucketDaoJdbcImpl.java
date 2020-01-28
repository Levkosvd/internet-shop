package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.libr.Dao;
import mate.academy.internetshop.libr.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;
@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    @Inject
    private static ItemDao itemDao;
    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Bucket entity) {
        String query = "INSERT INTO internet_shop.buckets(user_id) VALUES (?);";
        try(PreparedStatement createPrepStatement = connection.prepareStatement(query)){
            createPrepStatement.setLong(1, entity.getIdUser());
            createPrepStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't add item by id - " + entity.getId(), e);
        }
    }

    @Override
    public Optional<Bucket> get(Long id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM internet_shop.buckets WHERE bucket_id = ?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bucket getBucket = new Bucket(resultSet.getLong("user_id"));
                getBucket.setId(resultSet.getLong("bucket_id"));
                getBucket.getBucketItems().addAll(getAllItemsFromBucket(getBucket.getId()));
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
    public void update(Bucket entity) {
        String remove = "DELETE FROM bucket_item WHERE bucket_id = ?;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(remove)) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Item item : entity.getBucketItems()) {
            addItemToDb(entity,item);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "DELETE FROM internet_shop.order_item WHERE bucket_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query); ) {
            preparedStatement.setLong(1, id);
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
    public List<Bucket> getAll() {
        PreparedStatement showPrepStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM internet_shop.buckets;";
        List<Bucket> bucketsList = new ArrayList<>();
        try{
            showPrepStatement = connection.prepareStatement(query);
            resultSet = showPrepStatement.executeQuery();
            while (resultSet.next()) {
                Bucket bucket = new Bucket(resultSet.getLong("user_id"));
                bucket.setId(resultSet.getLong("bucket_id"));
                bucket.setBucketItems(getAllItemsFromBucket(bucket.getId()));
                bucketsList.add(bucket);
            }
            return bucketsList;
        } catch (SQLException e) {
            logger.warn("Can't do action", e);
        } finally {
            closeResources(showPrepStatement, resultSet);
        }
        return bucketsList;
    }
    @Override
    public void addItemToDb(Bucket bucket, Item item) {
        String query = "INSERT INTO internet_shop.bucket_item(bucket_id, item_id) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, bucket.getId());
                statement.setLong(2, item.getId());
                statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private List<Item> getAllItemsFromBucket(Long bucketId) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM internet_shop.bucket_item "
                + "JOIN items ON items.item_id = bucket_item.item_id WHERE bucket_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
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
