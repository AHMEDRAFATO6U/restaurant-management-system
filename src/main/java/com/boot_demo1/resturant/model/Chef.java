package com.boot_demo1.resturant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chef")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chef extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String spec;

    @Column(nullable = false)
    private String logoPath;

    @Column(nullable = true)
    private String facebookLink;

    @Column(nullable = true)
    private String twitterLink;

    @Column(nullable = true)
    private String instagramLink;
}
