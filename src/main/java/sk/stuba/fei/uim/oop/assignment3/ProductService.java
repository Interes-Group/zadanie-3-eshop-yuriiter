package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

@Service
public class ProductService {
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;

        this.repository.save(new Product("First", "First product", 2, "pcs", 100));
        this.repository.save(new Product("Second", "Second product", 3, "pcs", 200));
        this.repository.save(new Product("Third", "Third product", 4, "pcs", 300));
    }

    public List<Product> getAll() {
        List<Product> l = this.repository.findAll();
        return l;
    }

    public Optional<Product> getById(Long id) {
        return this.repository.findById(id);
    }

    public Product create(Product product) {
        return this.repository.save(product);
    }

    public Product addProductAmount(Long id, int amount) {
        Product p = this.repository.findAll().stream().filter(x -> x.getId().equals(id))
                .findAny()
                .orElse(null);
        if(p == null) {
            return null;
        }
        int currentAmount = p.getAmount();
        p.setAmount(currentAmount + amount);
        return p;
    }

    public Product update(Long id, Product product) {
        Product p = this.repository.findAll().stream().filter(x -> x.getId().equals(id))
                .findAny()
                .orElse(null);
        if(p == null) {
            return null;
        }
        String name = product.getName();
        String description = product.getDescription();
        String unit = product.getUnit();
        Long id_ = product.getId();
        int amount = product.getAmount();
        int price = product.getPrice();

        if(name != null)
            p.setName(name);
        if(description != null)
            p.setDescription(description);
        if(unit != null)
            p.setUnit(unit);
        if(id_ != null)
            p.setId(id_);
        if(amount > 0)
            p.setAmount(amount);
        if(price > 0)
            p.setPrice(price);

        return p;
    }

    public void delete(Long id) {
        Product p = this.repository.findAll().stream().filter(x -> x.getId().equals(id))
                .findAny()
                .orElse(null);
        if(p == null) {
            return;
        }
        this.repository.delete(p);
    }

}
