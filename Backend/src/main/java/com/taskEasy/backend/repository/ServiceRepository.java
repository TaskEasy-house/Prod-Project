package com.taskEasy.backend.repository;

import com.taskEasy.backend.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceRepository
    extends JpaRepository<Service, UUID> {

    boolean existsByCategory_CategoryIdAndServiceNameIgnoreCase(
        UUID categoryId, String serviceName);

    List<Service> findByCategory_CategoryIdAndActiveTrueOrderByServiceNameAsc(
        UUID categoryId);
}
