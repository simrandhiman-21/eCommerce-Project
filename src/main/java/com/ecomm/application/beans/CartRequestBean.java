package com.ecomm.application.beans;

import com.ecomm.application.entity.Product;
import com.ecomm.application.entity.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CartRequestBean {
    private Long productId;
    private String quantity;
}
