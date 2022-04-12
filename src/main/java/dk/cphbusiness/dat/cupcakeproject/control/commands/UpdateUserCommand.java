package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Account;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateUserCommand extends ProtectedPageCommand
{
    public UpdateUserCommand(String pageName, Role role)
    {
        super(pageName, role);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper userMapper = new UserMapper(connectionPool);

        DBEntity<User> user = (DBEntity<User>) request.getAttribute("user");

        String pageName;
        if (user.getEntity().getRole().equals(Role.CUSTOMER)) {
            pageName = "account-page";
        } else {
            pageName = "admin-page";
        }

        try{
            if (userMapper.update(user)) {
                HttpSession session = request.getSession();

                session.setAttribute("user", user);

                return new PageDirect(RedirectType.DEFAULT_REDIRECT, pageName);
            } else {
                request.setAttribute("error", "Update of user could not be completed");
                return new PageDirect(RedirectType.DEFAULT_REDIRECT, pageName);
            }

        } catch (DatabaseException ex) {
            // not needed right?
        }   // should I repeat down here?
        request.setAttribute("error", "Update of user could not be completed");
        return new PageDirect(RedirectType.DEFAULT_REDIRECT, pageName);
    }
}
