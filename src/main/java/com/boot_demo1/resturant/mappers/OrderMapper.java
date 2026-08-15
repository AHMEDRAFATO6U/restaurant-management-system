package com.boot_demo1.resturant.mappers;

import com.boot_demo1.resturant.dto.OrderResponseDTO;

import com.boot_demo1.resturant.model.Order;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderMapper {

    OrderResponseDTO toDTO(Order order);
//    List<OrderResponseDTO> toDTOList(List<Order> orders);
}