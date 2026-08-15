package com.boot_demo1.resturant.mappers;


import com.boot_demo1.resturant.dto.ChefDTO;
import com.boot_demo1.resturant.model.Chef;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChefMapper {

    ChefDTO chefToChefDTO(Chef chef);

    Chef chefDTOToChef(ChefDTO chefDTO);

    List<ChefDTO> chefListToChefDTOList(List<Chef> chefList);

    List<Chef> chefDTOListToChefList(List<ChefDTO> chefDTOList);
}
