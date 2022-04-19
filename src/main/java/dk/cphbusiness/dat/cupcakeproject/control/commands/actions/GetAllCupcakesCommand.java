package dk.cphbusiness.dat.cupcakeproject.control.commands.actions;

import dk.cphbusiness.dat.cupcakeproject.control.commands.pages.UnprotectedPageCommand;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webtypes.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponent;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.CupcakeComponentMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetAllCupcakesCommand extends UnprotectedPageCommand {
    public GetAllCupcakesCommand(String pageName) {
        super(pageName);
    }

    @Override       // can't get for a list on admin page
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) {
        CupcakeComponentMapper cupcakeMapper = new CupcakeComponentMapper(connectionPool);
        String goToPage;

        HttpSession session = request.getSession();
        boolean temp = (boolean) session.getAttribute("adminGetAllCupcakes");
        if (temp) {
            goToPage = "admin";
        } else {
            goToPage = "cupcakes";
        }

        try {
            List<DBEntity<CupcakeComponent>> cupcakes = cupcakeMapper.getAll();

            session.setAttribute("allCupcakes", cupcakes);
            if (goToPage.equals("admin")) {
                session.setAttribute("adminGetAllCupcakes", false);
            }

            return new PageDirect(RedirectType.DEFAULT, goToPage);


        }
        catch (DatabaseException ex) {
            request.setAttribute("error", "Could not get all cupcakes!");
            return new PageDirect(RedirectType.DEFAULT, goToPage);
        }
    }
}




