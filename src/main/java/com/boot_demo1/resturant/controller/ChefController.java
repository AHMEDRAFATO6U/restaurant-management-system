package com.boot_demo1.resturant.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.boot_demo1.resturant.dto.ChefDTO;
import com.boot_demo1.resturant.exception.ResourceNotFoundException;
import com.boot_demo1.resturant.service.ChefService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chefs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

public class ChefController {


    private final ChefService chefService;

    @Operation(summary = "create chef")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Http Status create chef"),
            @ApiResponse(responseCode = "500", description = "Http Status internal server error",
                    content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class)))
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ChefDTO> createChef(@RequestBody @Valid ChefDTO chefDTO) {
        ChefDTO chef = chefService.createChef(chefDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(chef);
    }

    @Operation(summary = "get all chefs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status get all chefs"),
            @ApiResponse(responseCode = "500", description = "Http Status internal server error",
                    content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class)))
    })
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<ChefDTO>> getAllChefs() {
        List<ChefDTO> chefs = chefService.getAllChefs();
        return ResponseEntity.status(HttpStatus.OK).body(chefs);
    }

    @Operation(summary = "get chef by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status get chef"),
            @ApiResponse(responseCode = "500", description = "Http Status internal server error",
                    content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class)))
    })
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ChefDTO> getChefById(@PathVariable Long id) {
        ChefDTO chefDTO = chefService.getChefById(id);
        return ResponseEntity.status(HttpStatus.OK).body(chefDTO);
    }

    @Operation(summary = "update chef")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status update chef"),
            @ApiResponse(responseCode = "500", description = "Http Status internal server error",
                    content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class)))
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ChefDTO> updateChef(@PathVariable Long id, @RequestBody @Valid ChefDTO chefDTO) {
        ChefDTO chef = chefService.updateChef(id, chefDTO);
        return ResponseEntity.status(HttpStatus.OK).body(chef);
    }

    @Operation(summary = "delete chef by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status delete chef"),
            @ApiResponse(responseCode = "500", description = "Http Status internal server error",
                    content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class)))
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Map<String, Object>> deleteChefById(@PathVariable Long id) {
        ChefDTO chefDTO = chefService.getChefById(id);
        chefService.deleteChefById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Chef '" + chefDTO.getName() + "' deleted successfully!");
        response.put("deletedId", id);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.ok(response);
    }
}
