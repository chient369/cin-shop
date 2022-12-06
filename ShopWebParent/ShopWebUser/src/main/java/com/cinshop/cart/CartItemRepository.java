package com.cinshop.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinshop.common.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem , Integer>{

}
