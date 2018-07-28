package org.fkjava.travel.site.action;

import org.fkjava.travel.core.service.ProductService;
import org.fkjava.travel.core.vo.IndexPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("site/index")
public class IndexController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("site/index");

        IndexPage page = this.productService.getIndexPage();
        mav.addObject("page", page);

        return mav;
    }
}
