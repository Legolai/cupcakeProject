package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Order;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

import java.util.List;
import java.util.Optional;

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
    public Optional<DBEntity<Order>> findById(int id) throws DatabaseException
    {
        return Optional.empty();
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
