package sk.stuba.fei.uim.oop.assignment3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;


    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return this.service.getAll();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable(name = "id") String id) {
        return this.service.getById(Long.valueOf(id));
    }

    @GetMapping("/product/{id}/amount")
    public int getProductAmount(@PathVariable(name = "id") String id) {
        return this.service.getById(Long.valueOf(id)).getAmount();
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        return this.service.create(product);
    }

    @PostMapping("/product/{id}/amount")
    public Product addProductAmount(@PathVariable(name = "id") String id, @RequestBody int amount) {
        return this.service.addProductAmount(Long.valueOf(id), amount);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable(name = "id") String id, @RequestBody Product product) {
        return this.service.update(Long.valueOf(id), product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable(name = "id") String id) {
        this.service.delete(Long.valueOf(id));
    }


}
