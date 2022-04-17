package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.*;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.CupcakeComponentMapper;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.OrderMapper;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.UserMapper;

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
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        HttpSession session = request.getSession();
        DBEntity<User> user = (DBEntity<User>) session.getAttribute("user");

        try{
            Optional<List<DBEntity<Order>>> orders = orderMapper.findByUserId(user.getId());

            session.setAttribute("userOrders", orders.get());

            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "account");


        } catch (DatabaseException ex) {
            request.setAttribute("error", "Could not get all your orders!");
            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "account");
        }

    }
}
