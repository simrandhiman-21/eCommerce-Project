package com.ecomm.application.service;

import com.ecomm.application.beans.CartModelMapper;
import com.ecomm.application.beans.CartRequestBean;
import com.ecomm.application.entity.CartItem;
import com.ecomm.application.entity.Product;
import com.ecomm.application.entity.User;
import com.ecomm.application.repository.CartRepository;
import com.ecomm.application.repository.ProductRepository;
import com.ecomm.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartModelMapper cartModelMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;


   public void addItem(String userId,CartRequestBean cartRequestBean){
       //CartItem cart=CartModelMapper.MapBeanToEntity(cartRequestBean);

       CartItem cartItem=new CartItem();

       User user=userRepository.findById(Long.parseLong(userId)).orElseThrow(()->new RuntimeException("User not found"));
       Product product=productRepository.findById(cartRequestBean.getProductId()).orElseThrow(()->new RuntimeException("Product not found"));
       cartItem.setUser(user);
       cartItem.setProduct(product);
       cartItem.setQuantity(cartRequestBean.getQuantity());
       cartRepository.save(cartItem);
    }
}
