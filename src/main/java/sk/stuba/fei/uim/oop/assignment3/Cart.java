package sk.stuba.fei.uim.oop.assignment3;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;


@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private List<ShoppingListItem> shoppingList;
    private boolean payed;

    public void addShoppingListItem(Long id, int amount) {
        this.shoppingList.add(new ShoppingListItem(id, amount));
    }
    public boolean removeShoppingListItem(Long id) {
        ShoppingListItem find = shoppingList.stream().filter(x -> x.getId().equals(id))
                .findAny()
                .orElse(null);
        if(find == null) return false;
        shoppingList.remove(find);
        return true;
    }
}
