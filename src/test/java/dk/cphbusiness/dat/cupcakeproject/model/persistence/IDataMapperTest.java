package dk.cphbusiness.dat.cupcakeproject.model.persistence;


import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

interface IDataMapperTest<T>
{
    IDataMapper<T> getDataMapper();

    List<T> createListOfEntities();

    @Test
    default void insert() throws DatabaseException
    {
        IDataMapper<T> dataMapper = getDataMapper();
        T t = createListOfEntities().get(0);
        assertEquals(4, dataMapper.insert(t).getId());
    }

    @Test
    default void getAll() throws DatabaseException
    {
        IDataMapper<T> dataMapper = getDataMapper();
        assertEquals(3, dataMapper.getAll().size());
        assertEquals(4, dataMapper.insert(createListOfEntities().get(2)).getId());
        assertEquals(4, dataMapper.getAll().size());
    }

    @Test
    default void findById() throws DatabaseException
    {
        IDataMapper<T> dataMapper = getDataMapper();
        T t = createListOfEntities().get(2);
        DBEntity<T> dbEntity = new DBEntity<>(4, t);
        assertTrue(dataMapper.findById(1).isPresent());
        assertEquals(dbEntity, dataMapper.insert(t));
        assertTrue(dataMapper.findById(dbEntity.getId()).isPresent());
    }

    @Test
    default void update() throws DatabaseException
    {
        IDataMapper<T> dataMapper = getDataMapper();
        T t = createListOfEntities().get(0);
        DBEntity<T> dbEntity = dataMapper.insert(t);
        dbEntity.setDeleted(true);
        assertTrue(dataMapper.update(dbEntity));
    }

    @Test
    default void delete() throws DatabaseException
    {
        IDataMapper<T> dataMapper = getDataMapper();
        T t = createListOfEntities().get(3);
        DBEntity<T> dbEntity = new DBEntity<>(4, t);
        assertEquals(dbEntity, dataMapper.insert(t));
        assertTrue(dataMapper.delete(dbEntity));
        assertFalse(dataMapper.findById(dbEntity.getId()).isPresent());
    }

    @Test
    default void allInOne() throws DatabaseException
    {
        IDataMapper<T> dataMapper = getDataMapper();
        T t = createListOfEntities().get(2);
        DBEntity<T> dbEntity = new DBEntity<>(4, t);
        assertEquals(dbEntity, dataMapper.insert(t));
        dbEntity.setDeleted(true);
        assertTrue(dataMapper.findById(dbEntity.getId()).isPresent());
        assertTrue(dataMapper.update(dbEntity));
        assertTrue(dataMapper.delete(dbEntity));
        assertFalse(dataMapper.findById(dbEntity.getId()).isPresent());
    }


}