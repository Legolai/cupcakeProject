package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.model.entities.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandController
{
    private static CommandController instance = null;
    private final Map<String, Command> commands = new HashMap<>();

    private CommandController(){
        commands.put("index", new UnprotectedPageCommand("index"));
        commands.put("cupcakes-page", new UnprotectedPageCommand("cupcakes"));
        commands.put("kontakt-page", new UnprotectedPageCommand("kontakt"));
        commands.put("omOs-page", new UnprotectedPageCommand("omOs"));
        commands.put("login-page", new UnprotectedPageCommand("login"));
        commands.put("register-page", new UnprotectedPageCommand("register"));
        commands.put("account-page", new ProtectedPageCommand("account", Role.CUSTOMER));
        commands.put("admin-page", new ProtectedPageCommand("admin", Role.ADMIN));

        commands.put("login-command", new LoginCommand(""));
        commands.put("logout-command", new LogoutCommand(""));
        commands.put("register-command", new RegisterCommand(""));
        commands.put("getAllUsers-command", new GetAllUsersCommand(""));
        commands.put("updateUser-command", new UpdateUserCommand(""));
        commands.put("insertCupcake-command", new InsertCupcakeCommand(""));
        commands.put("getAllCupcakes-command", new GetAllCupcakesCommand(""));
        commands.put("updateCupcake-command", new UpdateCupcakeCommand(""));
        commands.put("insertOrder-command", new InsertOrderCommand(""));
        commands.put("getAllOrders-command", new GetAllOrders(""));
        commands.put("updateOrder-command", new UpdateOrderCommand(""));
    }

    public static CommandController getInstance(){
        if (instance == null) {
            Logger.getLogger("CommandController").log(Level.SEVERE, "Commands has been created");
            instance = new CommandController();
        }
        return instance;
    }


    public Command fromPath(HttpServletRequest request) {
        String route = request.getPathInfo().replaceAll("^/+", "");

        String msg =  "--> " + route;
        String loggerName = "CommandController";
        Logger.getLogger(loggerName).log(Level.SEVERE, msg);

        Command command = commands.getOrDefault(route, new UnknownCommand());   // unknown-command is default
        msg = "Command class" + command.getClass();
        Logger.getLogger(loggerName).log(Level.SEVERE, msg);

        return command;
    }
}
