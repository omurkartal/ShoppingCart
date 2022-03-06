package edu.omur.shoppingcart.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ProductType {
    @Id
    @Column(name = "code", unique = true)
    private String code;
    private String name;
    private int discountId;

    public ProductType() {
    }

    public ProductType(String code, String name, int discountId) {
        this.code = code;
        this.name = name;
        this.discountId = discountId;
    }
}
