package org.fkjava.travel.front.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.fkjava.travel.core.domain.Product;
import org.fkjava.travel.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Luo Wenqiang<luo_wenqiang@qq.com>
 */
@RestController
@RequestMapping("front/product")
@JsonIgnoreProperties("type.children")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 获取图片的方式：
    // http://127.0.0.1:8080/commons/file/图片ID
    // 图片ID在产品信息的imageIds字段中
    @GetMapping
    public Page<Product> list(
            //
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer number,//
            @RequestParam(name = "typeId", required = false) String typeId,
            @RequestParam(name = "pageSize", defaultValue = "210000000") Integer pageSize
    ) {
        return this.productService.findProducts(typeId, number, pageSize);
    }

    @GetMapping("{id}")
    public Product details(
            //
            @PathVariable("id") String id
    ) {
        return this.productService.getProductById(id);
    }
}
