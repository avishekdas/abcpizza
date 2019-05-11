package com.abc.service.order.service;

import com.abc.service.order.repositories.OrderStatusSearchRepository;
import com.abc.service.order.domain.SearchResultDTO;
import com.abc.service.order.payload.StatusRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OrderStatusSearchServiceImpl implements OrderStatusSearchService {

    private OrderStatusSearchRepository repository;

    @Autowired
    public void setRepository(OrderStatusSearchRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SearchResultDTO> searchByPhone(String phoneNumber) {
        List<SearchResultDTO> searchResults = repository.searchByPhoneNumber(phoneNumber);
        return searchResults;
    }

    @Override
    public List<SearchResultDTO> searchByOrderId(String orderId) {
        List<SearchResultDTO> searchResults = repository.searchByOrderId(orderId);
        return searchResults;
    }

    @Override
    public StatusRequest parseMsg(String msg) throws IOException {
        StatusRequest obj = new StatusRequest();
        ObjectMapper mapper = new ObjectMapper();
        obj = mapper.readValue(msg, StatusRequest.class);
        return  obj;

    }
}
