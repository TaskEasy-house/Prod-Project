package com.taskEasy.backend.dto;

import java.util.UUID;

public record CategoryResponse(
    UUID categoryId,
    String categoryName,
    String description
) {}

