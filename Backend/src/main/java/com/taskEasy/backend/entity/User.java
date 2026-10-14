package com.taskEasy.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp; import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime; import java.util.UUID;
@Entity
@Table(name="users") @Getter
@Setter
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID) private UUID userId;
    @Column(nullable=false,length=120) private String name;
    @Column(nullable=false,unique=true,length=16) private String phone;
    @Column(unique=true,length=254) private String email;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private UserRole role;
    @Column(nullable=false) private boolean active=true;
    @CreationTimestamp
    @Column(nullable=false,updatable=false) private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable=false) private LocalDateTime updatedAt;
}
