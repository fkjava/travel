package org.fkjava.travel.front.action;

import java.util.List;
import org.fkjava.travel.core.domain.ProductType;
import org.fkjava.travel.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Luo Wenqiang<luo_wenqiang@qq.com>
 */
@RestController
@RequestMapping("front/product-type")
public class ProductTypeController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductType> list() {
        return this.productService.findTopTypes();
    }

    @GetMapping("{id}")
    public List<ProductType> list(@PathVariable("id") String id) {
        return this.productService.findSubTypes(id);
    }
}
