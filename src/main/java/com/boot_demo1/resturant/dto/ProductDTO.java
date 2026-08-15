package com.boot_demo1.resturant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Long id;

    @NotEmpty(message = "not_empty.productName")
    @Size(min = 7, max = 50, message = "size.productName")
    private String productName;

    @NotEmpty(message = "not_empty.description")
    private String description;


    @NotEmpty(message = "not_empty.imagePath")
    private String imagePath;


    private double price;

    private Long categoryId;

    private String categoryName;
}
