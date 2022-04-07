package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponent;
import org.junit.jupiter.api.BeforeAll;

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
        return null;
    }
}
