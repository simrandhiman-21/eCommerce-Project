package com.ecomm.application.beans;

import com.ecomm.application.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartModelMapper {

    public static Cart MapBeanToEntity(CartRequestBean cartRequestBean){
        Cart cart=new Cart();
        cart.setId(cartRequestBean.getId());
        cart.setProducts(cartRequestBean.getProduct());
        cart.setPrice(cartRequestBean.getPrice());
        cart.setQuantity(cartRequestBean.getQuantity());
        return cart;
    }
}
