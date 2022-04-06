package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.Order;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

public class OrderMapperTest extends DataMapperTest<Order>
{
    private static OrderMapper orderMapper;

    @BeforeAll
    public static void setUpClass() {
        DataMapperTest.setUpClass();
        orderMapper = new OrderMapper(connectionPool);
    }


    @Override
    public IDataMapper<Order> getDataMapper()
    {
        return orderMapper;
    }

    @Override
    public List<Order> createListOfEntities()
    {
        return null;
    }
}
