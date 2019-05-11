package com.abc.service.order.domain;

import com.abc.service.order.domain.audit.UserDateAudit;

import javax.persistence.*;

@Entity
@Table(name = "orderstatus")
public class OrderStatus extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_status_id;
    private Long status_id;
    private String order_data;
    private String phone_number;
    private String orderid;

    public Long getOrder_status_id() {
        return order_status_id;
    }

    public void setOrder_status_id(Long order_status_id) {
        this.order_status_id = order_status_id;
    }

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public String getOrder_data() {
        return order_data;
    }

    public void setOrder_data(String order_data) {
        this.order_data = order_data;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}
