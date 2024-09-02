package psk.example.feasthub;

import java.util.ArrayList;
import java.util.List;

public class CartData {
    private static List<CartItem> cartItems = new ArrayList<>();
    public static List<CartItem> getCartItems() {
        return cartItems;
    }

    public static void addToCart(CartItem item) {
        cartItems.add(item);
    }

    // You can add other methods for managing the cart data
}
