package sk.stuba.fei.uim.oop.assignment3;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListItemResponse {
    private Long productId;
    private int amount;

    public ShoppingListItemResponse(ShoppingListItem item) {
        this.productId = item.getProductId();
        this.amount = item.getAmount();
    }
}
