package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.*;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.CupcakeComponentMapper;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class InsertCupcakeCommand extends ProtectedPageCommand
{
    public InsertCupcakeCommand(String pageName)
    {
        super(pageName, Role.ADMIN);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        CupcakeComponentMapper cupcakeMapper = new CupcakeComponentMapper(connectionPool);

        String name = request.getParameter("newCupcakeName");
        int price = Integer.parseInt(request.getParameter("newCupcakePrice"));
        CupcakeComponentType cupcakeType = CupcakeComponentType.valueOf(request.getParameter("newCupcakeType"));
        if (cupcakeType.equals(CupcakeComponentType.TOPPING)) {
            cupcakeType = CupcakeComponentType.TOPPING;
        } else {
            cupcakeType = CupcakeComponentType.BOTTOM;
        }

        try{
            CupcakeComponent cupcake = new CupcakeComponent(cupcakeType, name, price);
            cupcakeMapper.insert(cupcake);

                // these 2 not needed, just call get all to refresh?
//            HttpSession session = request.getSession();
//            session.setAttribute("user", dbUser);

            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "admin");


        } catch (DatabaseException ex) {
            request.setAttribute("error", "New cupcake component could not be inserted!");
            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "admin");
        }

    }
}
