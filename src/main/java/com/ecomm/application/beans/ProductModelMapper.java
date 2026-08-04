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

    public ProductResponseBean mapDbToDTO(Product product){

        ProductResponseBean productResponseBean=new ProductResponseBean();
        productResponseBean.setId(product.getId());
        productResponseBean.setName(product.getName());
        productResponseBean.setDesciption(product.getDesciption());
        productResponseBean.setPrice(product.getPrice());
        productResponseBean.setStockquantity(product.getStockquantity());
        productResponseBean.setImg(product.getImg());
        return productResponseBean;

    }
}
