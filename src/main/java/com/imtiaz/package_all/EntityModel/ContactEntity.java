package com.imtiaz.package_all.EntityModel;

import com.imtiaz.package_all.dto.ContactDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name= "contact")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cid;
    private String name;
    private String phoneNumber;
    @Column(length = 500)
    private String description;
    private String image;
    private String nickName;
    private String email;
    private String work;
    @ManyToOne
    private UserEntity userEntity;
    public ContactEntity() {
        super();
        // TODO Auto-generated constructor stub
    }



    public ContactEntity(Long cid, String name, String phoneNumber, String description, String image, String nickName,
                         String email, String work) {
        super();
        this.cid = cid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.image = image;
        this.nickName = nickName;
        this.email = email;
        this.work = work;

    }



    public Long getCid() {
        return cid;
    }
    public void setCid(Long cid) {
        this.cid = cid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getWork() {
        return work;
    }
    public void setWork(String work) {
        this.work = work;
    }
    public UserEntity getUser() {
        return userEntity;
    }
    public void setUser(UserEntity user) {
        this.userEntity = user;
    }

    public static ContactEntity of(ContactDTO contactDTO) {
        return new ContactEntity(contactDTO.getCid(), contactDTO.getName(), contactDTO.getPhoneNumber(),
                contactDTO.getDescription(), contactDTO.getImage(),contactDTO.getNickName(),contactDTO.getEmail(),contactDTO.getWork());
    }

}
