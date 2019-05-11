package com.abc.service.order.kafka;

import com.abc.service.order.domain.OrderStatus;
import com.abc.service.order.domain.OrderStatusMessageBO;
import com.abc.service.order.domain.SearchResultDTO;
import com.abc.service.order.domain.StatusMaster;
import com.abc.service.order.repositories.OrderStatusRepository;
import com.abc.service.order.repositories.OrderStatusSearchRepository;
import com.abc.service.order.repositories.StatusMasterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

//@Component
class Consumer {
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    private StatusMasterRepository statusRepo;

    @Autowired
    public void setStatusRepo(StatusMasterRepository repository) {
        this.statusRepo = repository;
    }

    private OrderStatusSearchRepository ordStatSearchRepo;

    @Autowired
    public void setOrdStatSearchRepo(OrderStatusSearchRepository ordStatSearchRepo) {
        this.ordStatSearchRepo = ordStatSearchRepo;
    }

    private OrderStatusRepository ordStatRepo;

    @Autowired
    public void setOrdStatRepo(OrderStatusRepository ordStatRepo) {
        this.ordStatRepo = ordStatRepo;
    }

//    @KafkaListener(topics = "${orderupdate.topic}")
    public void processMessage(String message //,
//                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
//                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
//                               @Header(KafkaHeaders.OFFSET) List<Long> offsets
                               ) {
        //update database with the status of the order
        ObjectMapper mapper = new ObjectMapper();
        try {
            OrderStatusMessageBO incomingOrder = mapper.readValue(message, OrderStatusMessageBO.class);

            Optional<StatusMaster> status = statusRepo.findByStatusdesc(incomingOrder.getStatus());
            if(status.isPresent()) {

                List<SearchResultDTO> resultDto = ordStatSearchRepo.searchByOrderId(incomingOrder.getOrderId());
                if(resultDto != null && resultDto.get(0) != null) {

                    Optional<OrderStatus> orderstatusOptional = ordStatRepo.findByOrderid(incomingOrder.getOrderId());
                    OrderStatus orderStatus = orderstatusOptional.get();
                    orderStatus.setOrder_data(message);
                    orderStatus.setPhone_number(incomingOrder.getPhoneNumber());
                    orderStatus.setStatus_id(status.get().getStatus_id());

                    ordStatRepo.saveAndFlush(orderStatus);
                } else {
                    OrderStatus orderStatus = new OrderStatus();
                    orderStatus.setOrder_data(message);
                    orderStatus.setPhone_number(incomingOrder.getPhoneNumber());
                    orderStatus.setOrderid(incomingOrder.getOrderId());
                    orderStatus.setStatus_id(status.get().getStatus_id());
                    orderStatus.setCreatedby("InBound");

                    ordStatRepo.saveAndFlush(orderStatus);
                }
            } else {
                log.info("Status in incoming message not found");
            }
        } catch (IOException e) {
            log.info("Exception Parsing incoming message");
        }

//        log.info("%s-%d[%d] \"%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), message);
    }
}
