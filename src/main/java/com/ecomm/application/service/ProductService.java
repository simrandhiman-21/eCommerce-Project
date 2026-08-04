package com.ecomm.application.service;

import com.ecomm.application.beans.ProductModelMapper;
import com.ecomm.application.beans.ProductRequestBean;
import com.ecomm.application.beans.ProductResponseBean;
import com.ecomm.application.entity.Product;
import com.ecomm.application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public ProductResponseBean getProductById(Long id){
        Product product=productRepository.findById(id).orElseThrow(()->new RuntimeException("Product NOT FOUND "+ id));
        ProductResponseBean responseProduct=productModelMapper.mapDbToDTO(product);
        return responseProduct;
    }

    public List<ProductResponseBean> getAllProduct(){
        List<Product> productlist=productRepository.findAll();
        ArrayList<ProductResponseBean> listofProductResponse=new ArrayList<>();
        for(Product product:productlist) {
            ProductResponseBean responseProduct = productModelMapper.mapDbToDTO(product);
            listofProductResponse.add(responseProduct);
        }
        return listofProductResponse;
    }
}
