package dk.cphbusiness.dat.cupcakeproject.control.listeners;

import dk.cphbusiness.dat.cupcakeproject.model.config.ApplicationStart;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponent;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.exceptions.DatabaseException;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.CupcakeComponentMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class CupcakeComponentListener implements ServletContextListener, ServletContextAttributeListener {
    private ConnectionPool connectionPool;

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        connectionPool = ApplicationStart.getConnectionPool();
        CupcakeComponentMapper mapper = new CupcakeComponentMapper(connectionPool);
        ServletContext context = contextEvent.getServletContext();
        updateCupcakeComponentContext(mapper, context);
        Logger.getLogger("web").log(Level.INFO, "Cupcake components has been loaded");
    }

    private void updateCupcakeComponentContext(CupcakeComponentMapper mapper, ServletContext context) {
        try {
            List<DBEntity<CupcakeComponent>> toppings = mapper.getSelection("toppings");
            List<DBEntity<CupcakeComponent>> bottoms = mapper.getSelection("bottoms");
            context.setAttribute("cupcakeComponentUpdate", false);
            context.setAttribute("toppings", toppings);
            context.setAttribute("bottoms", bottoms);
        }
        catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent contextEvent) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent contextEvent) {
        /* This method is called when an attribute is added to servlet context. */
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent contextEvent) {
        /* This method is called when an attribute is removed from servlet context. */
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent contextEvent) {
        /* This method is called when an attribute is replaced in servlet context. */
        Logger.getLogger("web").log(Level.INFO, "Cupcake components trigger has been activated");
        ServletContext context = contextEvent.getServletContext();
        boolean shouldUpdate = (boolean) context.getAttribute("cupcakeComponentUpdate");
        if (shouldUpdate) {
            CupcakeComponentMapper mapper = new CupcakeComponentMapper(connectionPool);
            updateCupcakeComponentContext(mapper, context);
            Logger.getLogger("web").log(Level.INFO, "Cupcake components has been updated");
        }
    }
}
