package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.*;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.OrderMapper;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

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
        boolean isPaid = false;
        if (request.getParameter("isPaid").equals("isPaid")) {
            isPaid = true;
        }

        List<CartItem> cart = (List<CartItem>) request.getAttribute("cart");
        for (CartItem item: cart) {

        }

        try{
            Order order = new Order(userId,requestedDelivery);
            order.setIsPaid(isPaid);
            orderMapper.insert(order);

            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "receipt");

        } catch (DatabaseException ex) {
            request.setAttribute("error", "Failed to insert order");
            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "checkout");
        }

    }
}
