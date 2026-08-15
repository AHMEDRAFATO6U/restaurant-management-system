package com.boot_demo1.resturant.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateDTO {

    @NotNull(message = "Order ID is required")
    private Long id;

    private String code;
    private List<Long> productIds;
}
