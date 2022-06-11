package com.imtiaz.package_all.Service;



import com.imtiaz.package_all.dto.ContactDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;


public interface ContactService {

    public Page<ContactDTO> findContactListByUserId(String userName, Integer Page);

    public ContactDTO findContactDetail(Long cid);

    public void deleteContactById(Long cId, String userName);

    public boolean  updateContact(ContactDTO contactDTO,MultipartFile file, String  userName);

}
