package com.example.demo.service;

import com.example.demo.repository.ProductRepository;
import com.example.demo.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // id를 가지고 상세 검색
    public Product findById(Long productId) {
        return productRepository.findById(productId).get();
    }

    // 데이터가 없는 경우 insert하는 용도
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // id를 주고 해당 row 삭제
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    // 데이터가 있는 경우 update하는 용도
    public Product update(Product product) {
        return null;
    }
}
