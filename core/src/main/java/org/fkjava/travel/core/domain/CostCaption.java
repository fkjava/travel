package org.fkjava.travel.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.fkjava.travel.commons.domain.UUIDEntity;

/**
 *
 * @author lwq
 */
@Entity
@Table(name = "PRODUCT_COST_CAPTION")
public class CostCaption extends UUIDEntity {

    /**
     * 费用说明的内容，就是一个HTML文本
     */
    @Column(length = 2500)
    private String content;

    /**
     * 费用说明的内容，就是一个HTML文本
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * 费用说明的内容，就是一个HTML文本
     *
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CostCaption{" + "content=" + content + '}';
    }
}
