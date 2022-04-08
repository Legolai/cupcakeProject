package dk.cphbusiness.dat.cupcakeproject.model.entities;

public class OrderDetail
{
    private final int toppingId;
    private final int bottomId;
    private final int quantity;
    private int orderId;
    private String comments;

    public OrderDetail(int toppingId, int bottomId, int quantity)
    {
        this.toppingId = toppingId;
        this.bottomId = bottomId;
        this.quantity = quantity;
    }

    public OrderDetail(int toppingId, int bottomId, int quantity, int orderId, String comments)
    {
        this.toppingId = toppingId;
        this.bottomId = bottomId;
        this.quantity = quantity;
        this.orderId = orderId;
        this.comments = comments;
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
    public String getComments() {
        return comments;
    }


    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
}
