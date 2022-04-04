package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

public interface IUserMapper
{
    public User login(String email, String kodeord) throws DatabaseException;
    public User createUser(String username, String password, String role) throws DatabaseException;
}
