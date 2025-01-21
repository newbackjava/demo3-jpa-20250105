package com.example.demo;

import com.example.demo.repository.ProductRepository;
import com.example.demo.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @Sql("/insert-product.sql_")
    public void insertProduct() {
        List<Product> products = productRepository.findAll();
        assertThat(products.size()).isEqualTo(4);
    }

    @Test
     public void getProductById() {
        Product product = productRepository.findById(2L).get();
        assertThat(product).isNull(); //에러가 날때만 출력
    }

    @Test
    public void saveProduct() {
        Product product = Product.builder().productId(4L).name("nike").price(1000).build();
        productRepository.save(product);
        assertThat(productRepository.findById(4L)).isNull();
    }
    @Test
    public void saveProduct2() {

        List<Product> products = List.of(
                Product.builder().productId(5L).name("adidas").price(2000).build(),
                Product.builder().productId(6L).name("adidas").price(3000).build()
                );

        productRepository.saveAll(products);
        assertThat(productRepository.findAll()).hasSize(2);
    }


    @Test
    public void updateProduct() {
        Product product = productRepository.findById(4L).get();
        product.setName("@@@@@@@@@@@");
        productRepository.save(product);
        assertThat(productRepository.findById(1L).get().getName()).isEqualTo("@@@@@@@@@@@@");
    }


}