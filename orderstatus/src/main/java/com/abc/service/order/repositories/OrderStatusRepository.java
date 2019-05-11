package com.abc.service.order.repositories;

import com.abc.service.order.domain.OrderStatus;
import com.abc.service.order.domain.SearchResultDTO;
import com.abc.service.order.domain.StatusMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    Optional<OrderStatus> findByOrderid(String status);
}

