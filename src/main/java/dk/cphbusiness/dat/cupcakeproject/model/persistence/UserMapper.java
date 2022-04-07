package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserMapper extends DataMapper<User> implements IUserMapper
{


    public UserMapper(ConnectionPool connectionPool)
    {
        super(connectionPool);
    }

    @Override
    public DBEntity<User> insert(User user) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        DBEntity<User> dbUser;

        String sql = "insert into `user` (name, email, phone, password, role, address, balance) values (?,?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPhone());
                ps.setString(4, user.getPassword());
                ps.setString(5, user.getRole().toString());
                ps.setString(6, user.getAddress());
                ps.setInt(7, user.getAccount().getBalance());
                int rowsAffected = ps.executeUpdate();
                ResultSet rs = ps.getResultSet();

                if (rowsAffected == 1)
                {
                    dbUser = new DBEntity<>(rs.getInt("userID"),user);
                } else
                {
                    throw new DatabaseException("The user with email = " + user.getEmail() + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert username into database");
        }
        return dbUser;
    }

    @Override
    public List<DBEntity<User>> getAll() throws DatabaseException
    {
        return null;
    }

    @Override
    public Optional<DBEntity<User>> findById(int id) throws DatabaseException
    {
        return Optional.empty();
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
    public User login(String email, String password) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String name = rs.getString("Name");
                    Role role = rs.getObject("role", Role.class);
                    user = new User(name, email, password, role);
                } else
                {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
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