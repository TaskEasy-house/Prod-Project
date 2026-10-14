package com.taskEasy.backend.entity;

import jakarta.persistence.*; import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.util.UUID;

@Entity @Table(name="vendor_services",uniqueConstraints=@UniqueConstraint(name="uk_vendor_service_pair",columnNames={"vendor_id","service_id"}))
@Getter @Setter @NoArgsConstructor
public class VendorService {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID vendorServiceId;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="vendor_id",nullable=false) private VendorProfile vendor;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="service_id",nullable=false) private Service service;
}
