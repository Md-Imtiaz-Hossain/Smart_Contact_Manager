package com.imtiaz.package_all.Service;


import com.imtiaz.package_all.dto.ContactDTO;
import com.imtiaz.package_all.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;


public interface UserService {

    public UserDTO save(UserDTO userDTO);

    public UserDTO getUserDetailsByUserName(String username);

    public boolean saveContact(String userName, MultipartFile file,  ContactDTO contactdto);
    public boolean  update(UserDTO userDTO,MultipartFile file);

    public boolean deleteUser(String name);


}
