package com.abc.service.order.repositories;

import com.abc.service.order.domain.SearchResultDTO;

import java.util.List;

public interface OrderStatusSearchRepository {
    List<SearchResultDTO> searchByPhoneNumber(String phoneNumber);
    List<SearchResultDTO> searchByOrderId(String orderId);
}

