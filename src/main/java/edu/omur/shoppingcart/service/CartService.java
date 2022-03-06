package edu.omur.shoppingcart.service;

import edu.omur.shoppingcart.model.CartInfoList;
import edu.omur.shoppingcart.model.OrderInfo;
import edu.omur.shoppingcart.model.ServiceResponse;

public interface CartService {
    CartInfoList getCartInfoList();

    ServiceResponse<CartInfoList> addProductToCart(String productCode);

    ServiceResponse<CartInfoList> removeProductFromCart(String productCode);

    void clearList();

    ServiceResponse<CartInfoList> updateQuantities();

    ServiceResponse<CartInfoList> addDiscount();

    void removeDiscount();

    ServiceResponse<OrderInfo> completeOrder();
}
