package org.fkjava.travel.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.fkjava.travel.commons.domain.UUIDEntity;

/**
 * 广告信息
 *
 * @author lwq
 */
@Entity
@Table(name = "ADVERTISE")
public class Advertise extends UUIDEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 广告标题
     */
    private String title;
    /**
     * 广告页面的URL
     */
    private String url;
    /**
     * 广告的图片信息，可以为空。
     */
    private String imageUrl;
    /**
     * 排序的序号
     */
    private double number;
    /**
     * 字母、说明性文字
     */
    private String caption;

    /**
     * 广告标题
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 广告标题
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 广告页面的URL
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 广告页面的URL
     *
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 广告的图片信息，可以为空。
     *
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 广告的图片信息，可以为空。
     *
     * @param imageUrl the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 排序的序号
     *
     * @return the number
     */
    public double getNumber() {
        return number;
    }

    /**
     * 排序的序号
     *
     * @param number the number to set
     */
    public void setNumber(double number) {
        this.number = number;
    }

    /**
     * 字母、说明性文字
     *
     * @return the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * 字母、说明性文字
     *
     * @param caption the caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }
}
