package com.yerevan.yerevanshops.service.product;

import com.yerevan.yerevanshops.dto.ProductDto;
import com.yerevan.yerevanshops.model.Product;
import com.yerevan.yerevanshops.request.AddProductRequest;
import com.yerevan.yerevanshops.request.UpdateProductRequest;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Product addProduct(AddProductRequest product);

    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(UpdateProductRequest product, Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByName(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    Long getCountOfProducts();

    ProductDto convertToProductDto(Product product);

    List<ProductDto> convertToProductDtoList(List<Product> products);
}
