package com.taskEasy.backend.entity;

import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime; import java.util.UUID;

@Entity @Table(name="customer_addresses") @Getter @Setter @NoArgsConstructor
public class CustomerAddress {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID addressId;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="customer_id",nullable=false) private User customer;
    @Column(nullable=false,length=250) private String addressLine;
    @Column(length=150) private String landmark;
    @Column(nullable=false,length=6) private String pincode;
    @Column(nullable=false,length=100) private String city;
    @Column(nullable=false,length=100) private String state;
    @CreationTimestamp @Column(nullable=false,updatable=false) private LocalDateTime createdAt;
}
