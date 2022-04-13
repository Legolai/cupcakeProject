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
{                                                               // can admin still change users?
    public UpdateUserCommand(String pageName)
    {
        super(pageName, Role.CUSTOMER);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper userMapper = new UserMapper(connectionPool);

        HttpSession session = request.getSession();
        DBEntity<User> user = (DBEntity<User>) session.getAttribute("updateUser");
        String goToPage;
        if (user.getEntity().getRole().equals(Role.CUSTOMER)) {
            goToPage = "account";
            System.out.println("hello from role customer updateUserCommand");
        } else {
            goToPage = "admin";
        }
        user.getEntity().setName(request.getParameter("updateName"));
        user.getEntity().setEmail(request.getParameter("updateEmail"));
        user.getEntity().setAddress(request.getParameter("updateAddress"));


        try{
            if (userMapper.update(user)) {
                //HttpSession session = request.getSession();

                session.setAttribute("user", user);

                return new PageDirect(RedirectType.DEFAULT_REDIRECT, goToPage);
            } else {
                request.setAttribute("error", "Update of user could not be completed");
                return new PageDirect(RedirectType.DEFAULT_REDIRECT, goToPage);
            }

        } catch (DatabaseException ex) {
            // not needed right?
        }   // should I repeat down here?
        request.setAttribute("error", "Update of user could not be completed");
        return new PageDirect(RedirectType.DEFAULT_REDIRECT, goToPage);
    }
}
