package com.ecomm.application.service;

import com.ecomm.application.beans.CartModelMapper;
import com.ecomm.application.beans.CartRequestBean;
import com.ecomm.application.entity.Cart;
import com.ecomm.application.entity.Product;
import com.ecomm.application.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartModelMapper cartModelMapper;

   public void addItem(CartRequestBean cartRequestBean){
        Cart cart=CartModelMapper.MapBeanToEntity(cartRequestBean);
        cartRepository.save(cart);
    }
}
