package edu.omur.shoppingcart.service;

import edu.omur.shoppingcart.model.CartInfoList;
import edu.omur.shoppingcart.model.OrderInfo;
import edu.omur.shoppingcart.model.ServiceResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private static long orderNum = 0;
    private List<OrderInfo> list = new ArrayList<>();


    @Override
    public List<OrderInfo> getOrderList() {
        return this.list;
    }

    @Override
    public ServiceResponse<OrderInfo> completeOrder(CartInfoList cartInfoList) {
        logger.info("complete order");
        cartInfoList.getCartList().stream().forEach(item -> logger.info(item));
        orderNum++;
        OrderInfo orderInfo = new OrderInfo(orderNum, cartInfoList);
        list.add(orderInfo);
        return new ServiceResponse<>(orderInfo);
    }
}
