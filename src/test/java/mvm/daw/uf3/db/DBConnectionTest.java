package mvm.daw.uf3.db;

import mvm.daw.uf3.db.DBConnection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DBConnectionTest {
    private DBConnection dbConnection;
    private Connection connection;
    private String connectionProperties = "db.properties";

    @Before
    public void setUp() {
        dbConnection = new DBConnection(connectionProperties);
    }

    @After
    public void cleanUp() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testDbConnection() throws IOException, SQLException {
        connection = dbConnection.getConnection();
        assertNotNull(connection);
        assertEquals("H2 JDBC Driver", connection.getMetaData().getDriverName());
        assertEquals("DWES_DB", connection.getCatalog());
    }

    @Test
    public void testDbConnectionWithWrongDriver() throws IOException, SQLException {
        dbConnection = new DBConnection("db_wrong_driver.properties");
        connection = dbConnection.getConnection();
        assertNull(connection);
    }

    @Test
    public void testDbConnectionWithMissingPropertiesFile() throws IOException, SQLException {
        dbConnection = new DBConnection("db_missing.properties");
        connection = dbConnection.getConnection();
        assertNull(connection);
    }

    @Test
    public void testDbConnectionWithWrongUrl() throws IOException, SQLException {
        dbConnection = new DBConnection("db_wrong_url.properties");
        connection = dbConnection.getConnection();
        assertNull(connection);
    }

    @Test
    public void testDbConnectionWithWrongUsername() throws IOException, SQLException {
        dbConnection = new DBConnection("db_wrong_username.properties");
        connection = dbConnection.getConnection();
        assertNull(connection);
    }

    @Test
    public void testDbConnectionWithWrongPassword() throws IOException, SQLException {
        dbConnection = new DBConnection("db_wrong_password.properties");
        connection = dbConnection.getConnection();
        assertNull(connection);
    }
}
