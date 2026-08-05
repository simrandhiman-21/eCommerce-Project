package com.ecomm.application.controller;

import com.ecomm.application.beans.ProductRequestBean;
import com.ecomm.application.beans.ProductResponseBean;
import com.ecomm.application.entity.Product;
import com.ecomm.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseBean> getProductById(@PathVariable Long id){
         return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseBean>> getAllProduct(){
        return new ResponseEntity<>(productService.getAllProduct(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Product updateProductById(@PathVariable Long id,@RequestParam String updatevalue){
        return productService.updateProductById(id,updatevalue);
    }

    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam String keyword){
        return productService.searchProduct(keyword);
    }
}
