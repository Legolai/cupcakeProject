package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Order;
import dk.cphbusiness.dat.cupcakeproject.model.entities.OrderDetail;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderMapperTest extends DataMapperTest<Order> {
    private static OrderMapper orderMapper;

    @BeforeAll
    public static void setUpClass() {
        DataMapperTest.setUpClass();
        orderMapper = new OrderMapper(connectionPool);
    }


    @Override
    public IDataMapper<Order> getDataMapper() {
        return orderMapper;
    }

    @Override
    public List<Order> createListOfEntities() {
        List<Order> orders = new ArrayList<>();

        Order order1 = new Order(1, LocalDateTime.now());
        List<DBEntity<OrderDetail>> orderDetails = new ArrayList<>();
        orderDetails.add(new DBEntity<>(0, new OrderDetail(1, 1, 3)));
        orderDetails.add(new DBEntity<>(0, new OrderDetail(1, 2, 3)));
        orderDetails.add(new DBEntity<>(0, new OrderDetail(3, 1, 3)));
        order1.setOrderDetails(orderDetails);
        orders.add(order1);

        Order order2 = new Order(2, LocalDateTime.now());
        orderDetails = new ArrayList<>();
        orderDetails.add(new DBEntity<>(0, new OrderDetail(1, 1, 3)));
        orderDetails.add(new DBEntity<>(0, new OrderDetail(1, 2, 3)));
        orderDetails.add(new DBEntity<>(0, new OrderDetail(3, 1, 3)));
        order2.setOrderDetails(orderDetails);
        orders.add(order2);

        Order order3 = new Order(3, LocalDateTime.now());
        orderDetails = new ArrayList<>();
        orderDetails.add(new DBEntity<>(0, new OrderDetail(1, 1, 3)));
        orderDetails.add(new DBEntity<>(0, new OrderDetail(1, 2, 3)));
        orderDetails.add(new DBEntity<>(0, new OrderDetail(3, 1, 3)));
        order3.setOrderDetails(orderDetails);
        orders.add(order3);

        return orders;
    }

    @Test
    void findByUserId() throws DatabaseException {
        Order t = createListOfEntities().get(2);
        DBEntity<Order> dbEntity = new DBEntity<>(4, t);
        assertTrue(orderMapper.findByUserId(2).isPresent());
        assertEquals(dbEntity, orderMapper.insert(t));
        assertTrue(orderMapper.findByUserId(dbEntity.getEntity().getUserId()).isPresent());
    }

    @Override
    @Test
    public void update() throws DatabaseException {
        Order t = createListOfEntities().get(0);
        DBEntity<Order> dbEntity = orderMapper.insert(t);
        dbEntity.getEntity().setIsPaid(true);
        assertTrue(orderMapper.update(dbEntity));
    }

    @Test
    public void getOrder() throws DatabaseException {
        Optional<List<DBEntity<Order>>> optionalDBOrder = orderMapper.findByUserId(1);
        assertTrue(optionalDBOrder.isPresent());

        List<DBEntity<Order>> dbOrders = optionalDBOrder.get();

        DBEntity<Order> dbOrder = dbOrders.get(0);
        assertEquals(3, dbOrder.getEntity().getOrderDetails().size());

//        Order t = createListOfEntities().get(0);
//        DBEntity<Order> dbEntity = orderMapper.insert(t);
//        Order t2 = createListOfEntities().get(1);
//        DBEntity<Order> dbEntity2 = orderMapper.insert(t2);
//
//        List<DBEntity<Order>> lis


    }


}
