package org.fkjava.travel.core.dao;

import org.fkjava.travel.core.domain.Product;
import org.fkjava.travel.core.domain.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, String> {

    public Page<Product> findByType(ProductType type, Pageable pageable);

}
