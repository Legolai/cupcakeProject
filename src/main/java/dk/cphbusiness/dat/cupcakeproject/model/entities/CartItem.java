package dk.cphbusiness.dat.cupcakeproject.model.entities;

import java.util.Objects;

public class CartItem
{
    private final CupcakeComponent topping;
    private final CupcakeComponent bottom;
    private int quantity;
    private final String comments;

    public CartItem(CupcakeComponent topping, CupcakeComponent buttom, int quantity, String comments)
    {
        this.topping = topping;
        this.bottom = buttom;
        this.quantity = quantity;
        this.comments = comments;
    }


    public CupcakeComponent getTopping()
    {
        return topping;
    }

    public CupcakeComponent getBottom()
    {
        return bottom;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public String getComments()
    {
        return comments;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getTotalPrice(){
        return quantity * (topping.getComponentPrice() + bottom.getComponentPrice());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        CartItem cartItem = (CartItem) o;
        return getTopping().equals(cartItem.getTopping()) && getBottom().equals(cartItem.getBottom()) && getComments().equals(cartItem.getComments());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getTopping(), getBottom(), getComments());
    }
}
