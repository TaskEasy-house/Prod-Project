package com.taskEasy.backend.entity;

import jakarta.persistence.*; import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.math.BigDecimal; import java.util.UUID;

@Entity @Table(name="services") @Getter @Setter @NoArgsConstructor
public class Service {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID serviceId;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="category_id",nullable=false) private Category category;
    @Column(nullable=false,length=120) private String serviceName;
    @Column(nullable=false,length=1000) private String description;
    @Column(nullable=false,precision=10,scale=2) private BigDecimal fixedPrice;
    private Integer estimatedDurationMinutes;
    @Column(nullable=false) private boolean active=true;
}
