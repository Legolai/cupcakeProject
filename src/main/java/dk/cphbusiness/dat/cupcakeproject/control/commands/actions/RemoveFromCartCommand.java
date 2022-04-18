package dk.cphbusiness.dat.cupcakeproject.control.commands.actions;

import dk.cphbusiness.dat.cupcakeproject.control.commands.pages.UnprotectedPageCommand;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.services.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveFromCartCommand extends UnprotectedPageCommand
{
    public RemoveFromCartCommand(String pageName) {
        super(pageName);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        HttpSession session = request.getSession();
        String indexToRemoveString = request.getParameter("remove");

        if (indexToRemoveString != null && !indexToRemoveString.equals("")){
            int indexToRemove = Integer.parseInt(indexToRemoveString);
            Cart cart = (Cart) session.getAttribute("cart");
            cart.removeCartItem(indexToRemove);
        }

        return super.execute(request, response, connectionPool);
    }
}
