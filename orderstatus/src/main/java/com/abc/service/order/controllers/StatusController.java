package com.abc.service.order.controllers;

import com.abc.service.order.domain.SearchResultDTO;
import com.abc.service.order.payload.OrderStatusDetails;
import com.abc.service.order.payload.StatusRequest;
import com.abc.service.order.payload.StatusResponse;
import com.abc.service.order.service.OrderStatusSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/orderstatus")
public class StatusController {
    private static final Logger log = LoggerFactory.getLogger(StatusController.class);

    private OrderStatusSearchService orderStatusSearchService;

    @Autowired
    public void setReportService(OrderStatusSearchService orderStatusSearchService) {
        this.orderStatusSearchService = orderStatusSearchService;
    }

    @PostMapping(path = "/find")
    @ResponseBody
    public StatusResponse getStatusByPhoneNo(@RequestBody String body){
        StatusResponse statusResponse = new StatusResponse();
        StatusRequest statusRequest = null;
        try {
            statusRequest = orderStatusSearchService.parseMsg(body);

            String phoneNumber = statusRequest.getPhonenumber();
            String orderNumber = statusRequest.getOrderid();

            if(phoneNumber != null && !"".equalsIgnoreCase(phoneNumber)) {
                //return hardcoded
                /*List<OrderStatusDetails> orderStatusDtlsList = new ArrayList<OrderStatusDetails>();
                OrderStatusDetails temp = new OrderStatusDetails("12345", "BAKING");
                OrderStatusDetails temp1 = new OrderStatusDetails("987654", "ON ITS WAY");
                orderStatusDtlsList.add(temp);
                orderStatusDtlsList.add(temp1);
                ObjectMapper mapper = new ObjectMapper();
                statusResponse.setStatus(mapper.writeValueAsString(orderStatusDtlsList));
                statusResponse.setErrorFlag(false);*/

                List<SearchResultDTO> resultDTOS = orderStatusSearchService.searchByPhone(phoneNumber);
                if(resultDTOS != null) {
                    OrderStatusDetails ordStatDtls = null;
                    List<OrderStatusDetails> orderStatusDtlsList = new ArrayList<OrderStatusDetails>();
                    for(SearchResultDTO order : resultDTOS) {
                        ordStatDtls = new OrderStatusDetails(order.getOrderId(), order.getStatus());
                        orderStatusDtlsList.add(ordStatDtls);
                    }
                    ObjectMapper mapper = new ObjectMapper();
                    statusResponse.setStatus(mapper.writeValueAsString(orderStatusDtlsList));
                    statusResponse.setErrorFlag(false);
                } else {
                    statusResponse.setStatus("No Data Found");
                    statusResponse.setErrorFlag(true);
                }
            } else if(orderNumber != null && !"".equalsIgnoreCase(orderNumber)) {
                List<SearchResultDTO> resultDTOS = orderStatusSearchService.searchByOrderId(orderNumber);
                if(resultDTOS != null && resultDTOS.get(0) != null) {
                    OrderStatusDetails ordStatDtls = null;
                    SearchResultDTO result = resultDTOS.get(0);
                    ordStatDtls.setOrderId(result.getOrderId());
                    ordStatDtls.setStatus(result.getStatus());
                    ObjectMapper mapper = new ObjectMapper();
                    statusResponse.setStatus(mapper.writeValueAsString(ordStatDtls));
                    statusResponse.setErrorFlag(false);
                } else {
                    statusResponse.setStatus("No Data Found");
                    statusResponse.setErrorFlag(true);
                }
            } else {
                log.info("Search input null or Blank");
                statusResponse.setStatus("ERR-INPUT: Search input null or Blank");
                statusResponse.setErrorFlag(true);
            }
        } catch (IOException e) {
            log.info("Exception occured while parsing");
            statusResponse.setStatus("ERR-INPUT: Exception occured while parsing");
            statusResponse.setErrorFlag(true);
        }

        return statusResponse;
    }

    @GetMapping("/health")
    @ResponseBody
    public String getHealth(){
        return "API is Running";
    }
}
