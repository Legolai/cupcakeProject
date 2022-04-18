package dk.cphbusiness.dat.cupcakeproject.control.commands.actions;

import dk.cphbusiness.dat.cupcakeproject.control.commands.pages.UnprotectedPageCommand;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Order;
import dk.cphbusiness.dat.cupcakeproject.model.entities.OrderDetail;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.OrderMapper;
import dk.cphbusiness.dat.cupcakeproject.model.services.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

public class CheckOutCommand extends UnprotectedPageCommand
{
    public CheckOutCommand(String pageName)
    {
        super(pageName);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        OrderMapper orderMapper = new OrderMapper(connectionPool);

        HttpSession session = request.getSession(false);

        DBEntity<User> userDBEntity = (DBEntity<User>) session.getAttribute("user");

        if(userDBEntity == null){
            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "register");
        }

        LocalDateTime requestedDelivery = LocalDateTime.parse(request.getParameter("requestedDeliveryDate"));

        boolean isPaid = false;

        Cart cart = (Cart) session.getAttribute("cart");

        try{
            Order order = new Order(userDBEntity.getId(), requestedDelivery);
            List<DBEntity<OrderDetail>> orderDetails = cart.getCartItems().stream().map(item -> new DBEntity<>(0, new OrderDetail(item.getTopping().getId(), item.getBottom().getId(),  item.getQuantity(), 0, item.getComments()))).toList();
            order.setIsPaid(isPaid);
            order.setOrderDetails(orderDetails);

            DBEntity<Order> dbOrder = orderMapper.insert(order);
            request.setAttribute("newOrder", dbOrder);
            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "account");

        } catch (DatabaseException ex) {
            request.setAttribute("error", "Failed to insert order");
            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "checkout");
        }

    }
}
