package dk.cphbusiness.dat.cupcakeproject.control.filters;

import dk.cphbusiness.dat.cupcakeproject.control.commands.Command;
import dk.cphbusiness.dat.cupcakeproject.control.commands.CommandController;
import dk.cphbusiness.dat.cupcakeproject.control.commands.ProtectedPageCommand;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter
{
    private enum FailingStrategy
    {
        REDIRECT_TO_LOGIN,
        HARD_ERROR
    }

    private ServletContext context;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String servletPath = req.getServletPath();
        if (servletPath != null && servletPath.equals("/fc"))
        {
            Command command = CommandController.getInstance().fromPath(req);
            HttpSession session = req.getSession(false);
            if (command instanceof ProtectedPageCommand protectedPageCommand)
            {
                Role roleFromCommand = protectedPageCommand.getRole();
                if (session == null || session.getAttribute("user") == null)
                {
                    handleIllegalAccess(
                            req,
                            res,
                            FailingStrategy.HARD_ERROR,
                            "You are not authenticated. Please login first",
                            401);
                    return;
                } else
                {
                    DBEntity<User> user = (DBEntity<User>) session.getAttribute("user");
                    Role role = user.getEntity().getRole();
                    if (role == null || !role.equals(roleFromCommand) || !role.equals(Role.ADMIN))
                    {
                        handleIllegalAccess(
                                req,
                                res,
                                FailingStrategy.REDIRECT_TO_LOGIN,
                                "Attempt to call a resource you are not authorized to view ",
                                403);
                        return;
                    }
                }
            }
        }

        //Prevents users, who has logged out, to use the back-button and see pages they could see, while logged in
//        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//        res.setDateHeader("Expires", 0); // Proxies.

        chain.doFilter(request, response);

    }

    private void handleIllegalAccess(
            HttpServletRequest req,
            HttpServletResponse res,
            FailingStrategy fs,
            String msg, int errCode)
            throws IOException, ServletException
    {
        if (fs == FailingStrategy.REDIRECT_TO_LOGIN)
        {
            req.setAttribute("errormessage", msg);
            req.getRequestDispatcher("/login.jsp").forward(req, res);
        } else
        {
            res.sendError(errCode);
        }
    }

}
