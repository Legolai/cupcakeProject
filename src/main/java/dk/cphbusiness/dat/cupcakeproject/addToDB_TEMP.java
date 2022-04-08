package dk.cphbusiness.dat.cupcakeproject;

import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.DBType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class addToDB_TEMP {

    public static void main(String[] args) {
//        ConnectionPool connectionPool = new ConnectionPool(DBType.APPLICATION);
//        try (Connection testConnection = connectionPool.getConnection()) {
//            try (Statement stmt = testConnection.createStatement() ) {
//                // Remove all rows from all tables
//
//                stmt.execute("ALTER TABLE `CupcakeBottom` DISABLE KEYS");
//                stmt.execute("ALTER TABLE `CupcakeBottom` AUTO_INCREMENT = 1");
//                stmt.execute("insert into `CupcakeBottom` (`bottomName`, `price`) " +
//                        "values ('Chocolate', 5),('Vanilla', 5), ('Nutmeg', 5)");
//                stmt.execute("ALTER TABLE `CupcakeBottom` ENABLE KEYS");
//
//                stmt.execute("ALTER TABLE `CupcakeTopping` DISABLE KEYS");
//                stmt.execute("ALTER TABLE `CupcakeTopping` AUTO_INCREMENT = 1");
//                stmt.execute("insert into `CupcakeTopping` (`toppingName`, `price`) " +
//                        "values ('Chocolate', 5),('Blueberry', 5), ('Rasberry', 5)");
//                stmt.execute("ALTER TABLE `CupcakeTopping` ENABLE KEYS");
//
//
//                stmt.execute("ALTER TABLE `Order` DISABLE KEYS");
//                stmt.execute("ALTER TABLE `Order` AUTO_INCREMENT = 1");
//                stmt.execute("insert into `Order` (`userID`, `requestedDelivery`) " +
//                        "values (4, '2022-06-22 17:00:00'), (2, '2022-06-27 17:00:00'), (3, '2022-02-22 12:00:00')");
//                stmt.execute("ALTER TABLE `Order` ENABLE KEYS");
//
//                stmt.execute("ALTER TABLE `OrderDetail` DISABLE KEYS");
//                stmt.execute("ALTER TABLE `OrderDetail` AUTO_INCREMENT = 1");
//                stmt.execute("insert into `OrderDetail` (`orderNumber`, `quantityOrdered`, `toppingID`, `bottomID`) " +
//                        "values (1, 1, 1, 2), (1, 3, 1, 1), (1, 2, 3, 2), " +
//                        "(2, 1, 1, 2), (2, 3, 1, 1), (3, 2, 3, 3)");
//                stmt.execute("ALTER TABLE `OrderDetail` ENABLE KEYS");
//
//            }
//        } catch (SQLException throwables) {
//            System.out.println(throwables.getMessage());
//        }
    }

}
