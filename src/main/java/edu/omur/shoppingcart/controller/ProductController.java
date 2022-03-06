package edu.omur.shoppingcart.controller;

import edu.omur.shoppingcart.entity.Product;
import edu.omur.shoppingcart.entity.ProductType;
import edu.omur.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("productList")
public class ProductController {
    private static final String MVC_VIEW_NAME = "productList";

    @Autowired
    private ProductService productService;

    @ModelAttribute("productTypes")
    public List<ProductType> getProductTypes() {
        return productService.listProductTypes();
    }

    @ModelAttribute("productList")
    public List<Product> getProducts() {
        return productService.listAllProducts();
    }

    @GetMapping
    public String openPage() {
        return MVC_VIEW_NAME;
    }

    @PostMapping
    public String listProducts(Model model, @RequestParam("productType") String productType) {
        List<Product> productList = productService.listProductsByProductType(productType);
        model.addAttribute("productList", productList);
        return MVC_VIEW_NAME;
    }
}
