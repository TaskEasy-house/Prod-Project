package com.taskEasy.backend.config;

import com.taskEasy.backend.entity.Category;
import com.taskEasy.backend.entity.Service;
import com.taskEasy.backend.repository.CategoryRepository;
import com.taskEasy.backend.repository.ServiceRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedCatalog(
        CategoryRepository categoryRepository,
        ServiceRepository serviceRepository) {

        return args -> {
            seedCategory(categoryRepository, serviceRepository,
                "Carpenter", "Carpentry and furniture services");

            seedCategory(categoryRepository, serviceRepository,
                "Plumber", "Plumbing and water-related repairs");

            seedCategory(categoryRepository, serviceRepository,
                "Electrician", "Electrical installation and repairs");

            seedService(categoryRepository, serviceRepository,
                "Carpenter", "Door repair",
                "Repair of common door issues",
                "399.00", 60);

            seedService(categoryRepository, serviceRepository,
                "Carpenter", "Furniture assembly",
                "Assembly of household furniture",
                "599.00", 90);

            seedService(categoryRepository, serviceRepository,
                "Plumber", "Tap repair",
                "Repair of leaking or faulty taps",
                "299.00", 45);

            seedService(categoryRepository, serviceRepository,
                "Plumber", "Pipe leakage repair",
                "Repair of common household pipe leaks",
                "499.00", 60);

            seedService(categoryRepository, serviceRepository,
                "Electrician", "Fan installation",
                "Installation of a household ceiling fan",
                "399.00", 60);

            seedService(categoryRepository, serviceRepository,
                "Electrician", "Switchboard repair",
                "Repair of a faulty electrical switchboard",
                "299.00", 45);
        };
    }

    private void seedCategory(
        CategoryRepository categoryRepository,
        ServiceRepository serviceRepository,
        String categoryName,
        String description) {

        Category category = categoryRepository
            .findByCategoryNameIgnoreCase(categoryName)
            .orElseGet(() -> {
                Category newCategory = new Category();
                newCategory.setCategoryName(categoryName);
                newCategory.setDescription(description);
                newCategory.setActive(true);

                return categoryRepository.save(newCategory);
            });
    }

    private void seedService(
        CategoryRepository categoryRepository,
        ServiceRepository serviceRepository,
        String categoryName,
        String serviceName,
        String description,
        String price,
        int durationMinutes) {

        Category category = categoryRepository
            .findByCategoryNameIgnoreCase(categoryName)
            .orElseThrow(() -> new IllegalStateException(
                "Category not found: " + categoryName));

        boolean exists = serviceRepository
            .existsByCategory_CategoryIdAndServiceNameIgnoreCase(
                category.getCategoryId(), serviceName);

        if (!exists) {
            Service service = new Service();
            service.setCategory(category);
            service.setServiceName(serviceName);
            service.setDescription(description);
            service.setFixedPrice(new BigDecimal(price));
            service.setEstimatedDurationMinutes(durationMinutes);
            service.setActive(true);

            serviceRepository.save(service);
        }
    }
}
