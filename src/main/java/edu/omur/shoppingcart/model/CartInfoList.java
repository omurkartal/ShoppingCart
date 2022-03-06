package edu.omur.shoppingcart.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@Getter
@Setter
public class CartInfoList {
    private UUID uuid = UUID.randomUUID();
    private List<CartInfo> cartList = new ArrayList<>();
    private String discountId;
    private short discountRatio;

    public CartInfoList() {
    }

    public void clear() {
        cartList.clear();
        clearDiscount();
    }

    public void clearDiscount() {
        discountId = "";
        discountRatio = 0;
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
        return String.format("uuid:%s ; cartInfo:%s ; discountId:%s ; discountRatio:%d"
                , uuid.toString()
                , sj.toString()
                , discountId
                , discountRatio
        );
    }
}
