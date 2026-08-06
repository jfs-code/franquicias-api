package com.franquicias.api.infrastructure.adapter.out.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product", uniqueConstraints = {
                @UniqueConstraint(name = "uk_product_name_branch", columnNames = { "name", "branch_id" })
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 100)
        private String name;

        @Column(nullable = false)
        private Integer stock;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "branch_id", nullable = false)
        private BranchEntity branch;

}
