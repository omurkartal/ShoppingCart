package edu.omur.shoppingcart.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
public class OrderInfo {
    private long orderId;
    private List<CartInfo> cartList = new ArrayList<>();
    private String discountId;
    private short discountRatio;

    public OrderInfo(long orderNum, CartInfoList cartInfoList) {
        this.orderId = orderNum;
        this.cartList = new ArrayList<CartInfo>(cartInfoList.getCartList());
        this.discountId = cartInfoList.getDiscountId();
        this.discountRatio = cartInfoList.getDiscountRatio();
    }

    public double getTotalAmount() {
        double total = 0;
        for (CartInfo cartInfo : cartList) {
            total = total + cartInfo.getSubTotal();
        }

        if (discountRatio > 0 && discountRatio < 100) {
            total = (total * (100 - discountRatio)) / 100;
        }

        return total;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("|", "[", "]");
        for (CartInfo cartInfo : cartList) {
            sj.add(cartInfo.toString());
        }
        return String.format("orderId:%d ; cartInfo:%s ; discountId:%s ; discountRatio:%d"
                , orderId
                , sj.toString()
                , discountId
                , discountRatio
        );
    }
}
