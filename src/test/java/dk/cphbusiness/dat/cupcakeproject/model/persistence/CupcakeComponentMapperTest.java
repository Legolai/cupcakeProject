package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponent;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponentType;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

public class CupcakeComponentMapperTest extends DataMapperTest<CupcakeComponent>
{

    private static CupcakeComponentMapper cupcakeComponentMapper;

    @BeforeAll
    public static void setUpClass() {
        DataMapperTest.setUpClass();
        cupcakeComponentMapper = new CupcakeComponentMapper(connectionPool);
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
        components.add(new CupcakeComponent(CupcakeComponentType.BUTTOM, "Almond", 7));
        components.add(new CupcakeComponent(CupcakeComponentType.TOPPING, "Lemon", 8));

        return components;
    }
}
