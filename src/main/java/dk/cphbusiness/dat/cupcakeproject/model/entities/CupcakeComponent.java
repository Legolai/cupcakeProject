package dk.cphbusiness.dat.cupcakeproject.model.entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof CupcakeComponent)) return false;
        CupcakeComponent that = (CupcakeComponent) o;
        return getComponentPrice() == that.getComponentPrice() && getComponentType() == that.getComponentType() && getComponentName().equals(that.getComponentName());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getComponentType(), getComponentName(), getComponentPrice());
    }
}
