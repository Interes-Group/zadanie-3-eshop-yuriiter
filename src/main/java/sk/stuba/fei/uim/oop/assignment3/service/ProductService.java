package sk.stuba.fei.uim.oop.assignment3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.entity.Product;
import sk.stuba.fei.uim.oop.assignment3.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }

    public Optional<Product> getById(Long id) {
        return this.repository.findById(id);
    }

    public Product create(Product product) {
        return this.repository.save(product);
    }

    public Product addProductAmount(Long id, int amount) {
        Optional<Product> opt = this.repository.findById(id);
        if(opt.isEmpty()) {
            return null;
        }
        Product p = opt.get();
        int currentAmount = p.getAmount();
        p.setAmount(currentAmount + amount);
        return repository.save(p);
    }

    public Product update(Long id, Product product) {
        Optional<Product> opt = this.repository.findById(id);
        if(opt.isEmpty()) {
            return null;
        }

        Product p = opt.get();

        String name = product.getName();
        String description = product.getDescription();
        String unit = product.getUnit();
        Integer amount = product.getAmount();
        Integer price = product.getPrice();

        if(name != null)
            p.setName(name);
        if(description != null)
            p.setDescription(description);
        if(unit != null)
            p.setUnit(unit);
        if(amount != null)
            p.setAmount(amount);
        if(price != null)
            p.setPrice(price);

        return this.repository.save(p);
    }

    public boolean delete(Long id) {
        Optional<Product> opt = repository.findById(id);
        if(opt.isEmpty()) {
            return false;
        }
        this.repository.delete((opt.get()));
        return true;
    }

}
