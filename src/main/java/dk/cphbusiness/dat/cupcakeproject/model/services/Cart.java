package dk.cphbusiness.dat.cupcakeproject.model.services;

import dk.cphbusiness.dat.cupcakeproject.model.entities.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart
{
    private final List<CartItem> cartItems;

    public Cart()
    {
        this.cartItems = new ArrayList<>();
    }

    public List<CartItem> getCartItems(){
        return cartItems;
    }

    public void addCartItem(CartItem item) {
        Optional<CartItem> optionalCartItem = cartItems.stream().filter(element -> element.equals(item)).findFirst();
        if(!optionalCartItem.isPresent()){
            cartItems.add(item);
            return;
        }
        CartItem cartItem = optionalCartItem.get();
        int newQuantity = cartItem.getQuantity() + item.getQuantity();
        cartItem.setQuantity(newQuantity);
    }

    public void removeCartItem(CartItem item) {
        cartItems.remove(item);
    }

    public void removeCartItem(int index) {
        cartItems.remove(index);
    }

    public int getTotalPrice() {
        return cartItems.stream().mapToInt(CartItem::getTotalPrice).sum();
    }
}
