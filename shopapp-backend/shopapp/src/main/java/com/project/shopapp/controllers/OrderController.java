package com.project.shopapp.controllers;

import com.project.shopapp.components.LocalizationUtils;
import com.project.shopapp.dtos.*;
import com.project.shopapp.models.Order;
import com.project.shopapp.responses.OrderResponse;
import com.project.shopapp.services.IOrderService;
import com.project.shopapp.utils.MessageKeys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    private final IOrderService orderService;
    private final LocalizationUtils localizationUtils;
    @PostMapping("")
    public  ResponseEntity<?> createOrder(
            @Valid @RequestBody OrderDTO orderDTO,
            BindingResult result){
        try{
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Order orderResponse = orderService.createOrder(orderDTO);
            return ResponseEntity.ok(orderResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{user_id}")//Them bien duong dan "user_id"
    //GET ${api.prefix}/orders/user/4
    public ResponseEntity<?> getOrders(
            @Valid @PathVariable("user_id") Long userId
    ){
        try{
            List<Order> orders = orderService.findByUserId(userId);
            return ResponseEntity.ok(orders);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    //GET ${api.prefix}/orders/4
    public ResponseEntity<?> getOrder(
            @Valid @PathVariable("id") Long orderId
    ){
        try{
            Order existingOrder = orderService.getOrder(orderId);
            OrderResponse orderResponse = OrderResponse.fromOrder(existingOrder);
            return ResponseEntity.ok(orderResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrders(
            @Valid @PathVariable Long id,
            @Valid @RequestBody OrderDTO orderDTO
    ){
        try{
            Order order = orderService.updateOrder(id, orderDTO);
            return ResponseEntity.ok(order);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrders(
            @Valid @PathVariable Long id
    ){
        //Xoá mềm => cập nhật trường active = false
        orderService.deleteOrder(id);
        return ResponseEntity.ok(localizationUtils.getLocalizedMessage(MessageKeys.DELETE_ORDER_SUCCESSFULLY));
    }
}
