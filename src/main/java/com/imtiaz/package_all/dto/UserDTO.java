package com.imtiaz.package_all.dto;

import com.smartcontact.entities.UserEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class UserDTO {
	private Long id;
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 5, max = 30, message = "Name length should be 5")
	private String name;
	@NotEmpty(message = "Email should not be empty ")
	private String email;
	@NotEmpty(message = "Password should not be empty ")
	private String password;
	private String image;
	private String about;
	private String role;
	private boolean enabled;
	private String confirmPassword;
	private List<ContactDTO> contactDTOList = new ArrayList<ContactDTO>();

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(Long id, String name, String email, String password, String image, String about, String role,
			boolean enabled, List<ContactDTO> contactDto) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.image = image;
		this.about = about;
		this.role = role;
		this.enabled = enabled;
		this.contactDTOList = contactDto;
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

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getImage() {
		return image;
	}

	public String getAbout() {
		return about;
	}

	public String getRole() {
		return role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public List<ContactDTO> getContactDTOList() {
		return contactDTOList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setImage(String imageUrl) {
		this.image = imageUrl;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setContactDTOList(List<ContactDTO> contactDto) {
		this.contactDTOList = contactDto;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public static UserDTO of(UserEntity entity) {
		return new UserDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getPassword(),
				entity.getImage(), entity.getAbout(), entity.getRole(), false,
				entity.getContactList() == null ? new ArrayList<ContactDTO>() :entity.getContactList().stream().map(ContactDTO::of).collect(Collectors.toList()));
	}

}
