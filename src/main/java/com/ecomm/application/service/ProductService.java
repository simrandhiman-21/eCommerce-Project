package com.ecomm.application.service;

import com.ecomm.application.beans.ProductModelMapper;
import com.ecomm.application.beans.ProductRequestBean;
import com.ecomm.application.entity.Product;
import com.ecomm.application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductModelMapper productModelMapper;

    public void createProduct(ProductRequestBean productRequestBean){
       Product newproduct=productModelMapper.mapProductToDb(productRequestBean);
       productRepository.save(newproduct);
    }
}
