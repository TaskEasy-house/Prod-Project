package com.taskEasy.backend.controller;

import com.taskEasy.backend.dto.CategoryResponse;
import com.taskEasy.backend.dto.ServiceResponse;
import com.taskEasy.backend.service.CatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<CategoryResponse> getCategories() {
        return catalogService.getActiveCategories();
    }

    @GetMapping("/{categoryId}/services")
    public List<ServiceResponse> getServices(
        @PathVariable UUID categoryId) {
        return catalogService.getActiveServices(categoryId);
    }
}
