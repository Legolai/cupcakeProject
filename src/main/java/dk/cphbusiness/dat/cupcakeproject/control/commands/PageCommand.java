package dk.cphbusiness.dat.cupcakeproject.control.commands;

public abstract class PageCommand implements Command
{
    private final String pageName;

    protected PageCommand(String pageName)
    {
        this.pageName = pageName;
    }

    public String getPageName()
    {
        return pageName;
    }
}
