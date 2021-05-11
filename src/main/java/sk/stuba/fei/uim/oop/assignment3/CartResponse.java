package sk.stuba.fei.uim.oop.assignment3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private Long id;
    private List<ShoppingListItemResponse> shoppingList = new ArrayList<>();
    private boolean payed = false;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.payed = cart.isPayed();
        this.shoppingList = cart.getShoppingList()
                .stream()
                .map(shoppingListItem -> new ShoppingListItemResponse(shoppingListItem))
                .collect(Collectors.toList());
    }
}
