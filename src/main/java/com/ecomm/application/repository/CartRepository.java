package com.ecomm.application.repository;

import com.ecomm.application.entity.CartItem;
import org.hibernate.query.criteria.JpaCollectionJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem,Long> {
}
