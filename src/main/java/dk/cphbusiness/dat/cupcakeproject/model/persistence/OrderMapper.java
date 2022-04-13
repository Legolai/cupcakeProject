package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Order;
import dk.cphbusiness.dat.cupcakeproject.model.entities.OrderDetail;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

import java.sql.*;
import java.time.LocalDateTime;
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
        String sql = "insert into `order` (userID, created, requestedDelivery) values (?,?,?)";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setInt(1, order.getUserId());
                ps.setObject(2, order.getCreated());
                ps.setObject(3, order.getRequestedDelivery());

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1)
                {
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    int id = rs.getInt(1);
                    insertOrderDetails(order, id);
                    dbOrder = new DBEntity<>(id, order);
                } else
                {
                    throw new DatabaseException("The order with userID = " + order.getUserId() + " and delivery date = " + order.getRequestedDelivery() + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DatabaseException(ex, "Could not insert order into database");
        }
        return dbOrder;
    }
    private List<DBEntity<OrderDetail>> insertOrderDetails(Order order, int orderId) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        List<DBEntity<OrderDetail>> orderDetailList = order.getOrderDetails();
        OrderDetail orderDetail;
        List<DBEntity<OrderDetail>> dbOrderDetails = new ArrayList<>();
        String sql = "insert into `orderdetail` (orderNumber, quantityOrdered, toppingID, bottomID, comments) values (?,?,?,?,?)";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                for (DBEntity<OrderDetail> od : orderDetailList)
                {
                    orderDetail = od.getEntity();

                    ps.setInt(1, orderId);
                    ps.setInt(2, orderDetail.getQuantity());
                    ps.setInt(3, orderDetail.getToppingId());
                    ps.setInt(4, orderDetail.getBottomId());
                    ps.setString(5, orderDetail.getComments());

                    ps.addBatch();
                }

                ps.executeBatch();
                ResultSet rs = ps.getGeneratedKeys();

                int currentIndex = 0;
                while (rs.next())
                {
                    DBEntity<OrderDetail> od = orderDetailList.get(currentIndex);
                    int newId = rs.getInt(1);
                    od.setId(newId);
                    od.getEntity().setOrderId(orderId);
                    dbOrderDetails.add(od);
                    ++currentIndex;
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
        Logger.getLogger("web").log(Level.INFO, "");

        List<DBEntity<Order>> orderList = new ArrayList<>();

        String sql = "SELECT * FROM `order` order by 'orderID';";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                Order order;
                DBEntity<Order> dbOrder;

                while (rs.next())
                {
                    int orderID = rs.getInt("orderID");
                    int userID = rs.getInt("userID");
                    LocalDateTime created = (LocalDateTime) rs.getObject("created");
                    LocalDateTime requestedDelivery = (LocalDateTime) rs.getObject("requestedDelivery");
                    LocalDateTime shipped = (LocalDateTime) rs.getObject("shipped");
                    boolean isPaid = rs.getBoolean("paid");

                    List<DBEntity<OrderDetail>> orderDetails = getOrderDetailsFromOrderID(orderID);

                    order = new Order(userID, created, requestedDelivery, shipped, orderDetails, isPaid);
                    dbOrder = new DBEntity<>(orderID, order);
                    orderList.add(dbOrder);
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error getting all users. Something went wrong with the database");
        }
        return orderList;
    }

    @Override
    public Optional<DBEntity<Order>> findById(int id) throws DatabaseException
    {
        return Optional.empty();
    }

    @Override
    public Optional<List<DBEntity<Order>>> findByUserId(int id) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        Optional<List<DBEntity<Order>>> optionalDBEntityList = Optional.empty();
        List<DBEntity<Order>> list = new ArrayList<>();
        String sql = "SELECT * FROM `order` WHERE userID = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Order order;
                DBEntity<Order> dbOrder;

                if (rs.next())
                {
                    int orderID = rs.getInt("orderID");
                    int userID = rs.getInt("userID");
                    LocalDateTime created = (LocalDateTime) rs.getObject("created");
                    LocalDateTime requestedDelivery = (LocalDateTime) rs.getObject("requestedDelivery");
                    LocalDateTime shipped = (LocalDateTime) rs.getObject("shipped");
                    boolean isPaid = rs.getBoolean("paid");

                    List<DBEntity<OrderDetail>> orderDetails = getOrderDetailsFromOrderID(orderID);

                    order = new Order(userID, created, requestedDelivery, shipped, orderDetails, isPaid);
                    dbOrder = new DBEntity<>(orderID, order);
                    list.add(dbOrder);
                } else
                {
                    throw new DatabaseException("Order with userID: " + id + " was not found");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error finding user by id. Something went wrong with the database");
        }
        optionalDBEntityList = Optional.of(list);
        return optionalDBEntityList;
    }
    private List<DBEntity<OrderDetail>> getOrderDetailsFromOrderID(int orderId) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        List<DBEntity<OrderDetail>> orderDetailEntityList = new ArrayList<>();
        String sql = "SELECT * FROM orderdetail WHERE orderNumber = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, orderId);
                ResultSet rs = ps.executeQuery();
                OrderDetail orderDetail;
                DBEntity<OrderDetail> dbOrderDetail;

                if (rs.next())
                {
                    System.out.println("times in order detail");

                    int orderDetailID = rs.getInt("orderDetailID");
                    int orderNumber = rs.getInt("orderNumber");
                    int quantityOrdered = rs.getInt("quantityOrdered");
                    int toppingID = rs.getInt("toppingID");
                    int bottomID = rs.getInt("bottomID");
                    String comments = rs.getString("comments");

                    orderDetail = new OrderDetail(toppingID, bottomID, quantityOrdered, orderNumber, comments);
                    dbOrderDetail = new DBEntity<>(orderDetailID, orderDetail);
                    orderDetailEntityList.add(dbOrderDetail);
                } else
                {
                    throw new DatabaseException("Orderdetail with orderNumber: " + orderId + " was not found");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error finding user by id. Something went wrong with the database");
        }
        return orderDetailEntityList;
    }

    @Override
    public boolean update(DBEntity<Order> t) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "UPDATE `order`" +
                " SET userID = ?, created = ?, requestedDelivery = ?, shipped = ?, paid = ?" +
                " WHERE orderID = ?;";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                Order order = t.getEntity();
                ps.setInt(1,order.getUserId());
                ps.setObject(2,order.getCreated());
                ps.setObject(3,order.getRequestedDelivery());
                ps.setObject(4,order.getShipped());
                ps.setBoolean(5,order.getIsPaid());
                ps.setInt(6,t.getId());

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1)
                {
                    return true;
                } else
                {
                    throw new DatabaseException("The order with userID = " + t.getEntity().getUserId() + " and created: " + t.getEntity().getCreated() + " could not be updated in the database");
                }
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DatabaseException(ex, "Error updating selected order. Something went wrong with the database");
        }
    }

    @Override
    public boolean delete(DBEntity<Order> t) throws DatabaseException
    {
        return false;
    }
}
