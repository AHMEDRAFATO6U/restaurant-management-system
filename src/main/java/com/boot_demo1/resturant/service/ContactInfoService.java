package com.boot_demo1.resturant.service;

import com.boot_demo1.resturant.dto.ContactInfoDto;
import jakarta.validation.Valid;

import java.util.List;

public interface ContactInfoService {
    ContactInfoDto saveContactinfo(@Valid ContactInfoDto contactInfoDto);

    ContactInfoDto updateContactInfo(@Valid ContactInfoDto contactInfoDto, Long id);

    List<ContactInfoDto> getAllContactInfo();

    ContactInfoDto getContactInfoById(Long id);

    void deleteById(Long id);
}
