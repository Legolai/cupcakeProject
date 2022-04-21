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
import java.util.Optional;

public class UpdateUserByAdminCommand extends ProtectedPageCommand {
    public UpdateUserByAdminCommand(String pageName) {
        super(pageName, Role.ADMIN);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) {
        UserMapper userMapper = new UserMapper(connectionPool);

        try {
            Optional<DBEntity<User>> userFromDB = userMapper.findById(Integer.parseInt(request.getParameter("updateUserID")));
            DBEntity<User> dbUser = userFromDB.orElseThrow();
            int bal = dbUser.getEntity().getAccount().getBalance();
            dbUser.getEntity().getAccount().withdraw(bal);
            dbUser.getEntity().getAccount().deposit(Integer.parseInt(request.getParameter("updateUserBalance")));
            if (!userMapper.update(dbUser)) {
                request.setAttribute("error", "Update of user could not be completed");
            }
            return new PageDirect(RedirectType.DEFAULT, "admin");
        }
        catch (DatabaseException ex) {
            // not needed right?
        }   // should I repeat down here?
        request.setAttribute("error", "Update of user could not be completed");
        return new PageDirect(RedirectType.DEFAULT, "admin");
    }
}
