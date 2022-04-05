package dk.cphbusiness.dat.cupcakeproject.model.entities;

public class CupcakeComponent
{
    private final CupcakeComponentType componentType;
    private final String componentName;
    private final int componentPrice;

    public CupcakeComponent(CupcakeComponentType componentType, String componentName, int componentPrice)
    {
        this.componentType = componentType;
        this.componentName = componentName;
        this.componentPrice = componentPrice;
    }

    public CupcakeComponentType getComponentType()
    {
        return componentType;
    }

    public String getComponentName()
    {
        return componentName;
    }

    public int getComponentPrice()
    {
        return componentPrice;
    }
}
