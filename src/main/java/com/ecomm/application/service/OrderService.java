package com.ecomm.application.service;

import com.ecomm.application.beans.OrderItemBean;
import com.ecomm.application.beans.OrderResponseBean;
import com.ecomm.application.entity.*;
import com.ecomm.application.repository.CartRepository;
import com.ecomm.application.repository.OrderRepository;
import com.ecomm.application.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    public ResponseEntity<OrderResponseBean> placeOrder(String userId){

        //validate for cartItem
        //validate for user
        // calculate price
        //create order

        Order order=new Order();

        Optional<User> Optuser=userRepository.findById(Long.parseLong(userId));
        if (Optuser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        User user=Optuser.get();

        List<CartItem> cartItemList=cartRepository.findByUser(user);
        if (cartItemList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<OrderItem> orderItemsList=new ArrayList<>();

        long sum=0L;
        for(CartItem EachcartItem:cartItemList){
            sum+=Long.parseLong(String.valueOf(EachcartItem.getPrice())) * Long.parseLong(EachcartItem.getQuantity());

            OrderItem orderItem=new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(EachcartItem.getProduct());
            orderItem.setQuantity(EachcartItem.getQuantity());
            orderItem.setPrice(String.valueOf(EachcartItem.getPrice()));
            orderItemsList.add(orderItem);
        }
        order.setUser(user);
        order.setStatus(OrderStatus.SHIPPED);
        order.setAmount(String.valueOf(sum));
        order.setItems(orderItemsList);

        //save order
        orderRepository.save(order);
        cartRepository.deleteAll(cartItemList); // clear the cart after order placed

        OrderResponseBean orderResponseBean = mapToOrderResponse(order);
        return ResponseEntity.ok(orderResponseBean);
    }

    public OrderResponseBean mapToOrderResponse(Order order){
        OrderResponseBean orderResponseBean=new OrderResponseBean();
        orderResponseBean.setId(order.getId());
        orderResponseBean.setStatus(order.getStatus());
        orderResponseBean.setAmount(order.getAmount());
        orderResponseBean.setItems(mapToOrderItemBeans(order.getItems()));
        return orderResponseBean;
    }
    private List<OrderItemBean> mapToOrderItemBeans(List<OrderItem> orderItems){
        List<OrderItemBean> orderItemBeans = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            OrderItemBean orderItemBean = new OrderItemBean();
            orderItemBean.setProductId(orderItem.getProduct().getId());
            orderItemBean.setQuantity(orderItem.getQuantity());
            orderItemBean.setPrice(orderItem.getPrice());
            orderItemBeans.add(orderItemBean);
        }
        return orderItemBeans;
    }
}
