package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

import java.util.List;

public class UserMapper implements IUserMapper
{

    @Override
    public DBEntity<User> insert(User user) throws DatabaseException
    {
        return null;
    }

    @Override
    public List<DBEntity<User>> getAll() throws DatabaseException
    {
        return null;
    }

    @Override
    public DBEntity<User> findById(int id) throws DatabaseException
    {
        return null;
    }

    @Override
    public boolean update(DBEntity<User> t) throws DatabaseException
    {
        return false;
    }

    @Override
    public boolean delete(DBEntity<User> t) throws DatabaseException
    {
        return false;
    }

    @Override
    public User login(String email, String kodeord) throws DatabaseException
    {
        return null;
    }

}


//Logger.getLogger("web").log(Level.INFO, "");
//
//        User user = null;
//
//        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
//
//        try (Connection connection = connectionPool.getConnection())
//        {
//            try (PreparedStatement ps = connection.prepareStatement(sql))
//            {
//                ps.setString(1, username);
//                ps.setString(2, password);
//                ResultSet rs = ps.executeQuery();
//                if (rs.next())
//                {
//                    String role = rs.getString("role");
//                    user = new User(username, password, role);
//                } else
//                {
//                    throw new DatabaseException("Wrong username or password");
//                }
//            }
//        } catch (SQLException ex)
//        {
//            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
//        }
//        return user;