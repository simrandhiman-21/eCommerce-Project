package com.ecomm.application.beans;

import lombok.Data;

@Data
public class UserResponseBean {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private UserAddressBean userAddressBean;

}
