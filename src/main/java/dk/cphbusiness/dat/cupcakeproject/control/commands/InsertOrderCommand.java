package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Order;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.OrderMapper;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class InsertOrderCommand extends UnprotectedPageCommand
{
    public InsertOrderCommand(String pageName)
    {
        super(pageName);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        OrderMapper orderMapper = new OrderMapper(connectionPool);

        int userId = Integer.parseInt(request.getParameter("userID"));
        LocalDateTime requestedDelivery = LocalDateTime.parse(request.getParameter("requestedDeliveryDate"));


        try{
            Order order = new Order(userId,requestedDelivery);
            DBEntity<Order> dbOrder = orderMapper.insert(order);

            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "receipt");

        } catch (DatabaseException ex) {
            request.setAttribute("error", "Failed to insert order");
            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "checkout");
        }

    }
}
