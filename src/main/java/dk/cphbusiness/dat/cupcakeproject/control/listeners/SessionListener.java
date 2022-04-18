package dk.cphbusiness.dat.cupcakeproject.control.listeners;

import dk.cphbusiness.dat.cupcakeproject.model.services.Cart;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener
{
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent)
    {
        /* Session is created. */
        Logger.getLogger("web").log(Level.INFO, "Session has been created with cart");
        HttpSession session = sessionEvent.getSession();
        session.setAttribute("cart", new Cart());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sessionBindingEvent) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sessionBindingEvent) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sessionBindingEvent) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
