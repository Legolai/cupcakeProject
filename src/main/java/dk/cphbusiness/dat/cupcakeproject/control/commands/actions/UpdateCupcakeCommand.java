package dk.cphbusiness.dat.cupcakeproject.control.commands.actions;

import dk.cphbusiness.dat.cupcakeproject.control.commands.pages.ProtectedPageCommand;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponent;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponentType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.CupcakeComponentMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCupcakeCommand extends ProtectedPageCommand {
    public UpdateCupcakeCommand(String pageName) {
        super(pageName, Role.ADMIN);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) {
        CupcakeComponentMapper cupcakeMapper = new CupcakeComponentMapper(connectionPool);

        int id = Integer.parseInt(request.getParameter("updateCupcakeID"));
        String name = request.getParameter("updateCupcakeName");
        int price = Integer.parseInt(request.getParameter("updateCupcakePrice"));
        CupcakeComponentType cupcakeType = CupcakeComponentType.valueOf(request.getParameter("updateCupcakeType").toLowerCase());

        DBEntity<CupcakeComponent> cupcake = new DBEntity<>(id, new CupcakeComponent(cupcakeType, name, price));
        cupcake.setDeleted(false);
        try {
            if (!cupcakeMapper.update(cupcake)) {
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
