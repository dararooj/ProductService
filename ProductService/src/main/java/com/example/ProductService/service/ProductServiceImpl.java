package com.example.ProductService.service;

import com.example.ProductService.entity.Product;
import com.example.ProductService.exception.ProductServiceCustomException;
import com.example.ProductService.model.ProductRequest;
import com.example.ProductService.model.ProductResponse;
import com.example.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("AddingProduct...");
        log.info("Product Request Object {}",productRequest);
        Product product= Product.builder().productName(productRequest.getName()).quantity(productRequest.
                getQuantity()).price(productRequest.getPrice()).build();
        productRepository.save(product);
        log.info("Product Created");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product for productId: {}",productId);
        Product product=productRepository.findById(productId).orElseThrow(()-> new ProductServiceCustomException
                ("Product With given id not found","Product Not Found"));
        ProductResponse productResponse=new ProductResponse();
        copyProperties(product,productResponse);
        return productResponse;
    }

}
