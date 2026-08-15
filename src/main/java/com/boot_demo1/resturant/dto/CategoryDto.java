package com.boot_demo1.resturant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        name = "Category Dto",
        description = "category dto contains (id,name,logo,flag,products)"
)
public class CategoryDto {

    private Long  id;
    @NotEmpty(message = "not_empty.name")
    @Size(min = 7, max = 50, message = "size.categoryName")
    @Schema(
            name = "categoryName",
            description = "name for category (string)",
            example = "taher amin"
    )
    private String categoryName ;

    @NotEmpty(message = "not_empty.categoryLogo")
    private String categoryLogo ;

    @NotEmpty(message = "not_empty.categoryFlag")
    private String categoryFlag;


    @Schema(
            name = "products",
            description = "list of products (List<Product>)"
    )
    private List<ProductDTO> productList;
}
