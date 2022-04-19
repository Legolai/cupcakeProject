package dk.cphbusiness.dat.cupcakeproject.control.commands.actions;

import dk.cphbusiness.dat.cupcakeproject.control.commands.pages.ProtectedPageCommand;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Order;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.OrderMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;

public class UpdateOrderCommand extends ProtectedPageCommand {
    public UpdateOrderCommand(String pageName) {
        super(pageName, Role.ADMIN);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) {
        OrderMapper orderMapper = new OrderMapper(connectionPool);

        Optional<DBEntity<Order>> orderFromDB = orderMapper.findById(Integer.parseInt(request.getParameter("updateOrderID")));
        DBEntity<Order> order = orderFromDB.orElseThrow();
        if (request.getParameter("updateShipped").equals("true")) {
            order.getEntity().setShipped(LocalDateTime.now());
        } else {
            order.getEntity().setShipped(null);
        }
        order.getEntity().setIsPaid(request.getParameter("updateIsPaid").equals("true"));

        try {
            if (!orderMapper.update(order)) {
                request.setAttribute("error", "Update of cupcake could not be completed");
            }
            return new PageDirect(RedirectType.DEFAULT, "admin");
        }
        catch (DatabaseException ex) {
            // not needed right?
        }   // should I repeat down here?
        request.setAttribute("error", "Update of cupcake could not be completed");
        return new PageDirect(RedirectType.DEFAULT, "admin");
    }
}
