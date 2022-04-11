package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.control.webType.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webType.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends UnprotectedPageCommand
{
    public LogoutCommand(String pageName)
    {
        super(pageName);
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return new PageDirect(RedirectType.REDIRECT_INDICATOR, request.getContextPath());
    }
}
