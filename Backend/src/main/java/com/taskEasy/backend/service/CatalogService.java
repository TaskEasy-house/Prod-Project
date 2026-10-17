package com.taskEasy.backend.service;

import com.taskEasy.backend.dto.CategoryResponse;
import com.taskEasy.backend.dto.ServiceResponse;
import com.taskEasy.backend.repository.CategoryRepository;
import com.taskEasy.backend.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CatalogService {

    private final CategoryRepository categoryRepository;
    private final ServiceRepository serviceRepository;

    public CatalogService(
        CategoryRepository categoryRepository,
        ServiceRepository serviceRepository) {
        this.categoryRepository = categoryRepository;
        this.serviceRepository = serviceRepository;
    }

    public List<CategoryResponse> getActiveCategories() {
        return categoryRepository.findByActiveTrueOrderByCategoryNameAsc()
            .stream()
            .map(category -> new CategoryResponse(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getDescription()))
            .toList();
    }

    public List<ServiceResponse> getActiveServices(UUID categoryId) {
        return categoryRepository.findById(categoryId)
            .filter(category -> category.isActive())
            .map(category -> serviceRepository
                .findByCategory_CategoryIdAndActiveTrueOrderByServiceNameAsc(
                    category.getCategoryId())
                .stream()
                .map(service -> new ServiceResponse(
                    service.getServiceId(),
                    service.getServiceName(),
                    service.getDescription(),
                    service.getFixedPrice(),
                    service.getEstimatedDurationMinutes()))
                .toList())
            .orElseThrow(() -> new IllegalArgumentException(
                "Category not found: " + categoryId));
    }
}

