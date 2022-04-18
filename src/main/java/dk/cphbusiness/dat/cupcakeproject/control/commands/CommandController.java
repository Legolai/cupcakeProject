package dk.cphbusiness.dat.cupcakeproject.control.commands;

import dk.cphbusiness.dat.cupcakeproject.control.commands.actions.*;
import dk.cphbusiness.dat.cupcakeproject.control.commands.pages.AccountPageCommand;
import dk.cphbusiness.dat.cupcakeproject.control.commands.pages.AdminPageCommand;
import dk.cphbusiness.dat.cupcakeproject.control.commands.pages.CupcakesPageCommand;
import dk.cphbusiness.dat.cupcakeproject.control.commands.pages.UnprotectedPageCommand;

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
        commands.put("cupcakes-page", new CupcakesPageCommand("cupcakes"));
        commands.put("kontakt-page", new UnprotectedPageCommand("kontakt"));
        commands.put("omOs-page", new UnprotectedPageCommand("omOs"));
        commands.put("login-page", new UnprotectedPageCommand("login"));
        commands.put("cart-page", new UnprotectedPageCommand("cart"));
        commands.put("register-page", new UnprotectedPageCommand("register"));
        commands.put("account-page", new AccountPageCommand("account"));
        commands.put("admin-page", new AdminPageCommand("admin"));

        commands.put("login-command", new LoginCommand(""));
        commands.put("logout-command", new LogoutCommand(""));
        commands.put("register-command", new RegisterCommand(""));
        commands.put("addToCart-command", new AddToCartCommaned("cupcakes"));
        commands.put("removeFromCart-command", new RemoveFromCartCommand("cart"));
        commands.put("getAllUsers-command", new GetAllUsersCommand(""));
        commands.put("updateUser-command", new UpdateUserCommand(""));
        commands.put("insertCupcake-command", new InsertCupcakeCommand(""));
        commands.put("getAllCupcakes-command", new GetAllCupcakesCommand(""));
        commands.put("updateCupcake-command", new UpdateCupcakeCommand(""));
        commands.put("checkout-command", new CheckOutCommand(""));
        commands.put("getAllOrders-command", new GetAllOrders(""));
        commands.put("updateOrder-command", new UpdateOrderCommand(""));
        commands.put("updateUserByAdmin-command", new UpdateUserByAdminCommand(""));
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
