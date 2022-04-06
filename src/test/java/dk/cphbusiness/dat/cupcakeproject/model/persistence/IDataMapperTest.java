package dk.cphbusiness.dat.cupcakeproject.model.persistence;


import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

interface IDataMapperTest<T>
{
    IDataMapper<T> getDataMapper();

    List<T> createListOfEntities();

    @Test
    default void insert() throws DatabaseException
    {
        IDataMapper<T> dataMapper = getDataMapper();
        T t = createListOfEntities().get(0);
        DBEntity<T> dbEntity = new DBEntity<>(1, t);
        assertEquals(dbEntity, dataMapper.insert(t));
    }

    @Test
    default void getAll() throws DatabaseException
    {
        IDataMapper<T> dataMapper = getDataMapper();
        T t = createListOfEntities().get(0);
        DBEntity<T> dbEntity = new DBEntity<>(1, t);
        assertEquals(dbEntity, dataMapper.insert(t));
    }

    @Test
    default void findById() throws DatabaseException
    {
        IDataMapper<T> dataMapper = getDataMapper();
        T t = createListOfEntities().get(0);
        DBEntity<T> dbEntity = new DBEntity<>(1, t);
        assertEquals(dbEntity, dataMapper.insert(t));
    }

    @Test
    default void update() throws DatabaseException
    {
        IDataMapper<T> dataMapper = getDataMapper();
        T t = createListOfEntities().get(0);
        DBEntity<T> dbEntity = new DBEntity<>(1, t);
        assertEquals(dbEntity, dataMapper.insert(t));
    }

    @Test
    default void delete() throws DatabaseException
    {
        IDataMapper<T> dataMapper = getDataMapper();
        T t = createListOfEntities().get(0);
        DBEntity<T> dbEntity = new DBEntity<>(1, t);
        assertEquals(dbEntity, dataMapper.insert(t));
    }



}