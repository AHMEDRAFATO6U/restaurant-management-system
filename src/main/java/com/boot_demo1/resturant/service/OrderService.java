package com.boot_demo1.resturant.service;

import com.boot_demo1.resturant.dto.OrderRequestDTO;
import com.boot_demo1.resturant.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO requestDTO);

    OrderResponseDTO getOrderById(Long id);

    List<OrderResponseDTO> getAllOrders();

    OrderResponseDTO updateOrder(Long id, OrderRequestDTO requestDTO);

    void deleteOrder(Long id);
}
