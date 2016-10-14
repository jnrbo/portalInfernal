package repository;

/**
 * Created by junior on 25/09/16.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by junior and ramon.
 */

public class ConnectionFactory {

    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost/portal_infernal";
    private static final String USER = "root";
    private static final String PASSWORD = "meuSQL";
    private static Properties properties;

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao abrir Driver " + e);
        }

        properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASSWORD);
        properties.setProperty("useSSL", "false");
        properties.setProperty("autoReconnect", "true");
        connection = DriverManager.getConnection(URL, properties);
        return connection;
    }
}