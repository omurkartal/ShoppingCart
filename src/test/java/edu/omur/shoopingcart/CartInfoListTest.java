package edu.omur.shoopingcart;


import edu.omur.shoppingcart.entity.Product;
import edu.omur.shoppingcart.model.CartInfo;
import edu.omur.shoppingcart.model.CartInfoList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartInfoListTest {
    private CartInfoList cartInfoList;

    @Before
    public void init() {
        // total amount is 100
        CartInfo cartInfo1 = new CartInfo(new Product("code1", "name1", 10, "code1"));
        cartInfo1.setQuantity(2);
        CartInfo cartInfo2 = new CartInfo(new Product("code2", "name2", 20, "code2"));
        cartInfo2.setQuantity(4);
        List<CartInfo> cartList = new ArrayList<>();
        cartList.add(cartInfo1);
        cartList.add(cartInfo2);
        cartInfoList = new CartInfoList();
        cartInfoList.setUuid(UUID.randomUUID());
        cartInfoList.setCartList(cartList);
    }

    @Test
    public void testCartInfoListWithDiscount() {
        System.out.println(cartInfoList.toString());
        assertEquals(100, cartInfoList.getTotalAmount());
    }

    @Test
    public void testCartInfoListWithoutDiscount() {
        cartInfoList.setDiscountId("discount1");
        cartInfoList.setDiscountRatio((short) 10);
        System.out.println(cartInfoList.toString());
        assertEquals(90, cartInfoList.getTotalAmount());
    }

    @Test
    public void testCartInfoListWithoutDiscount2() {
        cartInfoList.setDiscountId("discount1");
        cartInfoList.setDiscountRatio((short) 20);
        System.out.println(cartInfoList.toString());
        assertEquals(80, cartInfoList.getTotalAmount());
    }
}
