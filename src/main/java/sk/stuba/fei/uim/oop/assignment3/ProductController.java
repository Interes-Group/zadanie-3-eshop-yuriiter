package sk.stuba.fei.uim.oop.assignment3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;


    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return this.service.getAll();
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getProductById(@PathVariable(name = "id") Long id) {
        return this.service.getById(id);
    }

    @GetMapping("/product/{id}/amount")
    public int getProductAmount(@PathVariable(name = "id") Long id) {
        Product product = this.service.getById(id).get();
        int amount = 0;
        if(product != null) {
            amount = product.getAmount();
        }
        return amount;
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        return this.service.create(product);
    }

    @PostMapping("/product/{id}/amount")
    public Product addProductAmount(@PathVariable(name = "id") Long id, @RequestBody int amount) {
        return this.service.addProductAmount(id, amount);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable(name = "id") Long id, @RequestBody Product product) {
        return this.service.update(id, product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long id) {
        this.service.delete(id);
    }


}
