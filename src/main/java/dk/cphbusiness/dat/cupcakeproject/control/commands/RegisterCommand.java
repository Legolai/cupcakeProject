package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterCommand extends UnprotectedPageCommand
{
    public RegisterCommand(String pageName)
    {
        super(pageName);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper userMapper = new UserMapper(connectionPool);

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmedPassword = request.getParameter("confirmedPassword");


        if(!password.equals(confirmedPassword)){
            request.setAttribute("error", "Confirmed password does not match!");
            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "createAccount-page");
        }

        try{
            User user = new User(name, email, password, Role.CUSTOMER);
            DBEntity<User> dbUser = userMapper.insert(user);

            HttpSession session = request.getSession();

            session.setAttribute("user", dbUser);

            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "index");


        } catch (DatabaseException ex) {
            request.setAttribute("error", "Email is already in use!");
            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "register");
        }

    }
}
