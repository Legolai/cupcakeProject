package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool
{
    private HikariDataSource ds;
    private static String USER = "root";
    private static String PASSWORD = "root";
    private static String URL = "jdbc:mysql://localhost:3306/%s";


    public ConnectionPool()
    {
        this(USER, PASSWORD, URL);
    }

    public ConnectionPool(DBType dbType)
    {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath().replace("%20", " ");
        String appConfigPath = rootPath + "app.properties";
        Properties appProps = new Properties();
        FileInputStream file = null;
        try
        {
            file = new FileInputStream(appConfigPath);
            appProps.load(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String user = appProps.getProperty("USERNAME");
        String password = appProps.getProperty("PASSWORD");
        String dbName = dbType.equals(DBType.TEST) ? appProps.getProperty("TEST_DB") : appProps.getProperty("DB") ;
        String dbUrl = String.format(URL, dbName);

        setupConnection(user, password, dbUrl);
    }

    public ConnectionPool(String USER, String PASSWORD, String URL)
    {
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null)
        {
            // Prod: hent variabler fra setenv.sh i Tomcats bin folder
            USER = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
            URL = System.getenv("JDBC_CONNECTION_STRING");
        }
        setupConnection(USER, PASSWORD, URL);
    }

    private void setupConnection(String user, String password, String url){
        String logMsg = String.format("Connection Pool created for: (%s, %s, %s)", user, password, url);
        Logger.getLogger("web").log(Level.INFO, logMsg);
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(5);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        this.ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException
    {
        Logger.getLogger("web").log(Level.INFO, ": get data connection");
        return ds.getConnection();
    }

    public void close()
    {
        Logger.getLogger("web").log(Level.INFO, "Shutting down connection pool");
        ds.close();
    }

}
