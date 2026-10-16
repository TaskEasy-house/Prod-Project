package com.taskEasy.backend.entity;

import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity @Table(name="categories")
@Getter @Setter @NoArgsConstructor

public class Category {
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID categoryId;
    @Column(nullable=false,unique=true,length=80) private String categoryName;
    @Column(length=500) private String description;
    @Column(nullable=false) private boolean active=true;
}
