package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.Account;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

        String sql = "insert into user (name, email, phone, password, role, address, balance) values (?,?,?,?,?,?,?)";
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

                if (rowsAffected == 1)
                {
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    int id = rs.getInt(1);
                    dbUser = new DBEntity<>(id,user);

                } else
                {
                    throw new DatabaseException("The user with email = " + user.getEmail() + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert user into database");
        }
        return dbUser;
    }

    @Override
    public List<DBEntity<User>> getAll() throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        List<DBEntity<User>> userList = new ArrayList<>();

        String sql = "SELECT * FROM User order by 'name';";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                User newUser;
                Account newAccount;
                DBEntity<User> user;
                while (rs.next())
                {
                    int userID = rs.getInt("userID");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    String address = rs.getString("address");
                    int balance = rs.getInt("balance");

                    newAccount = new Account(balance);
                    newUser = new User(name,email,phone,password,Role.valueOf(role),address,newAccount);
                    user = new DBEntity<>(userID, newUser);

                    userList.add(user);
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error getting all users. Something went wrong with the database");
        }
        return userList;
    }

    @Override
    public Optional<DBEntity<User>> findById(int id) throws DatabaseException
    {
        Optional<DBEntity<User>> optionalDBEntity = Optional.empty();
        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "SELECT * FROM User WHERE userID = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                User newUser;
                Account newAccount;
                DBEntity<User> user;
                if (rs.next())
                {
                    int userID = rs.getInt("userID");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    String address = rs.getString("address");
                    int balance = rs.getInt("balance");

                    newAccount = new Account(balance);
                    newUser = new User(name,email,phone,password,Role.valueOf(role),address,newAccount);
                    user = new DBEntity<>(userID, newUser);

                    optionalDBEntity = Optional.of(user);
                } else
                {
                    throw new DatabaseException("User with id: "+id+" was not found");
                }
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
//            throw new DatabaseException(ex, "Error finding user by id. Something went wrong with the database");
        }
        return optionalDBEntity;
    }

    @Override
    public boolean update(DBEntity<User> t) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "UPDATE `User`" +
                " SET `name` = ?, `email` = ?, `phone` = ?, `password` = ?, `role` = ?, `address` = ?, `balance` = ?" +
                " WHERE userID = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                User user = t.getEntity();
                ps.setString(1,user.getName());
                ps.setString(2,user.getEmail());
                ps.setString(3,user.getPhone());
                ps.setString(4,user.getPassword());
                ps.setString(5,user.getRole().name());
                ps.setString(6,user.getAddress());
                ps.setInt(7, user.getAccount().getBalance());
                ps.setInt(8, t.getId());

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1)
                {
                    return true;
                } else
                {
                    throw new DatabaseException("The user with email = " + t.getEntity().getEmail() + " could not be updated in the database");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error updating selected user. Something went wrong with the database");
        }
    }

    @Override
    public boolean delete(DBEntity<User> t) throws DatabaseException
    {
        return false;
    }

    @Override
    public DBEntity<User> login(String email, String password) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        DBEntity<User> user = null;

        String sql = "SELECT * FROM User WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                Account newAccount;
                User userEntity;
                if (rs.next())
                {
                    int userID = rs.getInt("userID");
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String role = rs.getString("role");
                    String address = rs.getString("address");
                    int balance = rs.getInt("balance");

                    newAccount = new Account(balance);
                    userEntity = new User(name,email,phone,password,Role.valueOf(role),address,newAccount);
                    user = new DBEntity<>(userID, userEntity);
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