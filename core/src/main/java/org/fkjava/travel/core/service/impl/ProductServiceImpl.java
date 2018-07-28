package org.fkjava.travel.core.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.fkjava.travel.commons.domain.OperationResult;
import org.fkjava.travel.core.dao.AdvertiseDao;
import org.fkjava.travel.core.dao.ProductDao;
import org.fkjava.travel.core.dao.ProductTypeDao;
import org.fkjava.travel.core.domain.Advertise;
import org.fkjava.travel.core.domain.Product;
import org.fkjava.travel.core.domain.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.fkjava.travel.core.service.ProductService;
import org.fkjava.travel.core.vo.IndexPage;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductTypeDao productTagDao;
    @Autowired
    private AdvertiseDao advertiseDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public OperationResult addType(ProductType tag) {
        ProductType old;
        Double maxOrderNumber;
        if (tag.getParent() == null || StringUtils.isBlank(tag.getParent().getId())) {
            // 没有上级的时候，直接判断一级标签是否有同名的
            old = this.productTagDao.findByNameAndParentIsNull(tag.getName());
            maxOrderNumber = this.productTagDao.getMaxOrderNumberByParentIsNull();
            tag.setParent(null);
        } else {
            // 根据名称和上级标签查询是否有同名的
            old = this.productTagDao.findByNameAndParent(tag.getName(), tag.getParent());
            maxOrderNumber = this.productTagDao.getMaxOrderNumberByParent(tag.getParent());
            ProductType parent = this.productTagDao.findById(tag.getParent().getId()).get();
            tag.setParent(parent);
        }

        // 设置增长的排序编号，修改的时候要作特殊处理，确保序号不会乱
        if (maxOrderNumber == null) {
            maxOrderNumber = 1000.0;
        } else {
            maxOrderNumber = maxOrderNumber + 1000;
        }
        tag.setOrderNumber(maxOrderNumber);

        OperationResult result;
        if (old == null) {
            // 不存在旧的记录，表示名称不重复
            tag.setInsertTime(new Date());
            ProductType saved = this.productTagDao.save(tag);
            result = new OperationResult(true, "产品分类标签添加成功");
        } else {
            tag.setId(old.getId());
            result = new OperationResult(false, "产品分类标签添加失败，名称已经被使用");
        }

        return result;
    }

    @Override
    public IndexPage getIndexPage() {
        IndexPage page = new IndexPage();

        // 查询横幅导航类型
        Pageable pageable = PageRequest.of(0, 19);
        List<ProductType> bannerTypes//
                = productTagDao.findByParentIsNull(pageable)//
                        .getContent();
        // 查询左侧导航类型
        pageable = PageRequest.of(0, 6);
        List<ProductType> navigationTypes //
                = productTagDao.findByParentIsNull(pageable)//
                        .getContent();
        // 查询巨幅广告
        Sort sort = Sort.by(Sort.Order.asc("number"));
        pageable = PageRequest.of(0, 10, sort);
        List<Advertise> ads = this.advertiseDao.findAll(pageable).getContent();

        page.setAdvertises(ads);
        page.setBannerTypes(bannerTypes);
        page.setNavigationTypes(navigationTypes);

        return page;
    }

    @Override
    public Page<Product> findProducts(String typeId, Integer number, int pageSize) {

        Pageable pageable = PageRequest.of(number, pageSize);
        Page<Product> page;
        if (StringUtils.isBlank(typeId)) {
            page = this.productDao.findAll(pageable);
        } else {
            ProductType type = new ProductType();
            type.setId(typeId);
            page = this.productDao.findByType(type, pageable);
        }
        return page;
    }

    @Override
    public Page<Product> findProducts(String typeId, Integer pageNumber) {
        return this.findProducts(typeId, pageNumber, 6);
    }

    @Override
    public List<ProductType> findTopTypes() {

        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        List<ProductType> topTypes //
                = productTagDao.findByParentIsNull(pageable)//
                        .getContent();
        return topTypes;
    }

    @Override
    public void save(Product product) {
        this.productDao.save(product);
    }

    @Override
    public Product getProductById(String id) {
        return this.productDao.findById(id).get();
    }

    @Override
    public List<ProductType> findSubTypes(String id) {
        ProductType parent = new ProductType();
        parent.setId(id);
        return this.productTagDao.findByParent(parent);
    }
}
