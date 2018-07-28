package org.fkjava.travel.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.fkjava.travel.commons.domain.UUIDEntity;

/**
 *
 * @author lwq
 */
@Entity
@Table(name = "PRODUCT_TYPE")
public class ProductType extends UUIDEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 显示在横幅中的名字
     */
    private String name;
    /**
     * 显示在搜索列表中的标题
     */
    private String title;
    /**
     * 上级类型
     */
    @ManyToOne()
    @JoinColumn(name = "PARENT_ID")
    @JsonIgnore
    private ProductType parent;
    /**
     * 下级类型
     */
    @OneToMany(mappedBy = "parent")
    @OrderBy(value = "orderNumber")
    @JsonProperty("children")
    private List<ProductType> childs;

    /**
     * 自动排序列
     */
    private double orderNumber = 0.0;

    /**
     * 显示在横幅中的名字
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 显示在横幅中的名字
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 显示在搜索列表中的标题
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 显示在搜索列表中的标题
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 上级类型
     *
     * @return the parent
     */
    public ProductType getParent() {
        return parent;
    }

    /**
     * 上级类型
     *
     * @param parent the parent to set
     */
    public void setParent(ProductType parent) {
        this.parent = parent;
    }

    /**
     * 下级类型
     *
     * @return the childs
     */
    public List<ProductType> getChilds() {
        return childs;
    }

    /**
     * 下级类型
     *
     * @param childs the childs to set
     */
    public void setChilds(List<ProductType> childs) {
        this.childs = childs;
    }

    /**
     * 自动排序列
     *
     * @return the orderNumber
     */
    public double getOrderNumber() {
        return orderNumber;
    }

    /**
     * 自动排序列
     *
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(double orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "ProductType{" + "name=" + name + ", title=" + title + ", childs=" + childs + ", orderNumber=" + orderNumber + '}';
    }
}
