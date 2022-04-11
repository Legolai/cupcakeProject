package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.control.webType.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webType.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnprotectedPageCommand extends PageCommand
{
    public UnprotectedPageCommand(String pageName)
    {
        super(pageName);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        return new PageDirect(RedirectType.STAY, getPageName());
    }
}
