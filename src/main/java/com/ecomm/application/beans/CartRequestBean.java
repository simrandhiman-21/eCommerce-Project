package com.ecomm.application.beans;

import com.ecomm.application.entity.Product;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CartRequestBean {

    private Long id;
    private Product product;
    private String quantity;
    private Long price;

}
