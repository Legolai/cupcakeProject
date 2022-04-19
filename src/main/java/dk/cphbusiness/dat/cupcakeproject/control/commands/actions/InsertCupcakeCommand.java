package dk.cphbusiness.dat.cupcakeproject.control.commands.actions;

import dk.cphbusiness.dat.cupcakeproject.control.commands.pages.ProtectedPageCommand;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponent;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponentType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.CupcakeComponentMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertCupcakeCommand extends ProtectedPageCommand {
    public InsertCupcakeCommand(String pageName) {
        super(pageName, Role.ADMIN);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) {
        CupcakeComponentMapper cupcakeMapper = new CupcakeComponentMapper(connectionPool);

        String name = request.getParameter("newCupcakeName");
        int price = Integer.parseInt(request.getParameter("newCupcakePrice"));
        CupcakeComponentType cupcakeType = CupcakeComponentType.valueOf(request.getParameter("newCupcakeType").toUpperCase());

        try {
            CupcakeComponent cupcake = new CupcakeComponent(cupcakeType, name, price);
            cupcakeMapper.insert(cupcake);

            return new PageDirect(RedirectType.DEFAULT, "admin");


        }
        catch (DatabaseException ex) {
            request.setAttribute("error", "New cupcake component could not be inserted!");
            return new PageDirect(RedirectType.DEFAULT, "admin");
        }

    }
}
