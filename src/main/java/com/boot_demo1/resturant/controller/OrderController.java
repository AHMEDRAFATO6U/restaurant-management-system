package com.boot_demo1.resturant.controller;

import com.boot_demo1.resturant.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boot_demo1.resturant.dto.OrderRequestDTO;
import com.boot_demo1.resturant.dto.OrderResponseDTO;
import com.boot_demo1.resturant.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/V1/orders")
@RequiredArgsConstructor
@Tag(name = "Order Management", description = "APIs for managing orders")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Create new order")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status Create new order"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO requestDTO) {
        OrderResponseDTO createdOrder = orderService.createOrder(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }




    @Operation(summary = "Get order by ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status Get order by ID"
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
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        OrderResponseDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @Operation(summary = "Get all orders")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status Get all orders"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "Update order")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status Update  order"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ResourceNotFoundException.class)
                    )
            ),
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<OrderResponseDTO> updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequestDTO requestDTO) {
        OrderResponseDTO updatedOrder = orderService.updateOrder(id, requestDTO);
        return ResponseEntity.ok(updatedOrder);
    }

    @Operation(summary = "Delete order")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status Delete  order"
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
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
