package com.ecomm.application.beans;

import com.ecomm.application.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductModelMapper {

    public Product mapProductToDb(ProductRequestBean productRequestBean){
        Product newProduct=new Product();
        newProduct.setName(productRequestBean.getName());
        newProduct.setDesciption(productRequestBean.getDesciption());
        newProduct.setStockquantity(productRequestBean.getStockquantity());
        newProduct.setImg(productRequestBean.getImg());
        newProduct.setPrice(productRequestBean.getPrice());
        return newProduct;
    }
}
