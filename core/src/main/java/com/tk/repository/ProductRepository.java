package com.tk.repository;

import com.tk.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query("select p from Product p where p.name like %?1%")
    List<Product> findByNameLike(String name);
    @Query("select p from Product p where p.sku like %?1%")
    List<Product> findBySkuLike(String sku);
}
