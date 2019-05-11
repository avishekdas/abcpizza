package com.abc.service.order.service;

import com.abc.service.order.domain.OrderStatus;

import java.util.List;

public interface OrderStatusService {

    List<OrderStatus> listAll();

    OrderStatus getById(Long id);

    OrderStatus saveOrUpdate(OrderStatus campaign);

    void delete(Long id);
}
