package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.control.webType.PageDirect;
import dk.cphbusiness.dat.cupcakeproject.control.webType.RedirectType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProtectedPageCommand extends PageCommand
{
    private final Role role;

    public ProtectedPageCommand(String pageName, Role role)
    {
        super(pageName);
        this.role = role;
    }

    @Override
    public PageDirect execute(HttpServletRequest request, HttpServletResponse response, ConnectionPool connectionPool) throws DatabaseException
    {
        return new PageDirect(RedirectType.STAY, getPageName());
    }

    public Role getRole()
    {
        return role;
    }
}
