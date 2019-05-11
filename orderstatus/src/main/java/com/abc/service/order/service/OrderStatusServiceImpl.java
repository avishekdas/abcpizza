package com.abc.service.order.service;

import com.abc.service.order.domain.OrderStatus;
import com.abc.service.order.repositories.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public List<OrderStatus> listAll() {
        List<OrderStatus> clients = new ArrayList<>();
        orderStatusRepository.findAll().forEach(clients::add); //fun with Java 8
        return clients;
    }

    @Override
    public OrderStatus getById(Long id) {
        return orderStatusRepository.findById(id).orElse(null);
    }

    @Override
    public OrderStatus saveOrUpdate(OrderStatus client) {
        orderStatusRepository.save(client);
        return client;
    }

    @Override
    public void delete(Long id) {
        orderStatusRepository.deleteById(id);

    }
}
