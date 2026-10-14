package com.taskEasy.backend.entity;

import jakarta.persistence.*; import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.util.UUID;
@Entity @Table(name="vendor_service_areas",uniqueConstraints=@UniqueConstraint(name="uk_vendor_pincode",columnNames={"vendor_id","pincode"}))
@Getter @Setter @NoArgsConstructor
public class VendorServiceArea {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID vendorServiceAreaId;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="vendor_id",nullable=false) private VendorProfile vendor;
    @Column(nullable=false,length=6) private String pincode;
}
