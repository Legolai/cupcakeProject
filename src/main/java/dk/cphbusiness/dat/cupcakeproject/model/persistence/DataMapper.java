package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

public abstract class DataMapper<T> implements IDataMapper<T>
{
    protected final ConnectionPool connectionPool;

    protected DataMapper(ConnectionPool connectionPool)
    {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean enable(DBEntity<T> t) throws DatabaseException
    {
        t.setDeleted(false);
        return update(t);
    }

    @Override
    public boolean disable(DBEntity<T> t) throws DatabaseException
    {
        t.setDeleted(true);
        return update(t);
    }


}
