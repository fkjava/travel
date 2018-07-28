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
@Table(name = "PRODUCT_SUBSCRIBE_NOTICE")
public class SubscribeNotice extends UUIDEntity {

    /**
     * 预订须知的内容，就是一个HTML文本
     */
    @Column(length = 2500)
    private String content;

    /**
     * 预订须知的内容，就是一个HTML文本
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * 预订须知的内容，就是一个HTML文本
     *
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SubscribeNotice{" + "content=" + content + '}';
    }
}
