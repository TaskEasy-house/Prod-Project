package com.taskEasy.backend.entity;

import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp; import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime; import java.util.UUID;

@Entity @Table(name="vendor_profiles") @Getter @Setter @NoArgsConstructor
public class VendorProfile {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID vendorId;
    @OneToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="user_id",nullable=false,unique=true) private User user;
    private Integer experienceYears;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private VerificationStatus verificationStatus=VerificationStatus.PENDING;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AvailabilityStatus availabilityStatus=AvailabilityStatus.UNAVAILABLE;
    @Column(length=1000) private String bio;
    @CreationTimestamp @Column(nullable=false,updatable=false) private LocalDateTime createdAt;
    @UpdateTimestamp @Column(nullable=false) private LocalDateTime updatedAt;
}
