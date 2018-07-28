package org.fkjava.travel.site.action;

import org.fkjava.travel.core.domain.Product;
import org.fkjava.travel.core.service.ProductService;
import org.fkjava.travel.core.vo.IndexPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Luo Wenqiang<luo_wenqiang@qq.com>
 */
@Controller
@RequestMapping("site/item")
public class ItemController {

    @Autowired
    private ProductService productService;

    @GetMapping("{id}")
    public ModelAndView item(//
            @PathVariable("id") String id
    ) {
        ModelAndView mav = new ModelAndView("site/item/item");

        IndexPage page = this.productService.getIndexPage();
        mav.addObject("page", page);
        Product product = this.productService.getProductById(id);
        mav.addObject("p", product);

        return mav;
    }
}
