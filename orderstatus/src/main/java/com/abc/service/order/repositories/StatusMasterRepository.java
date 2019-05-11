package com.abc.service.order.repositories;

import com.abc.service.order.domain.StatusMaster;
import com.abc.service.order.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusMasterRepository extends JpaRepository<StatusMaster, Long> {
    Optional<StatusMaster> findByStatusdesc(String status);
}
