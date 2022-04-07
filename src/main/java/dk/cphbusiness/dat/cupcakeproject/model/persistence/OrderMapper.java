package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Order;
import dk.cphbusiness.dat.cupcakeproject.model.entities.OrderDetail;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderMapper extends DataMapper<Order> implements IOrderMapper
{
    public OrderMapper(ConnectionPool connectionPool)
    {
        super(connectionPool);
    }

    @Override
    public DBEntity<Order> insert(Order order) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        DBEntity<Order> dbOrder;
        String sql = "insert into order (userID, requestedDelivery) values (?,?)";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
            {
                ps.setInt(1, order.getUserId());
                ps.setObject(2, order.getRequestedDelivery());

                int rowsAffected = ps.executeUpdate();
                ResultSet rs = ps.getResultSet();

                if (rowsAffected == 1)
                {
                    int id = rs.getInt(1);
                    insertOrderDetails(order, id);
                    dbOrder = new DBEntity<>(id,order);
                } else
                {
                    throw new DatabaseException("The order with userID = " + order.getUserId() + " and delivery date = "+order.getRequestedDelivery()+" could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert order into database");
        }
        return dbOrder;
    }
    private List<DBEntity<OrderDetail>> insertOrderDetails(Order order, int orderId) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        List<DBEntity<OrderDetail>> orderDetails = order.getOrderDetails();
        OrderDetail orderDetail;
        List<DBEntity<OrderDetail>> dbOrderDetails = new ArrayList<>();
        String sql = "insert into orderdetail (orderID, quantityOrdered, toppingID, bottomID, comments) values (?,?,?,?,?)";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
            {
                for (DBEntity<OrderDetail> od: orderDetails) {
                    orderDetail = od.getEntity();

                    ps.setInt(1, orderId);
                    ps.setInt(2, orderDetail.getQuantity());
                    ps.setInt(3, orderDetail.getToppingId());
                    ps.setInt(4, orderDetail.getBottomId());
                    ps.setString(5, orderDetail.getComments());

                    int rowsAffected = ps.executeUpdate();
                    ResultSet rs = ps.getResultSet();

                    if (rowsAffected == 1)
                    {
                        int newId = rs.getInt(1);
                        od.setId(newId);
                        od.getEntity().setOrderId(orderId);
                        dbOrderDetails.add(od);
                    } else
                    {
                        throw new DatabaseException("The orderdetail with orderID = " + orderId + " could not be inserted into the database");
                    }
                }

            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert the orderdetails into database");
        }
        return dbOrderDetails;
    }


    @Override
    public List<DBEntity<Order>> getAll() throws DatabaseException
    {
        return null;
    }

    @Override
    public Optional<DBEntity<Order>> findById(int id) throws DatabaseException
    {
        return Optional.empty();
    }

    public Optional<List<DBEntity<Order>>> findByUserId(int id) throws DatabaseException
    {
        return Optional.empty();
    }

    @Override
    public boolean update(DBEntity<Order> t) throws DatabaseException
    {
//        Logger.getLogger("web").log(Level.INFO, "");
//
//        String sql = "UPDATE order" +
//                "SET userID = ?, created = ?, requestedDelivery = ?, shipped = ?" +
//                "WHERE orderID = ?;";
//
//        try (Connection connection = connectionPool.getConnection())
//        {
//            try (PreparedStatement ps = connection.prepareStatement(sql))
//            {
//                User user = t.getEntity();
//                ps.setInt(1,user.getName());
//                ps.setObject(2,user.getEmail());
//                ps.setObject(3,user.getPhone());
//                ps.setObject(4,user.getPassword());
//                ps.setInt(5,t.getId());
//
//                //Role role = rs.getObject("role", Role.class);
//                int rowsAffected = ps.executeUpdate();
//
//                if (rowsAffected == 1)
//                {
//                    return true;
//                } else
//                {
//                    throw new DatabaseException("The user with email = " + t.getEntity().getEmail() + " could not be updated in the database");
//                }
//            }
//        } catch (SQLException ex)
//        {
//            throw new DatabaseException(ex, "Error updating selected user. Something went wrong with the database");
//        }
        return false;
    }

    @Override
    public boolean delete(DBEntity<Order> t) throws DatabaseException
    {
        return false;
    }
}
