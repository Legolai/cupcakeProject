package dk.cphbusiness.dat.cupcakeproject.model.services;

import dk.cphbusiness.dat.cupcakeproject.model.entities.CartItem;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponent;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponentType;
import dk.cphbusiness.dat.cupcakeproject.model.entities.DBEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTest
{
    private static Cart cart;

    @BeforeEach
    void setUp()
    {
        cart = new Cart();
        DBEntity<CupcakeComponent> top1 = new DBEntity<>(1, new CupcakeComponent(CupcakeComponentType.TOPPING, "Vanilla", 5));
        DBEntity<CupcakeComponent> top2 = new DBEntity<>(2, new CupcakeComponent(CupcakeComponentType.TOPPING, "Chocolate", 5));
        DBEntity<CupcakeComponent> bottom1 = new DBEntity<>(1, new CupcakeComponent(CupcakeComponentType.BOTTOM, "Chocolate", 6));
        DBEntity<CupcakeComponent> bottom2 = new DBEntity<>(2, new CupcakeComponent(CupcakeComponentType.BOTTOM, "Blueberry", 7));

        cart.addCartItem(new CartItem(top1, bottom1, 3, ""));
        cart.addCartItem(new CartItem(top2, bottom1, 4, ""));
        cart.addCartItem(new CartItem(top1, bottom2, 5, ""));
        cart.addCartItem(new CartItem(top2, bottom2, 1, ""));
    }

    @Test
    void getCartItems()
    {
        assertEquals(4, cart.getCartItems().size());
        DBEntity<CupcakeComponent> top = new DBEntity<>(2, new CupcakeComponent(CupcakeComponentType.TOPPING, "Chocolate", 5));
        DBEntity<CupcakeComponent> bottom = new DBEntity<>(1, new CupcakeComponent(CupcakeComponentType.BOTTOM, "Chocolate", 6));
        cart.addCartItem(new CartItem(top, bottom, 3, ""));
        assertEquals(4, cart.getCartItems().size());
        cart.removeCartItem(new CartItem(top, bottom, 3, ""));
        assertEquals(3, cart.getCartItems().size());
    }

    @Test
    void addCartItem()
    {
        assertEquals(4, cart.getCartItems().size());
        assertEquals(4, cart.getCartItems().get(1).getQuantity());
        DBEntity<CupcakeComponent> top = new DBEntity<>(2, new CupcakeComponent(CupcakeComponentType.TOPPING, "Chocolate", 5));
        DBEntity<CupcakeComponent> bottom = new DBEntity<>(1, new CupcakeComponent(CupcakeComponentType.BOTTOM, "Chocolate", 6));
        cart.addCartItem(new CartItem(top, bottom, 3, ""));
        assertEquals(4, cart.getCartItems().size());
        assertEquals(7, cart.getCartItems().get(1).getQuantity());
    }

    @Test
    void removeCartItem()
    {
        DBEntity<CupcakeComponent> top = new DBEntity<>(2, new CupcakeComponent(CupcakeComponentType.TOPPING, "Chocolate", 5));
        DBEntity<CupcakeComponent> bottom = new DBEntity<>(1, new CupcakeComponent(CupcakeComponentType.BOTTOM, "Chocolate", 6));
        assertEquals(4, cart.getCartItems().size());
        cart.removeCartItem(new CartItem(top, bottom, 3, ""));
        assertEquals(3, cart.getCartItems().size());
        cart.removeCartItem(0);
        assertEquals(2, cart.getCartItems().size());
    }

    @Test
    void getTotalPrice()
    {
        assertEquals(149, cart.getTotalPrice());
        DBEntity<CupcakeComponent> top = new DBEntity<>(2, new CupcakeComponent(CupcakeComponentType.TOPPING, "Chocolate", 5));
        DBEntity<CupcakeComponent> bottom = new DBEntity<>(1, new CupcakeComponent(CupcakeComponentType.BOTTOM, "Chocolate", 6));
        cart.addCartItem(new CartItem(top, bottom, 3, ""));
        assertEquals(182, cart.getTotalPrice());
    }
}