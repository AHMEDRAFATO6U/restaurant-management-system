package com.boot_demo1.resturant.model;


import com.boot_demo1.resturant.model.security.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends BaseEntity {



    @Column(unique=true)
    private String code ;
    @Column(nullable=false)
    private double totalPrice ;
    @Column(nullable=false)
    private int totalNumber ;


    @ManyToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user ;
}
