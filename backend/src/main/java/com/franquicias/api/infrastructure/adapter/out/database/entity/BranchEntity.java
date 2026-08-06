package com.franquicias.api.infrastructure.adapter.out.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "branch", uniqueConstraints = {
                @UniqueConstraint(name = "uk_branch_name_franchise", columnNames = { "name", "franchise_id" })
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 100)
        private String name;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "franchise_id", nullable = false)
        private FranchiseEntity franchise;

        @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
        @Builder.Default
        private List<ProductEntity> products = new ArrayList<>();

}
