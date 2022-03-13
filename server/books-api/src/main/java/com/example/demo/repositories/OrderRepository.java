package com.example.demo.repositories;

import com.example.demo.entities.Order;
import com.example.demo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findAllByCustomer_Id(Long id);
  List<Order> findAllByCustomer_IdAndStatus(Long id, Status status);
}
