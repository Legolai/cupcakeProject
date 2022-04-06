package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponent;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

import java.util.List;
import java.util.Optional;

public class CupcakeComponentMapper implements ICupcakeComponentMapper
{
    @Override
    public DBEntity<CupcakeComponent> insert(CupcakeComponent cupcakeComponent) throws DatabaseException
    {
        return null;
    }

    @Override
    public List<DBEntity<CupcakeComponent>> getAll() throws DatabaseException
    {
        return null;
    }

    @Override
    public Optional<DBEntity<CupcakeComponent>> findById(int id) throws DatabaseException
    {
        return Optional.empty();
    }

    @Override
    public boolean update(DBEntity<CupcakeComponent> t) throws DatabaseException
    {
        return false;
    }

    @Override
    public boolean delete(DBEntity<CupcakeComponent> t) throws DatabaseException
    {
        return false;
    }
}
