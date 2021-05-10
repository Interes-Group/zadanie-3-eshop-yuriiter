package sk.stuba.fei.uim.oop.assignment3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private CartRepository repository;

    @Autowired
    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    public Cart create(Cart cart) {
        return this.repository.save(cart);
    }

    public Optional<Cart> findById(Long id) {
        return this.repository.findById(id);
    }

    public boolean deleteById(Long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Cart addShoppingListItem(Long cartId, ShoppingListItem item) {
        Cart cart = this.repository.findById(cartId).get();
        ShoppingListItem inCartItem = cart.findShoppingListItem(item.getProductId());
        if(inCartItem != null) {
            inCartItem.setAmount(inCartItem.getAmount() + item.getAmount());
        }
        else {
            cart.addShoppingListItem(item);
        }
        return this.repository.save(cart);
    }
}
