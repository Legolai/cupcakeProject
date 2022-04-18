package dk.cphbusiness.dat.cupcakeproject.control.commands.actions;

import dk.cphbusiness.dat.cupcakeproject.control.commands.pages.UnprotectedPageCommand;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CartItem;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponent;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.services.Cart;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddToCartCommaned extends UnprotectedPageCommand
{
    public AddToCartCommaned(String pageName)
    {
        super(pageName);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        ServletContext context = request.getServletContext();
        HttpSession session = request.getSession(false);

        String toppingIdString = request.getParameter("topping");
        String bottomIdString = request.getParameter("bottom");
        String quantityString = request.getParameter("quantity");

        int toppingId = Integer.parseInt(toppingIdString);
        int bottomId = Integer.parseInt(bottomIdString);
        int quantity = Integer.parseInt(quantityString);

        List<DBEntity<CupcakeComponent>> toppings = (List<DBEntity<CupcakeComponent>>) context.getAttribute("toppings");
        List<DBEntity<CupcakeComponent>> bottoms = (List<DBEntity<CupcakeComponent>>) context.getAttribute("bottoms");

        DBEntity<CupcakeComponent> topping = toppings.stream().filter(item -> item.getId() == toppingId).findFirst().orElseThrow();
        DBEntity<CupcakeComponent> bottom = bottoms.stream().filter(item -> item.getId() == bottomId).findFirst().orElseThrow();

        Cart cart = (Cart) session.getAttribute("cart");

        cart.addCartItem(new CartItem(topping, bottom, quantity, ""));

        return super.execute(request, response, connectionPool);
    }
}
