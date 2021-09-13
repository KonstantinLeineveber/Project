package com.example.servingwebcontent.repository.orderRepository;

import com.example.servingwebcontent.user.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
