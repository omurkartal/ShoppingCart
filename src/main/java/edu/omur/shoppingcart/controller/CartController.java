package edu.omur.shoppingcart.controller;

import edu.omur.shoppingcart.model.CartInfoList;
import edu.omur.shoppingcart.model.OrderInfo;
import edu.omur.shoppingcart.model.ServiceResponse;
import edu.omur.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Scope("session")
@RequestMapping("/cartList")
public class CartController {
    private static final String MVC_VIEW_NAME = "cartList";

    @Autowired
    private CartService cartService;

    @ModelAttribute("cartInfoList")
    public CartInfoList getCartInfoList() {
        return cartService.getCartInfoList();
    }

    @GetMapping
    public String openPage() {
        return MVC_VIEW_NAME;
    }

    @GetMapping("/addProductToCart/{productCode}")
    public String addProductToCart(Model model, @PathVariable String productCode) {
        ServiceResponse serviceResponse = cartService.addProductToCart(productCode);
        addErrorMessageToModelIfExist(model, serviceResponse);
        return MVC_VIEW_NAME;
    }

    @GetMapping("/removeProductFromCart/{productCode}")
    public String removeProductFromCart(@PathVariable String productCode) {
        cartService.removeProductFromCart(productCode);
        return MVC_VIEW_NAME;
    }

    @PostMapping(value = "/formAction", params = "clearList")
    public String clearList(@ModelAttribute("cartInfoList") CartInfoList cartInfoList) {
        cartService.clearList();
        return MVC_VIEW_NAME;
    }

    @PostMapping(value = "/formAction", params = "updateQuantities")
    public String updateQuantities(Model model, @ModelAttribute("cartInfoList") CartInfoList cartInfoList) {
        ServiceResponse serviceResponse = cartService.updateQuantities();
        addErrorMessageToModelIfExist(model, serviceResponse);
        return MVC_VIEW_NAME;
    }

    @PostMapping(value = "/formAction", params = "addDiscount")
    public String addDiscount(Model model, @ModelAttribute("cartInfoList") CartInfoList cartInfoList) {
        ServiceResponse serviceResponse = cartService.addDiscount();
        addErrorMessageToModelIfExist(model, serviceResponse);
        return MVC_VIEW_NAME;
    }

    @PostMapping(value = "/formAction", params = "removeDiscount")
    public String removeDiscount(Model model, @ModelAttribute("cartInfoList") CartInfoList cartInfoList) {
        cartService.removeDiscount();
        return MVC_VIEW_NAME;
    }

    @PostMapping(value = "/formAction", params = "completeOrder")
    public String completeOrder(Model model, @ModelAttribute("cartInfoList") CartInfoList cartInfoList) {
        ServiceResponse<OrderInfo> serviceResponse = cartService.completeOrder();
        return "redirect:/api/v1/orderList";
    }

    private void addErrorMessageToModelIfExist(Model model, ServiceResponse serviceResponse) {
        if (serviceResponse != null && serviceResponse.isErrorOccurred()) {
            model.addAttribute("errorMessage", serviceResponse.getErrorMessage());
        }
    }
}
