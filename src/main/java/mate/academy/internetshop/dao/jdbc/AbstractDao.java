package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class AbstractDao<T> {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);
    protected final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }
    protected static void closeResources(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.warn("Can't close statement!", e);
            }
        }
    }
    protected static void closeResources(PreparedStatement preparedStatement, ResultSet resultSet) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.warn("Can't close statement!", e);
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.warn("Can't close resultSet!", e);
            }
        }
    }
}
