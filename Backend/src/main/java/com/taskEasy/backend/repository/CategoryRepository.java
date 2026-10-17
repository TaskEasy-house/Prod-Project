package com.taskEasy.backend.repository;

import com.taskEasy.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository
    extends JpaRepository<Category, UUID> {

    Optional<Category> findByCategoryNameIgnoreCase(String categoryName);

    List<Category> findByActiveTrueOrderByCategoryNameAsc();
}
