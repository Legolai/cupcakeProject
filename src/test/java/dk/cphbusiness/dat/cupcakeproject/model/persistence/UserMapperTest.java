package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserMapperTest extends DataMapperTest<User>
{
    private static UserMapper userMapper;

    @BeforeAll
    public static void setUpClass() {
        DataMapperTest.setUpClass();
        userMapper = new UserMapper(connectionPool);
    }


    @Test
    void login() throws DatabaseException
    {
        User expectedUser = new User("user","1234","user", Role.CUSTOMER);
        User actualUser = userMapper.login("user","1234");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void invalidPasswordLogin() throws DatabaseException
    {
        assertThrows(DatabaseException.class, () -> userMapper.login("user","123"));
    }

    @Test
    void invalidUserNameLogin() throws DatabaseException
    {
        assertThrows(DatabaseException.class, () -> userMapper.login("bob","1234"));
    }

    @Test
    void login2() throws DatabaseException
    {
        DBEntity<User> newUser = userMapper.insert(new User("jill", "1234", "user", Role.CUSTOMER));
        User logInUser = userMapper.login("jill","1234");
        User expectedUser = new User("jill", "1234", "user", Role.CUSTOMER);
        assertEquals(expectedUser, newUser.getEntity());
        assertEquals(expectedUser, logInUser);

    }

    @Override
    public IDataMapper<User> getDataMapper()
    {
        return userMapper;
    }

    @Override
    public List<User> createListOfEntities()
    {
        return null;
    }
}