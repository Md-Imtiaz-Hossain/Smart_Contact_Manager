package com.imtiaz.package_all.Service;


import com.imtiaz.package_all.EntityModel.ContactEntity;
import com.imtiaz.package_all.EntityModel.UserEntity;
import com.imtiaz.package_all.Repository.UserRepositories;
import com.imtiaz.package_all.dto.ContactDTO;
import com.imtiaz.package_all.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepositories userRepositories;

	@Override
	public UserDTO save(UserDTO userDTO) {
		userDTO.setImage("profile.png");
		return UserDTO.of(userRepositories.save(UserEntity.of(userDTO)));

	}

	@Override
	public UserDTO getUserDetailsByUserName(String username) {
		// TODO Auto-generated method stub
		return UserDTO.of(userRepositories.getUserByUserName(username));
	}

	@Override
	public boolean saveContact(String username, MultipartFile file, ContactDTO contactdto) {
		UserEntity userEntity = userRepositories.getUserByUserName(username);
		ContactEntity contactEntity = ContactEntity.of(contactdto);
		contactEntity.setUser(userEntity);

		try {

			if (file.isEmpty()) {
				System.out.println("file is empty");
				contactEntity.setImage("contact.png");
			} else {

				String fileName = file.getOriginalFilename();
				contactEntity.setImage(fileName);
				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("File uploaded");

			}
		} catch (Exception e) {
			return false;

		}
		userEntity.getContactList().add(contactEntity);
		userRepositories.save(userEntity);
		return true;
	}

	@Override
	public boolean update(UserDTO userDTO, MultipartFile file) {
		UserEntity oldUserEntity = userRepositories.findById(userDTO.getId()).get();
		

		try {
			if (!file.isEmpty()) {
				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + userDTO.getImage());
				File deleteFile = new File(path.toString());
				if (deleteFile.delete()) {
					System.out.println(file.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
				String fileName = file.getOriginalFilename();
				userDTO.setImage(fileName);
				Path newFilePath = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), newFilePath, StandardCopyOption.REPLACE_EXISTING);

			} else {

				userDTO.setImage(oldUserEntity.getImage());

			}

		
			userRepositories.save(UserEntity.of(userDTO));

		} catch (Exception e) {
			return false;

		}
		return true;
	}

	@Override
	public boolean deleteUser(String name) {
		try {
			userRepositories.deleteUserByUserName(name);
			return true;
			
		}catch(Exception e) {
			System.out.println("exception "+e.getLocalizedMessage());
			
			
		}
		return false;
	}

}
