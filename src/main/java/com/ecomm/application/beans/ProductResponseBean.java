package com.ecomm.application.beans;

import lombok.Data;

@Data
public class ProductResponseBean {
    private Long id;
    private String name;
    private String desciption;
    private Long price;
    private String stockquantity;
    private String img;
}
