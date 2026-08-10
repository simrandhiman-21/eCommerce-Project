package com.ecomm.application.controller;

import com.ecomm.application.beans.CartRequestBean;
import com.ecomm.application.entity.Product;
import com.ecomm.application.repository.CartRepository;
import com.ecomm.application.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("api/Cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping
    public ResponseEntity<String> addItem(CartRequestBean cartRequestBean){
        cartService.addItem(cartRequestBean);
        return  new ResponseEntity<>("Product added in Cart Successfully",HttpStatus.OK);
    }

}
