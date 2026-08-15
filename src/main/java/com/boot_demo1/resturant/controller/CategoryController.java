package com.boot_demo1.resturant.controller;

import com.boot_demo1.resturant.dto.CategoryDto;
import com.boot_demo1.resturant.exception.ResourceNotFoundException;
import com.boot_demo1.resturant.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

public class CategoryController {

  public final   CategoryService categoryService;


    @Operation(
            summary = "get all categories",
            description = "all categories in resturant"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status get all categories"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })
    @GetMapping("/all-categories")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }



    @Operation(
            summary = "create category"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status create category"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })
    @PostMapping("/create-category")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<CategoryDto> saveCategory (@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.saveCategory(categoryDto);
        return ResponseEntity.created(URI.create("/categories/" + savedCategory.getId())).body(savedCategory);    }


    @Operation(
            summary = "update category"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status update category"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })
    @PutMapping("/update-category")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<CategoryDto> updateCategory (@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.updateCategory(categoryDto);
        return ResponseEntity.ok(savedCategory);
        }




    @Operation(
            summary = "create List of  category"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status create List of category"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })
    @PostMapping("/create-categories")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<CategoryDto>> saveCategories (@Valid @RequestBody List<CategoryDto> categoryDtos) {
        List<CategoryDto> categoryDtoList = categoryService.saveListOfCategories(categoryDtos);
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }




    @Operation(
            summary = "update List of  category"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status update List of category"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })
    @PutMapping("/update-list-of-categories")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<CategoryDto>> updateListOfCategories (@Valid @RequestBody List<CategoryDto> categoryDtos) {

        List<CategoryDto> categoryDtoList = categoryService.updateListOfCategories(categoryDtos);
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }



    @Operation(
            summary = "Get Category By Category Id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status update List of category"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity <CategoryDto> getCategoryById(@Valid @PathVariable Long id) {
        CategoryDto categoryDto= categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }


    @Operation(
            summary = "Delete Category By ID",
            description = "Delete category by its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Category deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Map<String,Object>> deleteCategoryById(@Valid @PathVariable Long id) {
        CategoryDto categoryDto= categoryService.getCategoryById(id);
         categoryService.deleteCategoryById(id);
         Map<String,Object> map = new HashMap<>();
         map.put("category", categoryDto);
         map.put("message", "Category deleted successfully");
         map.put("status", "success");
         map.put("data", map);
         return new ResponseEntity<>(map, HttpStatus.OK);

    }





}
