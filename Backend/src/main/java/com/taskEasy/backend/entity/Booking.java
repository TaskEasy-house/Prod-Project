package com.taskEasy.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal; import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name="bookings") @Getter
@Setter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID) private UUID bookingId;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="customer_id",nullable=false) private User customer;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="address_id",nullable=false) private CustomerAddress address;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="service_id",nullable=false) private Service service;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="vendor_id") private VendorProfile vendor;
    @Column(length=2000) private String problemDescription;
    @Column(nullable=false) private LocalDateTime appointmentWindowStart;
    @Column(nullable=false) private LocalDateTime appointmentWindowEnd;
    @Column(nullable=false,precision=10,scale=2) private BigDecimal agreedPrice;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=40) private BookingStatus bookingStatus=BookingStatus.REQUESTED;
    @CreationTimestamp
    @Column(nullable=false,updatable=false) private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable=false) private LocalDateTime updatedAt;
}
