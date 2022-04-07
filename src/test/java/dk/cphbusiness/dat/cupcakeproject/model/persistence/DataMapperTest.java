package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class DataMapperTest<T> implements IDataMapperTest<T>
{
    protected static ConnectionPool connectionPool;


    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(DBType.TEST);
    }

    @BeforeEach
    void setUp()
    {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement() ) {
                // Remove all rows from all tables
                stmt.execute("delete from `OrderDetail`");
                stmt.execute("delete from `CupcakeBottom`");
                stmt.execute("delete from `CupcakeTopping`");
                stmt.execute("delete from `Order`");
                stmt.execute("delete from `User`");

                stmt.execute("ALTER TABLE `User` DISABLE KEYS");
                stmt.execute("ALTER TABLE `User` AUTO_INCREMENT = 1");
                stmt.execute("insert into `User` (name, email, password, role) " +
                        "values ('Peter','a@a.dk','1234','CUSTOMER'),('Jens','b@b.dk','1234','ADMIN'), ('Olga','list@a.dk','1234','CUSTOMER')");
                stmt.execute("ALTER TABLE `User` ENABLE KEYS");

                stmt.execute("ALTER TABLE `CupcakeBottom` DISABLE KEYS");
                stmt.execute("ALTER TABLE `CupcakeBottom` AUTO_INCREMENT = 1");
                stmt.execute("insert into `CupcakeBottom` (`bottomName`, `price`) " +
                        "values ('Chocolate', 5),('Vanilla', 5), ('Nutmeg', 5), ('Pistacio', 6), ('Almond', 7)");
                stmt.execute("ALTER TABLE `CupcakeBottom` ENABLE KEYS");

                stmt.execute("ALTER TABLE `CupcakeTopping` DISABLE KEYS");
                stmt.execute("ALTER TABLE `CupcakeTopping` AUTO_INCREMENT = 1");
                stmt.execute("insert into `CupcakeTopping` (`toppingName`, `price`) " +
                        "values ('Chocolate', 5),('Blueberry', 5), ('Rasberry', 5), ('Crispy', 6), ('Strawberry', 7)");
                stmt.execute("ALTER TABLE `CupcakeTopping` ENABLE KEYS");


                stmt.execute("ALTER TABLE `Order` DISABLE KEYS");
                stmt.execute("ALTER TABLE `Order` AUTO_INCREMENT = 1");
                stmt.execute("insert into `Order` (`userID`, `requestedDelivery`) " +
                        "values (1, '2022-06-22 17:00:00'), (2, '2022-06-27 17:00:00'), (3, '2022-02-22 12:00:00')");
                stmt.execute("ALTER TABLE `Order` ENABLE KEYS");

                stmt.execute("ALTER TABLE `OrderDetail` DISABLE KEYS");
                stmt.execute("ALTER TABLE `OrderDetail` AUTO_INCREMENT = 1");
                stmt.execute("insert into `OrderDetail` (`orderNumber`, `quantityOrdered`, `toppingID`, `bottomID`) " +
                        "values (1, 1, 1, 2), (1, 3, 1, 1), (1, 2, 3, 2), " +
                        "(2, 1, 1, 2), (2, 3, 1, 1), (3, 2, 4, 5)");
                stmt.execute("ALTER TABLE `OrderDetail` ENABLE KEYS");

            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException
    {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null)
        {
            connection.close();
        }
    }
}
