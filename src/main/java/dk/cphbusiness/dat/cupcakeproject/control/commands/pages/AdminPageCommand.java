package dk.cphbusiness.dat.cupcakeproject.control.commands.pages;

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

public class AdminPageCommand extends ProtectedPageCommand
{
    public AdminPageCommand(String pageName)
    {
        super(pageName, Role.ADMIN);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper userMapper = new UserMapper(connectionPool);
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        CupcakeComponentMapper cupcakeMapper = new CupcakeComponentMapper(connectionPool);


        try{
            List<DBEntity<User>> users = userMapper.getAll();
            List<DBEntity<Order>> orders = orderMapper.getAll();
            List<DBEntity<CupcakeComponent>> cupcakes = cupcakeMapper.getAll();

            HttpSession session = request.getSession();

            session.setAttribute("allUsers", users);
            session.setAttribute("allOrders", orders);
            session.setAttribute("allCupcakes", cupcakes);

            return new PageDirect(RedirectType.DEFAULT, getPageName());


        } catch (DatabaseException ex) {
            request.setAttribute("error", "Could not get all users!");
            return new PageDirect(RedirectType.DEFAULT, getPageName());
        }

    }
}
