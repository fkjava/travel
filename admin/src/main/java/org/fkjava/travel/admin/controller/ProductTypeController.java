package org.fkjava.travel.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.fkjava.travel.core.domain.ProductType;
import org.fkjava.travel.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author lwq
 */
@Controller
@RequestMapping("/admin/productType")
public class ProductTypeController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("admin/productType/list");

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

    @PostMapping
    public ModelAndView save(ProductType type) {
        ModelAndView mav = new ModelAndView("redirect:/admin/productType");

        this.productService.addType(type);

        return mav;
    }
}
