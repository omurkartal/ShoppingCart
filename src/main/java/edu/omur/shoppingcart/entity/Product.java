package edu.omur.shoppingcart.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Product {
    @Id
    @Column(name = "code", unique = true)
    private String code;
    private String name;
    private double price;
    private String productType;
    private String imagePath;

    public Product() {
    }

    public Product(String code, String name, double price, String productType) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.productType = productType;
    }

    public Product(String code, String name, double price, String productType, String imagePath) {
        this(code, name, price, productType);
        this.imagePath = imagePath;
    }
}
