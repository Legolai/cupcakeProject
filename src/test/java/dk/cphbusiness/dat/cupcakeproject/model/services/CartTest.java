package dk.cphbusiness.dat.cupcakeproject.model.services;

import dk.cphbusiness.dat.cupcakeproject.model.entities.CartItem;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponent;
import dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponentType;
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
        CupcakeComponent top1 = new CupcakeComponent(CupcakeComponentType.TOPPING, "Vanilla", 5);
        CupcakeComponent top2 = new CupcakeComponent(CupcakeComponentType.TOPPING, "Chocolate", 5);
        CupcakeComponent bottom1 = new CupcakeComponent(CupcakeComponentType.BOTTOM, "Chocolate", 6);
        CupcakeComponent bottom2 = new CupcakeComponent(CupcakeComponentType.BOTTOM, "Blueberry", 7);

        cart.addCartItem(new CartItem(top1, bottom1, 3, ""));
        cart.addCartItem(new CartItem(top2, bottom1, 4, ""));
        cart.addCartItem(new CartItem(top1, bottom2, 5, ""));
        cart.addCartItem(new CartItem(top2, bottom2, 1, ""));
    }

    @Test
    void getCartItems()
    {
        assertEquals(4, cart.getCartItems().size());
        CupcakeComponent top = new CupcakeComponent(CupcakeComponentType.TOPPING, "Chocolate", 5);
        CupcakeComponent bottom = new CupcakeComponent(CupcakeComponentType.BOTTOM, "Chocolate", 6);
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
        CupcakeComponent top = new CupcakeComponent(CupcakeComponentType.TOPPING, "Chocolate", 5);
        CupcakeComponent bottom = new CupcakeComponent(CupcakeComponentType.BOTTOM, "Chocolate", 6);
        cart.addCartItem(new CartItem(top, bottom, 3, ""));
        assertEquals(4, cart.getCartItems().size());
        assertEquals(7, cart.getCartItems().get(1).getQuantity());
    }

    @Test
    void removeCartItem()
    {
        CupcakeComponent top = new CupcakeComponent(CupcakeComponentType.TOPPING, "Chocolate", 5);
        CupcakeComponent bottom = new CupcakeComponent(CupcakeComponentType.BOTTOM, "Chocolate", 6);
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
        CupcakeComponent top = new CupcakeComponent(CupcakeComponentType.TOPPING, "Chocolate", 5);
        CupcakeComponent bottom = new CupcakeComponent(CupcakeComponentType.BOTTOM, "Chocolate", 6);
        cart.addCartItem(new CartItem(top, bottom, 3, ""));
        assertEquals(182, cart.getTotalPrice());
    }
}