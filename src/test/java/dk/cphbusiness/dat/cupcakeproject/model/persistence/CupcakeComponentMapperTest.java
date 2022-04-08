package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponent;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponentType;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CupcakeComponentMapperTest extends DataMapperTest<CupcakeComponent>
{

    private static CupcakeComponentMapper cupcakeComponentMapper;

    @BeforeAll
    public static void setUpClass() {
        DataMapperTest.setUpClass();
        cupcakeComponentMapper = new CupcakeComponentMapper(connectionPool);
    }

    @Test
    @Override
    public void getAll() throws DatabaseException
    {
        assertEquals(6, cupcakeComponentMapper.getAll().size());
        assertEquals(4, cupcakeComponentMapper.insert(createListOfEntities().get(2)).getId());
        assertEquals(7, cupcakeComponentMapper.getAll().size());
    }

    @Override
    public IDataMapper<CupcakeComponent> getDataMapper()
    {
        return cupcakeComponentMapper;
    }

    @Override
    public List<CupcakeComponent> createListOfEntities()
    {
        List<CupcakeComponent> components = new ArrayList<>();

        components.add(new CupcakeComponent(CupcakeComponentType.TOPPING, "Orange", 8));
        components.add(new CupcakeComponent(CupcakeComponentType.BOTTOM, "Almond", 7));
        components.add(new CupcakeComponent(CupcakeComponentType.TOPPING, "Lemon", 8));

        return components;
    }
}
