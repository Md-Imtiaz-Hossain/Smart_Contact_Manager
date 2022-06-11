package com.imtiaz.package_all.EntityModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cId;

    private String firstName;

    private String lastName;

    private String email;

    private String work;

    private String phone;

    private String description;

    private String imageUrl;

    @ManyToOne
    private User user;

}
