package com.boot_demo1.resturant.service.imple;

import com.boot_demo1.resturant.dto.ContactInfoDto;
import com.boot_demo1.resturant.exception.DuplicateResourceException;
import com.boot_demo1.resturant.exception.ResourceNotFoundException;
import com.boot_demo1.resturant.mappers.ContactInfoMapper;
import com.boot_demo1.resturant.model.ContactInfo;
import com.boot_demo1.resturant.repo.ContactInfoRepo;
import com.boot_demo1.resturant.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ContactInfoServiceImple implements ContactInfoService {

    private final  ContactInfoRepo contactInfoRepo;
    private final  ContactInfoMapper contactInfoMapper;



    @Override
    public ContactInfoDto saveContactinfo(ContactInfoDto contactInfoDto) {
        if(contactInfoRepo.existsByEmailIgnoreCase(contactInfoDto.getEmail())){
            throw new DuplicateResourceException("Contact name already exists with this email" + contactInfoDto.getEmail());
        }
        ContactInfo contactInfo = contactInfoMapper.contactInfoDtoToContactInfo(contactInfoDto);
        contactInfo.setId(null);
        ContactInfo savedContactInfo = contactInfoRepo.save(contactInfo);
        return contactInfoMapper.contactInfoToContactInfoDto(savedContactInfo);

    }

    @Override
    public ContactInfoDto updateContactInfo(ContactInfoDto contactInfoDto, Long id) {

        ContactInfo contactInfo = contactInfoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id " + id));

        contactInfo.setName(contactInfoDto.getName());
        contactInfo.setEmail(contactInfoDto.getEmail());
        contactInfo.setSubject(contactInfoDto.getSubject());
        ContactInfo updatedContactInfo = contactInfoRepo.save(contactInfo);
        return contactInfoMapper.contactInfoToContactInfoDto(updatedContactInfo);

    }

    @Override
    public List<ContactInfoDto> getAllContactInfo() {

        List<ContactInfo> contactInfos = contactInfoRepo.findAll();
        if(contactInfos.isEmpty()){
            throw new ResourceNotFoundException("No contacts found");
        }
        return contactInfoMapper.contactInfoListToContactInfoDtoList(contactInfos);

    }

    @Override
    public ContactInfoDto getContactInfoById(Long id) {
        ContactInfo contactInfo = contactInfoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id " + id));
        return contactInfoMapper.contactInfoToContactInfoDto(contactInfo);
    }

    @Override
    public void deleteById(Long id) {
        if (contactInfoRepo.existsById(id)) {
            contactInfoRepo.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Contact not found with id " + id);
        }


    }

}
