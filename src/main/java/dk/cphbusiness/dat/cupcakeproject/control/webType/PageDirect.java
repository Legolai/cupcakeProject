package dk.cphbusiness.dat.cupcakeproject.control.webType;

public class PageDirect
{
    public final RedirectType redirectType;
    public final String pageName;

    public PageDirect(RedirectType redirectType, String pageName)
    {
        this.redirectType = redirectType;
        this.pageName = pageName;
    }
}
