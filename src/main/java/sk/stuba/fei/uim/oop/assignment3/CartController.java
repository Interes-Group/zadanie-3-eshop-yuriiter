package sk.stuba.fei.uim.oop.assignment3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;


    @PostMapping("/cart")
    public Cart createCart(Cart cart) {
        return this.cartService.create(cart);
    }

    @GetMapping("/cart/{id}")
    public Optional<Cart> getCartById(@PathVariable(name = "id") Long id) {
        return this.cartService.findById(id);
    }

    @DeleteMapping("/cart/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        this.cartService.deleteById(id);
    }

    @PostMapping("/cart/{id}/add")
    public ResponseEntity<Cart> addToCart(@PathVariable(name = "id") Long cartId, @RequestBody ShoppingListItem item) {
        Product product = productService.getById(item.getProductId()).get();
        Cart cart = cartService.findById(cartId).get();
        if(product == null) {
            return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
        }
        if(cart.isPayed() || product.getAmount() < item.getAmount()) {
            return new ResponseEntity<Cart>(HttpStatus.BAD_REQUEST);
        }
        productService.addProductAmount(product.getId(), -1 * (item.getAmount()));
        cartService.addShoppingListItem(cartId, item);
        return new ResponseEntity<Cart>(cartService.findById(cartId).get(), HttpStatus.OK);
    }

    @GetMapping("/cart/{id}/pay")
    public ResponseEntity<Integer> sumPay(@PathVariable(name = "id") Long id) {
        Cart cart = cartService.findById(id).get();
        if(cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(cart.isPayed()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<ShoppingListItem> shoppingList = cart.getShoppingList();
        int sum = 0;
        for(ShoppingListItem item : shoppingList) {
            Long productId = item.getProductId();
            Product product = productService.getById(productId).get();
            if(product == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            sum += (product.getPrice() * item.getAmount());
        }

        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

}
