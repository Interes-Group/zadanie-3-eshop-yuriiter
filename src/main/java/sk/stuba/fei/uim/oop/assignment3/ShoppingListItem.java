package sk.stuba.fei.uim.oop.assignment3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@JsonIgnoreProperties(ignoreUnknown = true)
@SequenceGenerator(name = "shopping_list_item_sequence", initialValue = 1)
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ShoppingListItem {
    @GeneratedValue(generator = "shopping_list_item_sequence")
    @Id
    private Long id;
    private Long productId;
    private int amount;

    public ShoppingListItem(Long productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }
}
