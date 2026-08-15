package com.boot_demo1.resturant.model;



import com.boot_demo1.resturant.model.security.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contact_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo extends BaseEntity {



    @Column(name = "name", nullable = false, length = 100)
    private String name;


    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "subject", nullable = false, length = 150)
    private String subject;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;


    @ManyToOne
    private User user;




}