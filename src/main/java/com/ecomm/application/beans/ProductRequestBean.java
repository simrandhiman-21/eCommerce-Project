package com.ecomm.application.beans;

import lombok.Data;

@Data
public class ProductRequestBean {

    private String name;
    private String desciption;
    private Long price;
    private String stockquantity;
    private String img;

}
