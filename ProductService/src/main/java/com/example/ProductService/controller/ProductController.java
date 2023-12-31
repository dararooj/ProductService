package com.example.ProductService.controller;

import com.example.ProductService.model.ProductRequest;
import com.example.ProductService.model.ProductResponse;
import com.example.ProductService.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Log4j2
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Long> getProduct(@RequestBody ProductRequest productRequest){
        log.info("Product Request Object {}",productRequest);
        long productId=productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId){
     ProductResponse productResponse=productService.getProductById(productId);
     return  new ResponseEntity<>(productResponse,HttpStatus.NOT_FOUND);
    }
}
