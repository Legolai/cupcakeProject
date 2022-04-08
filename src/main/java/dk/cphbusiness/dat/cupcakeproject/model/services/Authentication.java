package dk.cphbusiness.dat.cupcakeproject.model.services;

import dk.cphbusiness.dat.cupcakeproject.model.entities.Access;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authentication
{
    public static Access isRoleAllowed(Role minimumRole, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        DBEntity<User> user = (DBEntity<User>) session.getAttribute("user");

        if (user == null) {
            return Access.INACTIVE;
        }

        Role userRole = user.getEntity().getRole();
        Access access = Access.DENIED;

        if (userRole.equals(minimumRole)){
            access = Access.LIMITED;
            if (minimumRole.equals(Role.ADMIN)){
                access = Access.FULL;
            }
        }

        return access;
    }
}
