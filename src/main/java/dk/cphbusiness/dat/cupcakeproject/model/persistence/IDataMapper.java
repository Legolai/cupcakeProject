package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

import java.util.List;

public interface IDataMapper <T>
{
    DBEntity<T> insert(T t) throws DatabaseException;
    List<DBEntity<T>> getAll() throws DatabaseException;
    DBEntity<T> findById(int id) throws DatabaseException;
    boolean update(DBEntity<T> t) throws DatabaseException;
    boolean delete(DBEntity<T> t) throws DatabaseException;
}