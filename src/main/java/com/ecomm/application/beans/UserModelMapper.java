package com.ecomm.application.beans;

import com.ecomm.application.entity.Address;
import com.ecomm.application.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserModelMapper {

    public User userMapper(UserRequestBean user){

        User newuser= new User();

        newuser.setFirstname(user.getFirstname());
        newuser.setLastname(user.getLastname());
        newuser.setEmail(user.getEmail());
        newuser.setPhone(user.getPhone());

        Address address=new Address();

        address.setStreet((user.getUserAddressBean().getStreet()));
        address.setCity(user.getUserAddressBean().getCity());
        address.setState(user.getUserAddressBean().getState());
        address.setCountry(user.getUserAddressBean().getCountry());
        address.setZipcode(user.getUserAddressBean().getZipcode());

        newuser.setAddress(address);

        return newuser;
    }

    public UserResponseBean userResponseMapper(User user){
        UserResponseBean userResponseBean=new UserResponseBean();
        userResponseBean.setId(user.getId());
        userResponseBean.setFirstname(user.getFirstname());
        userResponseBean.setLastname(user.getLastname());
        userResponseBean.setEmail(user.getEmail());

        UserAddressBean addressBean =new UserAddressBean();
        addressBean .setStreet(user.getAddress().getStreet());
        addressBean .setCity(user.getAddress().getCity());
        addressBean .setState(user.getAddress().getState());
        addressBean .setCountry(user.getAddress().getCountry());
        addressBean .setZipcode(user.getAddress().getZipcode());

        userResponseBean.setUserAddressBean(addressBean );

        return userResponseBean;
    }
}
