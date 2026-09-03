package com.ecomm.application.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id",nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private Product product;

    private String quantity;
    private String price;
}
