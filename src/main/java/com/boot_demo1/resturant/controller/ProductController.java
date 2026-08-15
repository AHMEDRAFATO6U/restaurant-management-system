package com.boot_demo1.resturant.controller;

import com.boot_demo1.resturant.dto.ProductDTO;
import com.boot_demo1.resturant.exception.ResourceNotFoundException;
import com.boot_demo1.resturant.service.ProductService;
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

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

public class ProductController {
    private final ProductService productService;

    @Operation(
            summary = "GET PRODUCTS BY CATEGORY ID"
                    ,description = "ALL PRODUCTS WITH CATEGORY ID "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status get product by category id"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })
    @GetMapping("/category/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<ProductDTO>> getProductsByCategoryId(@PathVariable Long id) {
        List<ProductDTO> products = productService.getproductByCategoryId(id);
        return new ResponseEntity <>(products, HttpStatus.OK);

    }


    @Operation(
            summary = "create product"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status create product"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })
    @PostMapping("/create-product")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        ProductDTO product =productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @Operation(
            summary = "update product"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status update product"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })
    @PutMapping("/update-product")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody @Valid ProductDTO productDTO) {
        ProductDTO product =productService.updateProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }


    @Operation(
            summary = "get  product by Id "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status get product"
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
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getByProductId(id);
        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }



    @Operation(
            summary = "get all   product  "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status get all product"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })
    @GetMapping("/")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOs = productService.getAllProductId();
        return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
    }



    @Operation(
            summary = "delete  product by Id "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status delete product"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String,Object>> deleteProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getByProductId(id);
         productService.deleteByProductId(id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Product '" + productDTO.getProductName() + "' deleted successfully!");
        response.put("deletedId", id);
        response.put("deletedName", productDTO.getProductName());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.ok(response);


    }


    @Operation(
            summary = " create list of products"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status save products"
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            )

    })
    @PostMapping("/list-products")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<ProductDTO>> saveProducts(@RequestBody List<ProductDTO> productDTOs) {
        List<ProductDTO> productDTOS = productService.saveProducts(productDTOs);
        return ResponseEntity.status(HttpStatus.OK).body(productDTOS);
    }


    @Operation(
            summary = " update  list of products"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status update products"
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            )

    })
    @PutMapping("/products")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<ProductDTO>> updateProducts(@RequestBody List<ProductDTO> productDTOs) {
        List<ProductDTO> productDTOS = productService.updateProducts(productDTOs);
        return ResponseEntity.status(HttpStatus.OK).body(productDTOS);
    }

    @Operation(
            summary = " get all product by category name  "
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status get all products"
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            )

    })
    @GetMapping("/category-name/{categoryName}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<ProductDTO>> getProductByCategoryName(@PathVariable String categoryName) {
        List<ProductDTO> productDTOs = productService.getProductByCategoryName(categoryName);
        return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
    }






}
