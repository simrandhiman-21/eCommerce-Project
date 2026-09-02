package com.ecomm.application.controller;

import com.ecomm.application.beans.CartRequestBean;
import com.ecomm.application.entity.CartItem;
import com.ecomm.application.entity.Product;
import com.ecomm.application.entity.User;
import com.ecomm.application.repository.CartRepository;
import com.ecomm.application.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<String> addItem(@RequestHeader ("User-ID")String userId, @RequestBody CartRequestBean cartRequestBean){
        Boolean itemadded=cartService.addItem(userId,cartRequestBean);
        if(itemadded){
            return new ResponseEntity<>("Product added in Cart Successfully",HttpStatus.CREATED);
        }
        return  new ResponseEntity<>("User not found OR Product not fount OR Product Out of Stock",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{productId}")
    public boolean DeleteItemFromCart(@RequestHeader("User-ID") String userId, @PathVariable String productId){
        return cartService.deleteItemFromCart(userId,productId);
    }
    @GetMapping
    public List<CartItem> fetchCartItemByUserId(@RequestHeader("User-ID") String userId){
           return cartService.findCartItemByUserId(userId);
    }
    @GetMapping("/all")
    public List<CartItem> FetchAllCartItems(){
        return cartService.fetchCartItemAll();
    }
}
