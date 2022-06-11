package com.imtiaz.package_all.Service;


import com.imtiaz.package_all.EntityModel.ContactEntity;
import com.imtiaz.package_all.EntityModel.UserEntity;
import com.imtiaz.package_all.Repository.ContactReposotories;
import com.imtiaz.package_all.Repository.UserRepositories;
import com.imtiaz.package_all.dto.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;


@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	UserRepositories userRepositories;
	@Autowired
	ContactReposotories contactRepositories;

	@Override
	public Page<ContactDTO> findContactListByUserId(String userName, Integer page) {
		// TODO Auto-generated method stub

		Pageable pageable = PageRequest.of(page, 3);
		UserEntity userEntity = userRepositories.getUserByUserName(userName);
		Page<ContactEntity> contactEntityList = contactRepositories.findContactByUser(userEntity.getId(), pageable);
		return contactEntityList.map(ContactDTO::of);
	}

	@Override
	public ContactDTO findContactDetail(Long cid) {

		Optional<ContactEntity> contactOptional = contactRepositories.findById(cid);
		return ContactDTO.of(contactOptional.get());
	}

	@Override
	public void deleteContactById(Long cId, String userName) {
		Optional<ContactEntity> contactOptional = contactRepositories.findById(cId);
		ContactEntity contactEntity = contactOptional.get();
		contactEntity.setUserEntity(null);
		UserEntity userEntity = userRepositories.getUserByUserName(userName);
		List<ContactEntity> contactEntities = userEntity.getContactList();
		contactEntities.remove(contactEntity);
		userEntity.setContactList(contactEntities);

		try {
			File saveFile = new ClassPathResource("static/img").getFile();
			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + contactEntity.getImage());
			File file = new File(path.toString());
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}
		} catch (Exception e) {
			System.out.println("Failed to Delete image !!");
		}

		userRepositories.save(userEntity);

	}

	@Override
	public boolean updateContact(ContactDTO contactDTO,MultipartFile file, String  userName) {

			ContactDTO oldContact = findContactDetail(contactDTO.getCid());
			
				 try { 
					 if(!file.isEmpty()) {
					 File saveFile = new ClassPathResource("static/img").getFile();
					 Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + oldContact.getImage());
					 File deleteFile  = new File(path.toString());
		             if(deleteFile.delete()) { 
		                System.out.println(file.getName() + " is deleted!");
		             } else {
		                System.out.println("Delete operation is failed.");
		                }
		             String fileName = file.getOriginalFilename();
		             contactDTO.setImage(fileName);
		             Path newFilePath = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
					 Files.copy(file.getInputStream(), newFilePath, StandardCopyOption.REPLACE_EXISTING);
		          
				
			}else {
				
				contactDTO.setImage(oldContact.getImage());
				
			}
			
			UserEntity userEntity = userRepositories.getUserByUserName(userName);
			ContactEntity contactEntity = ContactEntity.of(contactDTO);
			contactEntity.setUserEntity(userEntity);
			contactRepositories.save(contactEntity);
			
		}catch(Exception e) {
			return false;
			
		}
	 return true;
	}
	
	

}
