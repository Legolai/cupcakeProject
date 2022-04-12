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

public class UpdateCupcakeCommand extends ProtectedPageCommand
{
    public UpdateCupcakeCommand(String pageName)
    {
        super(pageName, Role.ADMIN);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        CupcakeComponentMapper cupcakeMapper = new CupcakeComponentMapper(connectionPool);

        DBEntity<CupcakeComponent> cupcake = (DBEntity<CupcakeComponent>) request.getAttribute("updateCupcake");

        try{
            if (cupcakeMapper.update(cupcake)) {
                HttpSession session = request.getSession();

                return new PageDirect(RedirectType.DEFAULT_REDIRECT, "admin-page");
            } else {
                request.setAttribute("error", "Update of cupcake could not be completed");
                return new PageDirect(RedirectType.DEFAULT_REDIRECT, "admin-page");
            }
        } catch (DatabaseException ex) {
            // not needed right?
        }   // should I repeat down here?
        request.setAttribute("error", "Update of cupcake could not be completed");
        return new PageDirect(RedirectType.DEFAULT_REDIRECT, "admin-page");

    }
}