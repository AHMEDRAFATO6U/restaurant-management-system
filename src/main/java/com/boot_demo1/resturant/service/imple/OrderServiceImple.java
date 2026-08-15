package com.boot_demo1.resturant.service.imple;
import com.boot_demo1.resturant.mappers.OrderMapper;
import com.boot_demo1.resturant.service.OrderService;
import org.springframework.stereotype.Service;
import com.boot_demo1.resturant.dto.OrderRequestDTO;
import com.boot_demo1.resturant.dto.OrderResponseDTO;
import com.boot_demo1.resturant.exception.ResourceNotFoundException;
import com.boot_demo1.resturant.model.Order;
import com.boot_demo1.resturant.model.Product;
import com.boot_demo1.resturant.repo.OrderRepo;
import com.boot_demo1.resturant.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImple implements OrderService {

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO requestDTO) {

        if (orderRepo.existsByCode(requestDTO.getCode())) {
            throw new RuntimeException("Order with code '" + requestDTO.getCode() + "' already exists");
        }

        List<Product> products = productRepo.findAllById(requestDTO.getProductIds());

        if (products.isEmpty()) {
            throw new RuntimeException("No products found for the given IDs");
        }

        double totalPrice = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        int totalNumber = products.size();

        Order order = new Order();
        order.setCode(requestDTO.getCode());
        order.setTotalPrice(totalPrice);
        order.setTotalNumber(totalNumber);
        order.setProducts(products);

        Order savedOrder = orderRepo.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return orderMapper.toDTO(order);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepo.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO requestDTO) {

        Order existingOrder = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        if (!existingOrder.getCode().equals(requestDTO.getCode())) {
            if (orderRepo.existsByCode(requestDTO.getCode())) {
                throw new RuntimeException("Order with code '" + requestDTO.getCode() + "' already exists");
            }
            existingOrder.setCode(requestDTO.getCode());
        }

        List<Product> products = productRepo.findAllById(requestDTO.getProductIds());

        if (products.isEmpty()) {
            throw new RuntimeException("No products found for the given IDs");
        }

        double totalPrice = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        int totalNumber = products.size();

        existingOrder.setProducts(products);
        existingOrder.setTotalPrice(totalPrice);
        existingOrder.setTotalNumber(totalNumber);

        Order updatedOrder = orderRepo.save(existingOrder);
        return orderMapper.toDTO(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        orderRepo.delete(order);
    }
}