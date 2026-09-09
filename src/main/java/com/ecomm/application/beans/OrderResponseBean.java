package com.ecomm.application.beans;

import com.ecomm.application.entity.OrderItem;
import com.ecomm.application.entity.OrderStatus;
import com.ecomm.application.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponseBean {

    private Long id;
    private OrderStatus status;
    //private User user;
    private String Amount;
    private List<OrderItemBean> items;

}
