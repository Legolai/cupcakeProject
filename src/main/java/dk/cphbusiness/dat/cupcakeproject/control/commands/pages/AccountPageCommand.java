package dk.cphbusiness.dat.cupcakeproject.control.commands.pages;

import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Order;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.OrderMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class AccountPageCommand extends ProtectedPageCommand
{
    public AccountPageCommand(String pageName)
    {
        super(pageName, Role.CUSTOMER);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) {
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        HttpSession session = request.getSession();
        DBEntity<User> user = (DBEntity<User>) session.getAttribute("user");

        try{
            Optional<List<DBEntity<Order>>> orders = orderMapper.findByUserId(user.getId());
            orders.ifPresent(dbEntities -> request.setAttribute("userOrders", dbEntities));

            return new PageDirect(RedirectType.DEFAULT, "account");
        } catch (DatabaseException ex) {
            request.setAttribute("error", "Could not get all your orders!");
            return new PageDirect(RedirectType.DEFAULT, "account");
        }

    }
}
