package dk.cphbusiness.dat.cupcakeproject.model.entities;

public class OrderDetail
{
    private final int toppingId;
    private final int bottomId;
    private final int quantity;
    private final int orderId;

    public OrderDetail(int toppingId, int bottomId, int quantity, int orderId)
    {
        this.toppingId = toppingId;
        this.bottomId = bottomId;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public int getToppingId()
    {
        return toppingId;
    }

    public int getBottomId()
    {
        return bottomId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public int getOrderId()
    {
        return orderId;
    }
}
