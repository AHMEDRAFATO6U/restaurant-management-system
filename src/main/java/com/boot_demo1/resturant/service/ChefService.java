package com.boot_demo1.resturant.service;

import com.boot_demo1.resturant.dto.ChefDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ChefService {
    ChefDTO createChef(@Valid ChefDTO chefDTO);

    List<ChefDTO> getAllChefs();

    ChefDTO getChefById(Long id);

    ChefDTO updateChef(Long id, @Valid ChefDTO chefDTO);

    void deleteChefById(Long id);
}
