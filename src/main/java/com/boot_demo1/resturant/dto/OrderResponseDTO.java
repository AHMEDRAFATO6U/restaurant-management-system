package com.boot_demo1.resturant.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

    private Long id;
    private String code;
    private Double totalPrice;
    private Integer totalNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private List<ProductDTO> products;
}
