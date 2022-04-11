package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponent;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponentType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CupcakeComponentMapper extends DataMapper<CupcakeComponent> implements ICupcakeComponentMapper
{
    public CupcakeComponentMapper(ConnectionPool connectionPool)
    {
        super(connectionPool);
    }

    @Override
    public DBEntity<CupcakeComponent> insert(CupcakeComponent cupcakeComponent) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        DBEntity<CupcakeComponent> dbCupcake;
        String sql = bottomOrTopping(cupcakeComponent, "insert");

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1, cupcakeComponent.getComponentName());
                ps.setInt(2, cupcakeComponent.getComponentPrice());
                int rowsAffected = ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                if (rowsAffected == 1)
                {
                    rs.next();
                    int id = rs.getInt(1);
                    dbCupcake = new DBEntity<>(id, cupcakeComponent);
                } else
                {
                    throw new DatabaseException("The cupcake " + cupcakeComponent.getComponentType() + " with name " + cupcakeComponent.getComponentName() + " and price " + cupcakeComponent.getComponentPrice() + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert cupcakecomponent into database");
        }
        return dbCupcake;
    }

    @Override
    public List<DBEntity<CupcakeComponent>> getAll() throws DatabaseException
    {
        return getSelection("all");
    }

    public List<DBEntity<CupcakeComponent>> getSelection(String selection) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        List<DBEntity<CupcakeComponent>> cupcakeList = new ArrayList<>();
        String sql;

        if (selection.equals("toppings") || selection.equals("all"))
        {
            sql = "SELECT * FROM cupcaketopping order by toppingName;";
            cupcakeList.addAll(getToppingsOrBottoms(sql, CupcakeComponentType.TOPPING));
        }
        if (selection.equals("bottoms") || selection.equals("all"))
        {
            sql = "SELECT * FROM cupcakebottom order by bottomName;";
            cupcakeList.addAll(getToppingsOrBottoms(sql, CupcakeComponentType.BOTTOM));
        }
        return cupcakeList;
    }

    private List<DBEntity<CupcakeComponent>> getToppingsOrBottoms(String sql, CupcakeComponentType type) throws DatabaseException
    {
        List<DBEntity<CupcakeComponent>> cupcakeList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                CupcakeComponent cupcake;
                DBEntity<CupcakeComponent> dbCupcake;
                while (rs.next())
                {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    int price = rs.getInt("price");
                    Boolean isDeleted = rs.getBoolean("isDeleted");

                    cupcake = new CupcakeComponent(type, name, price);
                    dbCupcake = new DBEntity<>(id, cupcake);
                    dbCupcake.setDeleted(isDeleted);

                    cupcakeList.add(dbCupcake);
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error cupcakes. Something went wrong with the database");
        }
        return cupcakeList;
    }

    @Override       //find by id not that useful
    public Optional<DBEntity<CupcakeComponent>> findById(int id) throws DatabaseException
    {
        return Optional.empty();
    }

    @Override
    public boolean update(DBEntity<CupcakeComponent> t) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        CupcakeComponent cupcake = t.getEntity();
        String sql;

        if (cupcake.getComponentType().equals(CupcakeComponentType.TOPPING))
        {
            sql = "UPDATE cupcaketopping" +
                    " SET toppingName = ?, price = ?, isDeleted = ?" +
                    " WHERE toppingID = ?;";
        } else
        {
            sql = "UPDATE cupcakebottom" +
                    " SET bottomName = ?, price = ?, isDeleted = ?" +
                    " WHERE bottomID = ?;";
        }

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, cupcake.getComponentName());
                ps.setInt(2, cupcake.getComponentPrice());
                ps.setBoolean(3, t.getDeleted());
                ps.setInt(4, t.getId());

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1)
                {
                    return true;
                } else
                {
                    throw new DatabaseException("The cupcake with name = " + cupcake.getComponentName() + " could not be updated in the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error updating selected cupcakeComponent. Something went wrong with the database");
        }
    }

    @Override       //is this needed? can just update and set isDeleted to 1 for true
    public boolean delete(DBEntity<CupcakeComponent> t) throws DatabaseException
    {
        return false;
    }


    private String bottomOrTopping(CupcakeComponent cupcakeComponent, String method)
    {
        String sql;
        if (method.equals("insert"))
        {
            if (cupcakeComponent.getComponentType().equals(CupcakeComponentType.TOPPING))
            {
                sql = "insert into cupcaketopping (toppingName, price) values (?,?)";
            } else
            {
                sql = "insert into cupcakebottom (bottomName, price) values (?,?)";
            }
        } else
        {
            if (cupcakeComponent.getComponentType().equals(CupcakeComponentType.TOPPING))
            {
                sql = "SELECT * FROM cupcaketopping order by toppingName;";
            } else
            {
                sql = "SELECT * FROM cupcakebottom order by bottomName;";
            }
        }

        return sql;
    }

}
