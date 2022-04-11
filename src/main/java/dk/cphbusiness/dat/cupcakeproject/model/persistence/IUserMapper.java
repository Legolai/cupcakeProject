package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IUserMapper extends IDataMapper<User>
{
    DBEntity<User> login(String email, String kodeord) throws DatabaseException;
    DBEntity<User> createUserEntity(ResultSet rs) throws SQLException;
}
