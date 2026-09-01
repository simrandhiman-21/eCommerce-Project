package com.ecomm.application.repository;

import com.ecomm.application.entity.CartItem;
import com.ecomm.application.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecomm.application.entity.User;
@Repository
public interface CartRepository extends JpaRepository<CartItem,Long> {

    public CartItem findByProductAndUser(Product product,User user);

}
