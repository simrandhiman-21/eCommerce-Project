package com.ecomm.application.beans;

import lombok.Data;

@Data
public class OrderItemBean {
    private Long productId;
    private String quantity;
    private String price;
}
