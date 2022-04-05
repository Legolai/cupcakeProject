package dk.cphbusiness.dat.cupcakeproject.model.persistence;


import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

interface IDataMapperTest<T>
{
    IDataMapper<T> createDataMapper();

    List<T> createListOfEntities();

    @Test
    default void insert() throws DatabaseException
    {
        IDataMapper<T> dataMapper = createDataMapper();
        T t = createListOfEntities().get(0);
        DBEntity<T> dbEntity = new DBEntity<>(1, t);
        assertEquals(dbEntity, dataMapper.insert(t));
    }



}