package com.yerevan.yerevanshops.controller;

import com.yerevan.yerevanshops.model.Product;
import com.yerevan.yerevanshops.request.AddProductRequest;
import com.yerevan.yerevanshops.request.UpdateProductRequest;
import com.yerevan.yerevanshops.response.ApiResponse;
import com.yerevan.yerevanshops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("Success!", productService.convertToProductDtoList(products)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);

            return ResponseEntity.ok(new ApiResponse("Success!", productService.convertToProductDto(product)));
        } catch (Exception ex) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not found!", null));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product newProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Success!", newProduct));
        } catch (Exception ex) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Something went Wrong!", ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest product) {
        try {
            Product updatedProduct = productService.updateProduct(product, id);
            return ResponseEntity.ok(new ApiResponse("Success!", updatedProduct));
        } catch (Exception ex) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not found!", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Success!", null));
        } catch (Exception ex) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not found!", null));
        }
    }

    @GetMapping("/name")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam String name) {
        try {
            List<Product> products = productService.getProductsByName(name);
            if (products.isEmpty()) {
//                return ResponseEntity.notFound().build();
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not found!", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success!", products));
        } catch (Exception ex) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not found!", null));
        }
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse> getCountOfProducts() {
        try {
            var count = productService.getCountOfProducts();
            return ResponseEntity.ok(new ApiResponse("Success!", count));
        } catch (Exception ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Something went Wrong!", ex.getMessage()));
        }
    }

    @GetMapping("/category")
    public ResponseEntity<ApiResponse> getProductByCategory(@RequestParam String category) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not found!", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success!", products));
        } catch (Exception ex) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not found!", null));
        }
    }
}
