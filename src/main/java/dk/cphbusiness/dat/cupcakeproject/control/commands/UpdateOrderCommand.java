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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UpdateOrderCommand extends ProtectedPageCommand
{
    public UpdateOrderCommand(String pageName)
    {
        super(pageName, Role.ADMIN);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        OrderMapper orderMapper = new OrderMapper(connectionPool);

        Optional<DBEntity<Order>> orderFromDB = orderMapper.findById(Integer.parseInt(request.getParameter("updateOrderID")));
        DBEntity<Order> order = orderFromDB.get();
        if (request.getParameter("updateShipped").equals("true"))
        {
            order.getEntity().setShipped(LocalDateTime.now());
        } else
        {
            order.getEntity().setShipped(null);
        }
        if (request.getParameter("updateIsPaid").equals("true"))
        {
            order.getEntity().setIsPaid(true);
        } else
        {
            order.getEntity().setIsPaid(false);
        }

        try{
            if (orderMapper.update(order)) {

                return new PageDirect(RedirectType.DEFAULT_REDIRECT, "admin");
            } else {
                request.setAttribute("error", "Update of cupcake could not be completed");
                return new PageDirect(RedirectType.DEFAULT_REDIRECT, "admin");
            }
        } catch (DatabaseException ex) {
            // not needed right?
        }   // should I repeat down here?
        request.setAttribute("error", "Update of cupcake could not be completed");
        return new PageDirect(RedirectType.DEFAULT_REDIRECT, "admin");
    }
}
