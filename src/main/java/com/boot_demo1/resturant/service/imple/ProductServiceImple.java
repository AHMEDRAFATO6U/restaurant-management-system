package com.boot_demo1.resturant.service.imple;

import com.boot_demo1.resturant.dto.ProductDTO;
import com.boot_demo1.resturant.exception.ResourceNotFoundException;
import com.boot_demo1.resturant.mappers.ProductMapper;
import com.boot_demo1.resturant.model.Category;
import com.boot_demo1.resturant.model.Product;
import com.boot_demo1.resturant.repo.CategoryRepo;
import com.boot_demo1.resturant.repo.ProductRepo;
import com.boot_demo1.resturant.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductServiceImple implements ProductService {

    private final ProductMapper productMapper;
    private final  ProductRepo productRepo;
    private final CategoryRepo categoryRepo;


    @Override
    public List<ProductDTO> getproductByCategoryId(Long id) {
        categoryRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "CATEGORY ID: " + id + " NOT FOUND"
                ));

        List<Product> products = productRepo.getProductByCategoryId(id);
        return products.stream()
                .map(productMapper ::productToProductDTO)
                .collect(Collectors.toList());



    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        if(productRepo.existsByProductNameIgnoreCase(productDTO.getProductName())) {
            throw new ResourceNotFoundException("Product with name " + productDTO.getProductName() + " already exists");
        }

        Product product = productMapper.productDTOToProduct(productDTO);
        product.setId(null);
        productRepo.save(product);
        return productMapper.productToProductDTO(product);
    }



    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product =productRepo.findById(productDTO.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Product with id " + productDTO.getId() + " NOT FOUND"));

        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getDescription());
        product.setImagePath(productDTO.getImagePath());
        product.setPrice(productDTO.getPrice());

        if(productDTO.getCategoryId() != null) {
            Category category = categoryRepo.findById(productDTO.getCategoryId())
                    .orElseThrow(()-> new ResourceNotFoundException("Category with id " + productDTO.getCategoryId() + " NOT FOUND"));

                     product.setCategory(category);
        }

        Product updatedProduct = productRepo.save(product);
        return productMapper.productToProductDTO(updatedProduct);
    }

    @Override
    public ProductDTO getByProductId(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product with id " + id + " NOT FOUND"));
        return productMapper.productToProductDTO(product);



    }

    @Override
    public List<ProductDTO> getAllProductId() {

        List<Product> products = productRepo.findAll();
        if(products.isEmpty()){
            throw new ResourceNotFoundException("No products found");
        }
        return productMapper.productListToProductDTOList(products);
    }

    @Override
    public void deleteByProductId(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product with id " + id + " does not exist"));
        productRepo.delete(product);

    }

    @Override
    public List<ProductDTO> saveProducts(List<ProductDTO> productDTOs) {
        List<Product> products = productDTOs.stream()
                .map(productDTO -> {
                    Product product = productMapper.productDTOToProduct(productDTO);
                    product.setId(null);
                    return product;
                })
                .collect(Collectors.toList());

        List<Product> savedProducts = productRepo.saveAll(products);
        return productMapper.productListToProductDTOList(savedProducts);


    }

    @Override
    public List<ProductDTO> updateProducts(List<ProductDTO> productDTOs) {
        Set<Long> productIds = productDTOs.stream()
                .map(ProductDTO::getId)
                .collect(Collectors.toSet());

        List<Product> existingProducts = productRepo.findAllById(productIds);

        Map<Long, Product> productMap = existingProducts.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        List<Product> updatedProducts = productDTOs.stream()
                .map(dto -> {
                    Product product = productMap.get(dto.getId());
                    if (product == null) {
                        throw new ResourceNotFoundException("Product with id " + dto.getId() + " does not exist");
                    }
                    product.setProductName(dto.getProductName());
                    product.setProductDescription(dto.getDescription());
                    product.setImagePath(dto.getImagePath());
                    product.setPrice(dto.getPrice());

                    if (dto.getCategoryId() != null) {
                        Category category = categoryRepo.findById(dto.getCategoryId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                        "CATEGORY ID: " + dto.getCategoryId() + " NOT FOUND"));
                        product.setCategory(category);
                    }

                    return product;
                }).collect(Collectors.toList());

        List<Product> savedProducts = productRepo.saveAll(updatedProducts);
        return productMapper.productListToProductDTOList(savedProducts);
    }

    @Override
    public List<ProductDTO> getProductByCategoryName(String categoryName) {
        List<Product> products = productRepo.getProductByCategory_CategoryName(categoryName);
        if(products.isEmpty()){
            throw new ResourceNotFoundException("No products found");
        }
        return products.stream()
                .map(productMapper ::productToProductDTO)
                .collect(Collectors.toList());
    }
}

