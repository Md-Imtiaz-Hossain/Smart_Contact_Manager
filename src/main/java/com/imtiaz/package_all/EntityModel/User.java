package com.imtiaz.package_all.EntityModel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name field is required !!")
    @Size(min =2, max = 20, message = "Min 2 and maximum 20 are allowed.")
    private String firstName;

    private String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email !! ")
    private String email;


    @NotBlank(message = "Password field is required !!")
    @Size(min =6, max = 200, message = "Min 6 and maximum 200 are allowed.")
    private String password;

    private String role;

    private boolean enable;

    private String imageUrl;

    @Column(length = 500)
    private String about;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
//    @JoinColumn(name = "Contact_CId")
    private List<Contact> contacts;

}
