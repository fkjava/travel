package org.fkjava.travel.core.dao;

import java.util.List;
import org.fkjava.travel.core.domain.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeDao extends JpaRepository<ProductType, String> {

    ProductType findByNameAndParentIsNull(String name);

    ProductType findByNameAndParent(String name, ProductType parent);

    @Query("select max(orderNumber) from ProductType where parent is null")
    public Double getMaxOrderNumberByParentIsNull();

    @Query("select max(orderNumber) from ProductType where parent = :parent")
    public Double getMaxOrderNumberByParent(@Param("parent") ProductType parent);

    public Page<ProductType> findByParentIsNull(Pageable pageable);

    public List<ProductType> findByParent(ProductType parent);

}
