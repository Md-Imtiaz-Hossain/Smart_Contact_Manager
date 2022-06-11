package com.imtiaz.package_all.dto;


import com.imtiaz.package_all.EntityModel.ContactEntity;

public class ContactDTO {
    private Long cid;
    private String name;
    private String phoneNumber;
    private String description;
    private String image;
    private String nickName;
    private String email;
    private String work;
    private Long userId;

    public ContactDTO() {
        super();
    }

    public ContactDTO(Long cid, String name, String phoneNumber, String description, String image, String nickName,
                      String email, String work, Long userId) {
        super();
        this.cid = cid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.image = image;
        this.nickName = nickName;
        this.email = email;
        this.work = work;
        this.userId = userId;
    }

    public Long getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getWork() {
        return work;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static ContactDTO of(ContactEntity contactEntity) {
        return new ContactDTO(contactEntity.getCid(), contactEntity.getName(), contactEntity.getPhoneNumber(),
                contactEntity.getDescription(), contactEntity.getImage(), contactEntity.getNickName(), contactEntity.getEmail(), contactEntity.getWork(), contactEntity.getUser().getId());
    }

    @Override
    public String toString() {
        return "ContactDTO [cid=" + cid + ", name=" + name + ", phoneNumber=" + phoneNumber + ", description="
                + description + ", image=" + image + ", nickName=" + nickName + ", email=" + email + ", work=" + work
                + "]";
    }


}
