package com.ecomm.application.service;

import com.ecomm.application.beans.CartModelMapper;
import com.ecomm.application.beans.CartRequestBean;
import com.ecomm.application.entity.CartItem;
import com.ecomm.application.entity.Product;
import com.ecomm.application.entity.User;
import com.ecomm.application.repository.CartRepository;
import com.ecomm.application.repository.ProductRepository;
import com.ecomm.application.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartModelMapper cartModelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    public boolean addItem(String userId, CartRequestBean cartRequestBean) {
        // check if user exist or not
        Optional<User> userOpt = userRepository.findById(Long.parseLong(userId));
        if (userOpt.isEmpty()) return false;
        User user = userOpt.get();

        // check if product exist or not
        Optional<Product> productOpt = productRepository.findById(cartRequestBean.getProductId());
        if (productOpt.isEmpty()) return false;
        Product product = productOpt.get();

        // check stockquantity
        if (Long.parseLong(product.getStockquantity()) < Long.parseLong(cartRequestBean.getQuantity())) {
            return false;
        }
        // so untill now we have validated user exist and product exist and quantity exist now 2 options
        // product already exist in card , update quantity ,
        // create new cart

        CartItem cartItemexist = cartRepository.findByProductAndUser(product, user);
        if (cartItemexist != null) {
            cartItemexist.setQuantity(cartRequestBean.getQuantity());
            cartItemexist.setPrice(product.getPrice() * Long.parseLong(cartRequestBean.getQuantity()));
            cartRepository.save(cartItemexist);
        } else {
            CartItem newcartItem = new CartItem();
            newcartItem.setUser(user);
            newcartItem.setProduct(product);
            newcartItem.setQuantity(cartRequestBean.getQuantity());
            cartItemexist.setPrice(product.getPrice() * Long.parseLong(cartRequestBean.getQuantity()));
            cartRepository.save(newcartItem);
        }
        return true;
    }

    public boolean deleteItemFromCart(String userId, String productId) {

//        Optional<User> OptUser = userRepository.findById(Long.parseLong(userId));
//        if (OptUser.isEmpty()) return false;
//        User user = OptUser.get();
//
//        Optional<Product> OptProduct = productRepository.findById(Long.parseLong(productId));
//        if (OptProduct.isEmpty()) return false;
//        Product product = OptProduct.get();
//
////        CartItem cartItem = cartRepository.findByProductAndUser(product, user);
////        if (cartItem == null) return false;
////        cartRepository.delete(cartItem);
//
//        cartRepository.deleteByProductAndUser(product,user);
//        return true;

        Optional<User> OptUser = userRepository.findById(Long.parseLong(userId));
        Optional<Product> OptProduct = productRepository.findById(Long.parseLong(productId));
        if(OptProduct.isPresent() && OptUser.isPresent()) {
            cartRepository.deleteByProductAndUser(OptProduct.get(),OptUser.get());
            return true;
        }
        return false;
    }

    public List<CartItem> findCartItemByUserId(String userId){
        Optional<User> OptUser=userRepository.findById(Long.parseLong(userId));
        if(OptUser.isEmpty()) return List.of();
        User user=OptUser.get();
        return cartRepository.findByUser(user);
    }

    public List<CartItem> fetchCartItemAll(){
        return cartRepository.findAll();
    }
}
