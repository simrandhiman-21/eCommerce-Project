package com.ecomm.application.controller;

import com.ecomm.application.beans.ProductRequestBean;
import com.ecomm.application.entity.Product;
import com.ecomm.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestBean product){
        productService.createProduct(product);
        return new ResponseEntity<>("Product added Successfully", HttpStatus.OK);
    }


}
