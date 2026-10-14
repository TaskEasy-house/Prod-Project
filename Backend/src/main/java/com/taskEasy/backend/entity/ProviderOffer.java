package com.taskEasy.backend.entity;

import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime; import java.util.UUID;

@Entity @Table(name="provider_offers",uniqueConstraints=@UniqueConstraint(name="uk_booking_vendor_offer",columnNames={"booking_id","vendor_id"}))
@Getter @Setter @NoArgsConstructor
public class ProviderOffer {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID offerId;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="booking_id",nullable=false) private Booking booking;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="vendor_id",nullable=false) private VendorProfile vendor;
    @Column(nullable=false) private LocalDateTime offeredAt;
    @Column(nullable=false) private LocalDateTime expiresAt;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private OfferStatus offerStatus=OfferStatus.PENDING;
    @CreationTimestamp @Column(nullable=false,updatable=false) private LocalDateTime createdAt;
}
