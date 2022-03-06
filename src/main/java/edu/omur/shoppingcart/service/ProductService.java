package edu.omur.shoppingcart.service;

import edu.omur.shoppingcart.entity.Product;
import edu.omur.shoppingcart.entity.ProductType;

import java.util.List;

public interface ProductService {
    List<ProductType> listProductTypes();

    List<Product> listAllProducts();

    List<Product> listProductsByProductType(String productType);
}
