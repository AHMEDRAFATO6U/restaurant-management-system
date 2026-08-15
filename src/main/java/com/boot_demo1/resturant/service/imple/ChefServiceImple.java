package com.boot_demo1.resturant.service.imple;

import com.boot_demo1.resturant.dto.ChefDTO;
import com.boot_demo1.resturant.exception.ResourceNotFoundException;
import com.boot_demo1.resturant.mappers.ChefMapper;
import com.boot_demo1.resturant.model.Chef;
import com.boot_demo1.resturant.repo.ChefRepo;
import com.boot_demo1.resturant.service.ChefService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ChefServiceImple implements ChefService {

    private final ChefRepo chefRepo;
    private final ChefMapper chefMapper;




    @Override
    public ChefDTO createChef(ChefDTO chefDTO) {
        if(chefRepo.existsByNameIgnoreCase(chefDTO.getName())) {
            throw new ResourceNotFoundException("Chef with name " + chefDTO.getName() + " already exists");
        }
        Chef chef = chefMapper.chefDTOToChef(chefDTO);
        chef.setId(null);
        Chef chefSaved = chefRepo.save(chef);
        return chefMapper.chefToChefDTO(chefSaved);

    }

    @Override
    public List<ChefDTO> getAllChefs() {
        List<Chef> chefs = chefRepo.findAll();
        if(chefs.isEmpty()) {
            throw new ResourceNotFoundException("No chefs found");
        }
        return chefs.stream().map(chefMapper::chefToChefDTO).collect(Collectors.toList());

    }

    @Override
    public ChefDTO getChefById(Long id) {
        Chef chef = chefRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chef with id " + id + " not found"));
        return chefMapper.chefToChefDTO(chef);
    }

    @Override
    public ChefDTO updateChef(Long id, ChefDTO chefDTO) {
        Chef chef = chefRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chef with id " + id + " not found"));
        chef.setName(chefDTO.getName());
        chef.setSpec(chefDTO.getSpec());
        chef.setLogoPath(chefDTO.getLogoPath());

        chef.setFacebookLink(chefDTO.getFacebookLink());
        chef.setTwitterLink(chefDTO.getTwitterLink());
        chef.setInstagramLink(chefDTO.getInstagramLink());

        Chef updatedChef = chefRepo.save(chef);
        return chefMapper.chefToChefDTO(updatedChef);
    }

    @Override
    public void deleteChefById(Long id) {
      Chef chef = chefRepo.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("Chef with id " + id + " not found"));

      chefRepo.delete(chef);

    }
}
