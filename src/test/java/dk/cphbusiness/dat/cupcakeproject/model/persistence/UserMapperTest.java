package dk.cphbusiness.dat.cupcakeproject.model.persistence;

import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        User expectedUser = new User("Peter","a@a.dk","1234",Role.CUSTOMER);
        DBEntity<User> actualUser = userMapper.login("a@a.dk","1234");
        assertEquals(expectedUser, actualUser.getEntity());
    }

    @Test
    void invalidPasswordLogin()
    {
        assertThrows(DatabaseException.class, () -> userMapper.login("user","123"));
    }

    @Test
    void invalidUserNameLogin()
    {
        assertThrows(DatabaseException.class, () -> userMapper.login("bob","1234"));
    }

    @Test
    void login2() throws DatabaseException
    {
        DBEntity<User> newUser = userMapper.insert(new User("jill", "1234@gmail.com", "user", Role.CUSTOMER));
        DBEntity<User> logInUser = userMapper.login("1234@gmail.com","user");
        User expectedUser = new User("jill", "1234@gmail.com", "user", Role.CUSTOMER);
        assertEquals(expectedUser, newUser.getEntity());
        assertEquals(expectedUser, logInUser.getEntity());
    }

    @Test
    @Override
    public void update() throws DatabaseException
    {
        User t = createListOfEntities().get(0);
        DBEntity<User> dbEntity = userMapper.insert(t);
        dbEntity.getEntity().setAddress("Abevej 1");
        assertTrue(userMapper.update(dbEntity));
    }

    @Override
    public IDataMapper<User> getDataMapper()
    {
        return userMapper;
    }

    @Override
    public List<User> createListOfEntities()
    {
        List<User> users = new ArrayList<>();

        users.add(new User("Nicolai", "nic@gmail.com", "1234", Role.CUSTOMER));
        users.add(new User("Michael", "mic@gmail.com", "123456", Role.ADMIN));
        users.add(new User("Muneeb", "tid@gmail.com", "dgh", Role.CUSTOMER));

        return users;
    }
}