package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponent;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.CupcakeComponentMapper;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetAllCupcakesCommand extends UnprotectedPageCommand
{
    public GetAllCupcakesCommand(String pageName)
    {
        super(pageName);
    }

    @Override       // can't get for a list on admin page
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        CupcakeComponentMapper cupcakeMapper = new CupcakeComponentMapper(connectionPool);

        try{
            List<DBEntity<CupcakeComponent>> cupcakes = cupcakeMapper.getAll();

            HttpSession session = request.getSession();

            session.setAttribute("allCupcakes", cupcakes);

            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "cupcakes-page");


        } catch (DatabaseException ex) {
            request.setAttribute("error", "Could not get all cupcakes!");
            return new PageDirect(RedirectType.DEFAULT_REDIRECT, "cupcakes-page");
        }
    }
}




