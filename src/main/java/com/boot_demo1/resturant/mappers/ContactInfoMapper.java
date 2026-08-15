package com.boot_demo1.resturant.mappers;

import com.boot_demo1.resturant.dto.ContactInfoDto;
import com.boot_demo1.resturant.model.ContactInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {

    ContactInfoDto contactInfoToContactInfoDto(ContactInfo contactInfo);
    ContactInfo contactInfoDtoToContactInfo(ContactInfoDto contactInfoDto);
    List<ContactInfoDto> contactInfoListToContactInfoDtoList(List<ContactInfo> contactInfoList);
    List<ContactInfo> contactInfoDtoListToContactInfoList(List<ContactInfoDto> contactInfoDtoList);
}
