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
import java.util.Optional;

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

    public Product updateProductById(Long id,String updatevalue){
        Optional<Product> product=productRepository.findById(id);
        product.get().setName(updatevalue);
        productRepository.save(product.get());
        return product.get();
    }

    public List<Product> searchProduct(String keyword){
        //return productRepository.findByName(keyword);
        return productRepository.findByKeyword(keyword);
    }
}
