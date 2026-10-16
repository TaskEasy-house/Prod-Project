package com.taskEasy.backend.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ServiceResponse(
    UUID serviceId,
    String serviceName,
    String description,
    BigDecimal fixedPrice,
    Integer estimatedDurationMinutes
) {}
