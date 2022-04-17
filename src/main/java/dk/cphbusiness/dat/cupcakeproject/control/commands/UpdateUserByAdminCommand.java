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
import java.util.Optional;

public class UpdateUserByAdminCommand extends ProtectedPageCommand
{
    public UpdateUserByAdminCommand(String pageName)
    {
        super(pageName, Role.ADMIN);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper userMapper = new UserMapper(connectionPool);


        Optional<DBEntity<User>> userFromDB = userMapper.findById(Integer.parseInt(request.getParameter("updateUserID")));
        DBEntity<User> dbUser = userFromDB.get();
        int bal = dbUser.getEntity().getAccount().getBalance();
        dbUser.getEntity().getAccount().withdraw(bal);
        dbUser.getEntity().getAccount().deposit(Integer.parseInt(request.getParameter("updateUserBalance")));

        try{
            if (userMapper.update(dbUser)) {

                return new PageDirect(RedirectType.DEFAULT_REDIRECT, "admin");
            } else {
                request.setAttribute("error", "Update of user could not be completed");
                return new PageDirect(RedirectType.DEFAULT_REDIRECT, "admin");
            }

        } catch (DatabaseException ex) {
            // not needed right?
        }   // should I repeat down here?
        request.setAttribute("error", "Update of user could not be completed");
        return new PageDirect(RedirectType.DEFAULT_REDIRECT, "admin");
    }
}
