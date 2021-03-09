package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    //product는 class 선언(노출할 attribute)
    //long은 product타입
}
