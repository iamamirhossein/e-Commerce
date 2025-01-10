package com.yerevan.yerevanshops.controller;

import com.yerevan.yerevanshops.exceptions.ResourceNotFoundException;
import com.yerevan.yerevanshops.model.CartItem;
import com.yerevan.yerevanshops.response.ApiResponse;
import com.yerevan.yerevanshops.service.cart.ICartItemService;
import com.yerevan.yerevanshops.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/items")
public class CartItemController {
    private final ICartItemService cartItemService;
    private final ICartService cartService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam(required = false) Long cartId,
                                                     @RequestParam Long productId,
                                                     @RequestParam Integer quantity) {
        if (cartId == null) {
            cartId = cartService.initializeNewCart();
        }
        try {
            cartItemService.addItemToCart(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Success", null));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.ok(new ApiResponse(ex.getMessage(), null));
        }
    }

    @DeleteMapping("/{cartId}/{itemId}")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        try {
            cartItemService.removeItemFromCart(cartId, itemId);
            return ResponseEntity.ok(new ApiResponse("Success", null));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.ok(new ApiResponse(ex.getMessage(), null));
        }
    }

    @PutMapping("/{cartId}/{productId}")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,
                                                          @PathVariable Long productId,
                                                          @RequestParam Integer quantity) {
        try {
            cartItemService.updateItemQuantity(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Success", null));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.ok(new ApiResponse(ex.getMessage(), null));
        }

    }
}
