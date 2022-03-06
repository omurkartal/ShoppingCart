package edu.omur.shoppingcart.model;

import edu.omur.shoppingcart.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartInfo {
    private Product product;
    private int quantity;

    public CartInfo(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public double getSubTotal() {
        return this.product.getPrice() * this.quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getProduct().getCode());
        sb.append(":");
        sb.append(getQuantity());
        return sb.toString();
    }
}
