package edu.omur.shoppingcart.controller;

import edu.omur.shoppingcart.model.OrderInfo;
import edu.omur.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orderList")
public class OrderController {
    private static final String MVC_VIEW_NAME = "orderList";

    @Autowired
    private OrderService orderService;

    @ModelAttribute("orderList")
    public List<OrderInfo> getCartInfoList() {
        return orderService.getOrderList();
    }

    @GetMapping
    public String openPage() {
        return MVC_VIEW_NAME;
    }
}
