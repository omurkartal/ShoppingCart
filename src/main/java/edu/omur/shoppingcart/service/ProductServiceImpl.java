package edu.omur.shoppingcart.service;

import edu.omur.shoppingcart.entity.Product;
import edu.omur.shoppingcart.entity.ProductType;
import edu.omur.shoppingcart.repository.ProductRepository;
import edu.omur.shoppingcart.repository.ProductTypeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
    private static final String PRODUCT_TYPE_ALL = "ALL-PRODUCTS";
    private static List<ProductType> productTypelist;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    public void init() {
        productTypelist = productTypeRepository.findAll();
        productTypelist.sort(Comparator.comparing(ProductType::getName));
        productTypelist.add(0, new ProductType(PRODUCT_TYPE_ALL, "All", 0));
    }

    @Override
    public List<ProductType> listProductTypes() {
        return productTypelist;
    }

    @Override
    public List<Product> listAllProducts() {
        return listProductsByProductType(PRODUCT_TYPE_ALL);
    }

    @Override
    public List<Product> listProductsByProductType(String productType) {
        List<Product> productList;
        if (productType.equalsIgnoreCase(PRODUCT_TYPE_ALL)) {
            productList = productRepository.findAll();
        } else {
            productList = productRepository.findAll().stream().filter(item -> item.getProductType().equalsIgnoreCase(productType)).collect(Collectors.toList());
        }
        return productList;
    }
}
