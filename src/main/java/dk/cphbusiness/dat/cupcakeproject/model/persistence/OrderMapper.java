package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Order;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

import java.util.List;

public class OrderMapper extends DataMapper<Order> implements IOrderMapper
{
    public OrderMapper(ConnectionPool connectionPool)
    {
        super(connectionPool);
    }

    @Override
    public DBEntity<Order> insert(Order order) throws DatabaseException
    {
        return null;
    }

    @Override
    public List<DBEntity<Order>> getAll() throws DatabaseException
    {
        return null;
    }

    @Override
    public DBEntity<Order> findById(int id) throws DatabaseException
    {
        return null;
    }

    @Override
    public boolean update(DBEntity<Order> t) throws DatabaseException
    {
        return false;
    }

    @Override
    public boolean delete(DBEntity<Order> t) throws DatabaseException
    {
        return false;
    }
}
