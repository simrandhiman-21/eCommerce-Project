package com.ecomm.application.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="Product_name")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String desciption;
    private Long price;
    private String stockquantity;
    private String img;
}
