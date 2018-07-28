package org.fkjava.travel.core.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.fkjava.travel.core.CoreConfig;
import org.fkjava.travel.core.domain.ProductType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.fkjava.travel.core.service.ProductService;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = CoreConfig.class)
public class ProductTypeServiceTests {

    @Autowired
    private ProductService productTypeService;

//    @Test
    public void testAddTag() {
        // 添加一级标签
        ProductType t1 = new ProductType();
        t1.setName("游轮游");
        t1.setTitle("游轮旅游");
        this.productTypeService.addType(t1);

        ProductType t2 = new ProductType();
        t2.setName("跟团游");
        t2.setTitle("跟团旅游");
        this.productTypeService.addType(t2);

        ProductType t5 = new ProductType();
        t5.setName("自驾游");
        t5.setTitle("自驾游");
        this.productTypeService.addType(t5);

        ProductType t3 = new ProductType();
        t3.setName("东南亚航线");
        t3.setTitle("东南亚航线");
        t3.setParent(t1);
        try {
            this.productTypeService.addType(t3);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ProductType t4 = new ProductType();
        t4.setName("三峡航线");
        t4.setTitle("三峡航线");
        t4.setParent(t1);
        this.productTypeService.addType(t4);

    }

    @Test
    public void testJsonAdd() throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            try ( InputStream src = this.getClass().getResourceAsStream("/ProductType.json")) {

                TestA t = objectMapper.readValue(src, TestA.class);

                List<ProductType> types = t.childs;
                types.forEach(type -> {
                    this.productTypeService.addType(type);
                    if (type.getChilds() != null) {
                        type.getChilds().forEach(st -> {
                            st.setParent(type);
                            this.productTypeService.addType(st);
                        });
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class TestA {

        private List<ProductType> childs;

        public List<ProductType> getChilds() {
            return childs;
        }

        public void setChilds(List<ProductType> childs) {
            this.childs = childs;
        }
    }
}
