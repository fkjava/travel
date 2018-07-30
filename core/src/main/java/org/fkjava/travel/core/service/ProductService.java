package org.fkjava.travel.core.service;

import java.util.List;
import org.fkjava.travel.commons.domain.OperationResult;
import org.fkjava.travel.core.domain.Product;
import org.fkjava.travel.core.domain.ProductType;
import org.fkjava.travel.core.vo.IndexPage;
import org.springframework.data.domain.Page;

public interface ProductService {

    /**
     * 增加一个产品类型
     *
     * @param type
     * @return
     */
    OperationResult addType(ProductType type);

    /**
     * 获取首页需要的数据
     *
     * @return
     */
    IndexPage getIndexPage();

    public Page<Product> findProducts(String typeId, Integer pageNumber);

    public List<ProductType> findTopTypes();

    public void save(Product product);

    public Product getProductById(String id);

    public List<ProductType> findSubTypes(String id);

    public Page<Product> findProducts(String typeId, Integer number, int pageSize);

    public OperationResult deleteProductType(String id);
}
