package dk.cphbusiness.dat.cupcakeproject.control.commands.actions;

import dk.cphbusiness.dat.cupcakeproject.control.commands.pages.ProtectedPageCommand;
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

public class UpdateUserCommand extends ProtectedPageCommand
{
    public UpdateUserCommand(String pageName)
    {
        super(pageName, Role.CUSTOMER);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper userMapper = new UserMapper(connectionPool);

        HttpSession session = request.getSession();
        DBEntity<User> user = (DBEntity<User>) session.getAttribute("user");
        DBEntity<User> tempUser = new DBEntity<>(user.getId(), new User(user.getEntity()));

        String goToPage = user.getEntity().getRole().equals(Role.CUSTOMER) ? "account" : "admin";

        tempUser.getEntity().setName(request.getParameter("updateName"));
        tempUser.getEntity().setEmail(request.getParameter("updateEmail"));
        tempUser.getEntity().setAddress(request.getParameter("updateAddress"));


        try{
            if (userMapper.update(tempUser)) {
                session.setAttribute("user", tempUser);
            } else {
                request.setAttribute("error", "Update of user could not be completed");
            }
        } catch (DatabaseException ex) {
            request.setAttribute("error", "Update of user could not be completed");
        }
        return new PageDirect(RedirectType.DEFAULT, goToPage);
    }
}
