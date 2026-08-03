package com.ecomm.application.beans;

import lombok.Data;

@Data
public class UserAddressBean {

    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;

}
