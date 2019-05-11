package com.abc.service.order.service;

import com.abc.service.order.domain.SearchResultDTO;
import com.abc.service.order.payload.StatusRequest;

import java.io.IOException;
import java.util.List;

public interface OrderStatusSearchService {
    public List<SearchResultDTO> searchByPhone(String phoneNumber);

    public List<SearchResultDTO> searchByOrderId(String orderId);

    public StatusRequest parseMsg(String msg) throws IOException;
}
