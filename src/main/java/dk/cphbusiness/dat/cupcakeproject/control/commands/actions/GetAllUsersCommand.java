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
import java.util.List;

public class GetAllUsersCommand extends ProtectedPageCommand
{
    public GetAllUsersCommand(String pageName)
    {
        super(pageName, Role.ADMIN);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) {
        UserMapper userMapper = new UserMapper(connectionPool);


        try{
            List<DBEntity<User>> users = userMapper.getAll();

            HttpSession session = request.getSession();

            session.setAttribute("allUsers", users);

            return new PageDirect(RedirectType.DEFAULT, "admin");


        } catch (DatabaseException ex) {
            request.setAttribute("error", "Could not get all users!");
            return new PageDirect(RedirectType.DEFAULT, "admin");
        }

    }
}
