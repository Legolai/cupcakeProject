package dk.cphbusiness.dat.cupcakeproject.model.entities;


import java.time.LocalDateTime;
import java.util.List;

public class Order
{
    private final int userId;
    private LocalDateTime created;
    private LocalDateTime requestedDelivery;
    private LocalDateTime shipped;
    private List<DBEntity<OrderDetail>> orderDetails;

    public Order(int userId, LocalDateTime requestedDelivery)
    {
        this.userId = userId;
        this.requestedDelivery = requestedDelivery;
    }

    public int getUserId()
    {
        return userId;
    }

    public LocalDateTime getRequestedDelivery()
    {
        return requestedDelivery;
    }

    public LocalDateTime getCreated()
    {
        return created;
    }

    public LocalDateTime getShipped()
    {
        return shipped;
    }

    public List<DBEntity<OrderDetail>> getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetails(List<DBEntity<OrderDetail>> orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    public void setCreated(LocalDateTime created)
    {
        this.created = created;
    }

    public void setShipped(LocalDateTime shipped)
    {
        this.shipped = shipped;
    }
}
