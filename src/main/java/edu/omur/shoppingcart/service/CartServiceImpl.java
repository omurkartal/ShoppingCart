package edu.omur.shoppingcart.service;

import edu.omur.shoppingcart.annotation.TrackCartItem;
import edu.omur.shoppingcart.config.AppConfig;
import edu.omur.shoppingcart.entity.Product;
import edu.omur.shoppingcart.model.*;
import edu.omur.shoppingcart.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Scope("session")
public class CartServiceImpl implements CartService {
    private static final Logger logger = LogManager.getLogger(CartServiceImpl.class);
    private static final String MVC_VIEW_NAME = "cartList";

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    OrderService orderService;

    private CartInfoList cartInfoList = new CartInfoList();

    @Override
    public CartInfoList getCartInfoList() {
        return cartInfoList;
    }

    @Override
    @TrackCartItem(logParameters = true)
    public ServiceResponse<CartInfoList> addProductToCart(String productCode) {
        logger.info("productCode will be added: {}", productCode);
        String errorMessage = null;
        Optional<CartInfo> cartInfo = cartInfoList.getCartList().stream().filter(item -> item.getProduct().getCode().equalsIgnoreCase(productCode)).findFirst();
        if (cartInfo.isPresent()) {
            cartInfo.get().setQuantity(cartInfo.get().getQuantity() + 1);
        } else {
            Product product = productRepository.findById(productCode).get();
            if (product != null) {
                cartInfoList.getCartList().add(new CartInfo(product));
            } else {
                errorMessage = String.format("Product (code: %s) not found!!", productCode);
            }
        }
        return new ServiceResponse(cartInfoList, errorMessage);
    }

    @Override
    @TrackCartItem(logParameters = true)
    public ServiceResponse<CartInfoList> removeProductFromCart(String productCode) {
        logger.info("productCode will be removed: {}", productCode);
        Optional<CartInfo> cartInfo = cartInfoList.getCartList().stream().filter(item -> item.getProduct().getCode().equalsIgnoreCase(productCode)).findFirst();
        if (cartInfo != null) {
            cartInfoList.getCartList().remove(cartInfo.get());
        }
        return new ServiceResponse(cartInfoList);
    }

    @Override
    @TrackCartItem(logParameters = false)
    public void clearList() {
        cartInfoList.clear();
    }

    @Override
    @TrackCartItem(logParameters = false)
    public ServiceResponse<CartInfoList> updateQuantities() {
        String errorMessage = null;
        cartInfoList.getCartList().stream().forEach(item -> logger.info("Code: {}, Quantity: {}", item.getProduct().getCode(), item.getQuantity()));
        Optional<CartInfo> tmpList = cartInfoList.getCartList().stream().filter(item -> item.getQuantity() > 1000).findAny();
        if (tmpList.isPresent()) {
            errorMessage = "Quantity must be less than 1000 for any item.";
        }
        return new ServiceResponse(cartInfoList, errorMessage);
    }

    @Override
    @TrackCartItem(logParameters = false)
    public ServiceResponse<CartInfoList> addDiscount() {
        String errorMessage = null;
        Optional<Discount> discountOption = appConfig.getDiscountList().stream().filter(item -> item.getId().equalsIgnoreCase(cartInfoList.getDiscountId())).findAny();
        if (discountOption.isPresent()) {
            Discount discount = discountOption.get();
            cartInfoList.setDiscountRatio(discount.getRatio());
        } else {
            cartInfoList.setDiscountRatio((short) 0);
            if (cartInfoList.getDiscountId().trim().isEmpty()) {
                errorMessage = "Discount id cannot be empty!";
            } else {
                errorMessage = "Invalid discount code: " + cartInfoList.getDiscountId();
                cartInfoList.setDiscountId("");
            }
        }
        return new ServiceResponse(cartInfoList, errorMessage);
    }

    @Override
    @TrackCartItem(logParameters = false)
    public void removeDiscount() {
        cartInfoList.clearDiscount();
    }

    @Override
    @TrackCartItem
    public ServiceResponse<OrderInfo> completeOrder() {
        ServiceResponse<OrderInfo> serviceResponse = orderService.completeOrder(cartInfoList);
        cartInfoList.clear();
        return serviceResponse;
    }
}
