package edu.omur.shoopingcart;

import edu.omur.shoppingcart.entity.Product;
import edu.omur.shoppingcart.entity.ProductType;
import edu.omur.shoppingcart.repository.ProductRepository;
import edu.omur.shoppingcart.repository.ProductTypeRepository;
import edu.omur.shoppingcart.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    ProductTypeRepository productTypeRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    @Autowired
    ProductServiceImpl productService;

    @Before
    public void init() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);

        List<ProductType> productTypelist = new ArrayList<>();
        productTypelist.add(new ProductType("ProductType1", "name1", 1));
        productTypelist.add(new ProductType("ProductType2", "name2", 2));
        productTypelist.add(new ProductType("ProductType3", "name3", 3));
        when(productTypeRepository.findAll()).thenReturn(productTypelist);

        List<Product> productlist = new ArrayList<>();
        productlist.add(new Product("Product1a", "name1", 10, "ProductType1"));
        productlist.add(new Product("Product1b", "name2", 11, "ProductType1"));
        productlist.add(new Product("Product2", "name3", 20, "ProductType2"));
        productlist.add(new Product("Product3", "name4", 30, "ProductType3"));
        when(productRepository.findAll()).thenReturn(productlist);

        // call post-constructor
        Method postConstruct = ProductServiceImpl.class.getDeclaredMethod("init", null);
        postConstruct.setAccessible(true);
        postConstruct.invoke(productService);
    }

    @Test
    public void testProductTypeList() {
        List<ProductType> productTypeList = productService.listProductTypes();
        productTypeList.stream().forEach(item -> System.out.println(item.toString()));
        assertNotNull(productTypeList);
        assertEquals(4, productTypeList.size());
        assertEquals("ProductType1", productTypeList.get(1).getCode());
        assertEquals("ProductType2", productTypeList.get(2).getCode());
    }

    @Test
    public void testProductList() {
        List<Product> productList = productService.listProductsByProductType("ProductType1");
        System.out.println(productList);
        assertNotNull(productList);
        assertEquals(2, productList.size());
        assertEquals("Product1a", productList.get(0).getCode());
        assertEquals("Product1b", productList.get(1).getCode());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testProductList2() {
        List<Product> productList = productService.listProductsByProductType("not-found");
        productList.get(1);
    }
}
