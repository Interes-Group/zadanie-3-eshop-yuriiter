package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShoppingListItemRepository extends CrudRepository<ShoppingListItem, Long> {
    List<ShoppingListItem> findAll();
}
