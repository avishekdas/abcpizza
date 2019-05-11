package com.abc.service.order.domain;

import javax.persistence.*;

@Entity
@Table(name = "statusmaster")
public class StatusMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long status_id;
    private Long statusdesc;
    private String entity_version;
    private String is_active;

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public Long getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(Long statusdesc) {
        this.statusdesc = statusdesc;
    }

    public String getEntity_version() {
        return entity_version;
    }

    public void setEntity_version(String entity_version) {
        this.entity_version = entity_version;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
}
