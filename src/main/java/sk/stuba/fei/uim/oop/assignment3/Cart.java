package sk.stuba.fei.uim.oop.assignment3;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(targetEntity=ShoppingListItem.class, mappedBy="productId", fetch= FetchType.EAGER)
    private List<ShoppingListItem> shoppingList;
    private boolean payed = true;

    public Cart() {}
    public Cart(List<ShoppingListItem> shoppingList, boolean payed) {
        this.shoppingList = new ArrayList<>(shoppingList);
        this.payed = payed;
    }

    public void addShoppingListItem(Long id, int amount) {
        this.shoppingList.add(new ShoppingListItem(id, amount));
    }
    public boolean removeShoppingListItem(Long id) {
        ShoppingListItem find = shoppingList.stream().filter(x -> x.getProductId().equals(id))
                .findAny()
                .orElse(null);
        if(find == null) return false;
        shoppingList.remove(find);
        return true;
    }
    public ShoppingListItem findShoppingListItem(Long productId) {
        ShoppingListItem find = shoppingList.stream().filter(x -> x.getProductId().equals(productId))
                .findAny()
                .orElse(null);
        return find;
    }
    public void addShoppingListItem(ShoppingListItem item) {
        this.shoppingList.add(item);
    }

}
