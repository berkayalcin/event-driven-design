package com.estore.productservice.core.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "productLookup")
public class ProductLookup {
    @Id
    @Column(unique = true)
    private String id;

    @Column(unique = true)
    private String title;
}
