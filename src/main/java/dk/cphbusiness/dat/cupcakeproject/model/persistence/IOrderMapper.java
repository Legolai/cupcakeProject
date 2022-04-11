package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Order;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

import java.util.List;
import java.util.Optional;

public interface IOrderMapper extends IDataMapper<Order>
{
    Optional<List<DBEntity<Order>>> findByUserId(int userId) throws DatabaseException;
}
