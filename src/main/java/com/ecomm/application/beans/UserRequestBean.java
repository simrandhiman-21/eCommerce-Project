package com.ecomm.application.beans;

import com.ecomm.application.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserRequestBean {

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private UserAddressBean userAddressBean;

}
