package com.ecomm.application.controller;

import com.ecomm.application.beans.OrderResponseBean;
import com.ecomm.application.beans.ProductResponseBean;
import com.ecomm.application.service.OrderService;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@NoArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderResponseBean> OrderPlace(@RequestHeader("X-User-Id") String userId) {
        return orderService.placeOrder(userId);
    }

}
