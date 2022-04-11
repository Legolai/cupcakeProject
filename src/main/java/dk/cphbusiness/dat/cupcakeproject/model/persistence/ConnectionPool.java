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
    private static String user = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://localhost:3306/%s";


    public ConnectionPool()
    {
        this(user, password, url);
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
        String propUser = appProps.getProperty("USERNAME");
        String propPassword = appProps.getProperty("PASSWORD");
        String propDbName = dbType.equals(DBType.TEST) ? appProps.getProperty("TEST_DB") : appProps.getProperty("DB") ;
        String propDbUrl = String.format(url, propDbName);

        setupConnection(propUser, propPassword, propDbUrl);
    }

    public ConnectionPool(String user, String password, String url)
    {
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null)
        {
            // Prod: hent variabler fra setenv.sh i Tomcats bin folder
            user = System.getenv("JDBC_USER");
            password = System.getenv("JDBC_PASSWORD");
            url = System.getenv("JDBC_CONNECTION_STRING");
        }
        setupConnection(user, password, url);
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
