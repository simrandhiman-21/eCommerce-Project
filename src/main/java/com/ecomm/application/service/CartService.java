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

import java.util.List;
import java.util.Optional;

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

       Optional<User> userOpt=userRepository.findById(Long.parseLong(userId));
       User user=userOpt.get();
       if(user!=null && user.getId()!=null) {
           cartItem.setUser(user);
       }else{
           throw new RuntimeException("User not found");
       }
       Optional<Product> productOpt=productRepository.findById(cartRequestBean.getProductId());
       Product product=productOpt.get();
       if(product!=null && product.getId()!=null) {
           cartItem.setProduct(product);
       }else{
           throw new RuntimeException("Product not found");
       }
       //if(product.getStockquantity()>0 && product.getStockquantity()>product.getStockquantity()){}

       // so untill now we have validated user exist and product exist and quantity exist now 2 options
       // product already exist in card , update quantity ,
       // create new cart
       
       cartItem.setQuantity(cartRequestBean.getQuantity());
       cartRepository.save(cartItem);
    }

//    public List<CartItem> getAllCartItems(){
//       return cartRepository.findAll();
//    }

}
