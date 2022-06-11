package com.imtiaz.package_all.EntityModel;


import com.imtiaz.package_all.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String image;
    @Column(length = 500)
    private String about;
    private String role;
    private boolean enabled;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "userEntity")
    private List<ContactEntity> contactEntityList = new ArrayList<ContactEntity>();

    public UserEntity() {
        super();
        // TODO Auto-generated constructor stub
    }


    public UserEntity(Long id, String name, String email, String password, String image, String about, String role,
                      boolean enabled, List<ContactEntity> contactEntityList) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
        this.about = about;
        this.role = role;
        this.enabled = enabled;
        this.contactEntityList = contactEntityList;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imageUrl) {
        this.image = image;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<ContactEntity> getContactList() {
        return contactEntityList;
    }

    public void setContactList(List<ContactEntity> contactList) {
        this.contactEntityList = contactList;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", imageUrl="
                + image + ", about=" + about + ", role=" + role + ", enabled=" + enabled + ", contactList="
                + contactEntityList + "]";
    }
    public static UserEntity of(UserDTO userDTO) {
        return new UserEntity(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(),
                userDTO.getImage(), userDTO.getAbout(), userDTO.getRole(), false,
                userDTO.getContactDTOList().stream().map(ContactEntity::of).collect(Collectors.toList()));
    }



}
