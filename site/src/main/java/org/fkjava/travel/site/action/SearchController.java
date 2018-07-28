package org.fkjava.travel.site.action;

import org.fkjava.travel.core.domain.Product;
import org.fkjava.travel.core.service.ProductService;
import org.fkjava.travel.core.vo.IndexPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Luo Wenqiang<luo_wenqiang@qq.com>
 */
@Controller
@RequestMapping("site/search")
public class SearchController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView list(//
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer number,//
            @RequestParam(name = "typeId", required = false) String typeId
    ) {
        ModelAndView mav = new ModelAndView("site/search/list");
        Page<Product> productPage = this.productService.findProducts(typeId, number);
        mav.addObject("productPage", productPage);

        IndexPage page = this.productService.getIndexPage();
        mav.addObject("page", page);

        return mav;
    }
}
