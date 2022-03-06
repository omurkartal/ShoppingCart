package edu.omur.shoppingcart.service;

import edu.omur.shoppingcart.model.CartInfoList;
import edu.omur.shoppingcart.model.OrderInfo;
import edu.omur.shoppingcart.model.ServiceResponse;

import java.util.List;

public interface OrderService {
    List<OrderInfo> getOrderList();

    ServiceResponse<OrderInfo> completeOrder(CartInfoList cartInfoList);
}
