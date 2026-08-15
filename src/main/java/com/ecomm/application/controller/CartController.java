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
        cartService.addItem(userId,cartRequestBean);
        return  new ResponseEntity<>("Product added in Cart Successfully",HttpStatus.CREATED);
    }

//    @GetMapping("/getAllCartItems")
//    public List<CartItem> getAllCartItems(){
//        return cartService.getAllCartItems();
//    }



}
