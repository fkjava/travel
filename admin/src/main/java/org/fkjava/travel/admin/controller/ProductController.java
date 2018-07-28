package org.fkjava.travel.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.fkjava.travel.commons.DateEditor;
import org.fkjava.travel.core.domain.Product;
import org.fkjava.travel.core.domain.ProductType;
import org.fkjava.travel.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author lwq
 */
@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ModelAndView list(//
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,//
            @RequestParam(name = "typeId", required = false) String typeId
    //
    ) {
        ModelAndView mav = new ModelAndView("admin/product/list");
        Page<Product> page = productService.findProducts(typeId, pageNumber);
        mav.addObject("page", page);

        return mav;
    }

    @GetMapping("add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("admin/product/add");

        List<ProductType> types = this.productService.findTopTypes();
        String json = "[]";
        try {
            json = objectMapper.writeValueAsString(types);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        mav.addObject("json", json);

        return mav;
    }

    @InitBinder
    public void init0(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor(new SimpleDateFormat("yyyy-MM-dd")));
    }

    @PostMapping
    public ModelAndView save(Product product) {
        ModelAndView mav = new ModelAndView("redirect:/admin/product");

        this.productService.save(product);

        return mav;
    }
}
