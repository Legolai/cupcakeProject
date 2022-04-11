package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;
import dk.cphbusiness.dat.cupcakeproject.model.persistence.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandController
{
    private static CommandController instance = null;
    private static final Map<String, Command> commands = new HashMap<>();
    private static ConnectionPool connectionPool;

    private CommandController(){
        commands.put("index", new UnprotectedPageCommand("index"));
        commands.put("cupcakes-page", new UnprotectedPageCommand("cupcakes"));
        commands.put("kontakt-page", new UnprotectedPageCommand("kontakt"));
        commands.put("omOs-page", new UnprotectedPageCommand("omOs"));
        commands.put("login-page", new UnprotectedPageCommand("login"));
        commands.put("login-command", new LoginCommand(""));
        commands.put("logout-command", new LogoutCommand(""));
        commands.put("register-page", new UnprotectedPageCommand("register"));
        commands.put("register-command", new RegisterCommand(""));
        commands.put("customer-page", new ProtectedPageCommand("account", Role.CUSTOMER));
        commands.put("employee-page", new ProtectedPageCommand("admin", Role.ADMIN));
    }

    public static CommandController getInstance(){
        if (instance == null) {
            instance = new CommandController();
        }
        return instance;
    }


    public static Command fromPath(
            HttpServletRequest request,
            ConnectionPool connPool)
    {
        String action = request.getPathInfo().replaceAll("^/+", "");

        String msg =  "--> " + action;
        String loggerName = "CommandController";
        Logger.getLogger(loggerName).log(Level.SEVERE, msg);

        if (commands.size() <= 0)
        {
            connectionPool = connPool;
            getInstance();
            msg = "Commands has been created";
            Logger.getLogger(loggerName).log(Level.SEVERE, msg);
        }
        Command command = commands.getOrDefault(action, new UnknownCommand());   // unknowncommand is default
        msg = "Command class" + command.getClass();
        Logger.getLogger(loggerName).log(Level.SEVERE, msg);
        return command;
    }

    public static ConnectionPool getConnectionPool()
    {
        return connectionPool;
    }
}
