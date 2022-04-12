package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand extends UnprotectedPageCommand
{
    public LoginCommand(String pageName)
    {
        super(pageName);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper userMapper = new UserMapper(connectionPool);

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            DBEntity<User> dbUser = userMapper.login(email, password);

            HttpSession session = request.getSession();
            session.setAttribute("user", dbUser);

            String pageToShow = "account-page";
            return new PageDirect(RedirectType.REDIRECT, pageToShow);
        }
        catch (DatabaseException ex)
        {
            request.setAttribute("error", "Wrong username or password!");
            return new PageDirect(RedirectType.DEFAULT, "login-page");
        }
    }
}
