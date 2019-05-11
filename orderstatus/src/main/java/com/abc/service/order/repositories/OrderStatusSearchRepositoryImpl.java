package com.abc.service.order.repositories;

import com.abc.service.order.domain.SearchResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderStatusSearchRepositoryImpl implements OrderStatusSearchRepository {

    private static final String SEARCH_BY_PHONE_NUMBER = "select s.orderid as orderId, u.status_desc as status from orderstatus s, statusmaster u " +
            "where s.status_id = u.status_id and s.phone_number = :phoneNumber and s.is_active = 1";

    private static final String SEARCH_BY_ORDER_NUMBER = "select s.orderid as orderId, u.status_desc as status from orderstatus s, statusmaster u " +
            "where s.status_id = u.status_id and s.orderid = :orderId and s.is_active = 1";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    OrderStatusSearchRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SearchResultDTO> searchByPhoneNumber(String phoneNumber) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("phoneNumber", phoneNumber);

        List<SearchResultDTO> searchResults = jdbcTemplate.query(SEARCH_BY_PHONE_NUMBER,
                queryParams,
                new BeanPropertyRowMapper<>(SearchResultDTO.class)
        );

        return searchResults;
    }

    @Override
    public List<SearchResultDTO> searchByOrderId(String orderId) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("orderId", orderId);

        List<SearchResultDTO> searchResults = jdbcTemplate.query(SEARCH_BY_ORDER_NUMBER,
                queryParams,
                new BeanPropertyRowMapper<>(SearchResultDTO.class)
        );

        return searchResults;
    }
}

