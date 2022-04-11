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
    private boolean isPaid;

    public Order(int userId, LocalDateTime requestedDelivery)
    {
        this.userId = userId;
        this.requestedDelivery = requestedDelivery;
    }
    public Order(int userId, LocalDateTime created, LocalDateTime requestedDelivery, LocalDateTime shipped, List<DBEntity<OrderDetail>> orderDetails, boolean isPaid) {
        this.userId = userId;
        this.created = created;
        this.requestedDelivery = requestedDelivery;
        this.shipped = shipped;
        this.orderDetails = orderDetails;
        this.isPaid = isPaid;
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
    public boolean isPaid()
    {
        return isPaid;
    }

    public void setCreated(LocalDateTime created)
    {
        this.created = created;
    }
    public void setShipped(LocalDateTime shipped)
    {
        this.shipped = shipped;
    }
    public void setOrderDetails(List<DBEntity<OrderDetail>> orderDetails)
    {
        this.orderDetails = orderDetails;
    }
    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
}
