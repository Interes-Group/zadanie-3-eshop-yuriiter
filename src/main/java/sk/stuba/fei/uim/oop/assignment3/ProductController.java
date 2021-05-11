package sk.stuba.fei.uim.oop.assignment3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Product> getProductById(@PathVariable(name = "id") Long id) {
        Optional opt = this.service.getById(id);
        if(opt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>((Product) opt.get(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}/amount")
    public ResponseEntity<AmountContainer> getProductAmount(@PathVariable(name = "id") Long id) {
        Optional opt = this.service.getById(id);
        if(opt.isPresent()) {
            AmountContainer response = new AmountContainer(((Product) opt.get()).getAmount());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(this.service.create(product), HttpStatus.CREATED);
    }

    @PostMapping("/product/{id}/amount")
    public ResponseEntity<AmountContainer> addProductAmount(@PathVariable(name = "id") Long id, @RequestBody AmountContainer amountContainer) {
        int amount = amountContainer.getAmount();
        Product updated = this.service.addProductAmount(id, amount);
        if(updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new AmountContainer(updated.getAmount()), HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(name = "id") Long id, @RequestBody Product product) {
        Product updated = this.service.update(id, product);;
        if(updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable(name = "id") Long id) {
        if(this.service.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
