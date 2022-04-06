package dk.cphbusiness.dat.cupcakeproject.model.persistence;

public abstract class DataMapper<T> implements IDataMapper<T>
{
    protected ConnectionPool connectionPool;

    public DataMapper(ConnectionPool connectionPool)
    {
        this.connectionPool = connectionPool;
    }
}
