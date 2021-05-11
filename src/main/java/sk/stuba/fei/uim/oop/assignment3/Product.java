package sk.stuba.fei.uim.oop.assignment3;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "product_sequence", initialValue = 1)
public class Product {
    @Id
    @GeneratedValue(generator = "product_sequence")
    private Long id;
    private String name;
    private String description;
    private Integer amount;
    private String unit;
    private Integer price;

    public Product() {}

    public Product(String name, String description, int amount, String unit, int price) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.unit = unit;
        this.price = price;
    }
}


