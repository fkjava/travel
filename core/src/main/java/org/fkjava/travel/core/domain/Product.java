package org.fkjava.travel.core.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.fkjava.travel.commons.domain.UUIDEntity;

/**
 *
 * @author lwq
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends UUIDEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品分类
     */
    @ManyToOne()
    @JoinColumn(name = "TYPE_ID")
    private ProductType type;
    /**
     * 封面介绍图片文件ID，用于获取图片的时候使用
     */
    @ElementCollection()
    private List<String> imageIds;
    /**
     * 产品价格
     */
    private Double price;
    /**
     * 游玩路线
     */
    private String travelLine;

    /**
     * 出发时间和出发城市
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<StartOut> startOuts;
    /**
     * 行程天数
     */
    private int travelDays;
    /**
     * 产品的品牌
     */
    private String trademark;
    /**
     * 产品特色
     */
    @OneToOne(cascade = CascadeType.ALL)
    private ProductFeature feature;
    /**
     * 线路介绍
     */
    @OneToOne(cascade = CascadeType.ALL)
    private LineIntroduce introduce;
    /**
     * 费用说明
     */
    @OneToOne(cascade = CascadeType.ALL)
    private CostCaption caption;
    /**
     * 预订须知
     */
    @OneToOne(cascade = CascadeType.ALL)
    private SubscribeNotice notice;

    /**
     * 产品名称
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 产品名称
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 产品分类
     *
     * @return the type
     */
    public ProductType getType() {
        return type;
    }

    /**
     * 产品分类
     *
     * @param type the type to set
     */
    public void setType(ProductType type) {
        this.type = type;
    }

    /**
     * 产品价格
     *
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 产品价格
     *
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 游玩路线
     *
     * @return the travelLine
     */
    public String getTravelLine() {
        return travelLine;
    }

    /**
     * 游玩路线
     *
     * @param travelLine the travelLine to set
     */
    public void setTravelLine(String travelLine) {
        this.travelLine = travelLine;
    }

    /**
     * 行程天数
     *
     * @return the travelDays
     */
    public int getTravelDays() {
        return travelDays;
    }

    /**
     * 行程天数
     *
     * @param travelDays the travelDays to set
     */
    public void setTravelDays(int travelDays) {
        this.travelDays = travelDays;
    }

    /**
     * 产品的品牌
     *
     * @return the trademark
     */
    public String getTrademark() {
        return trademark;
    }

    /**
     * 产品的品牌
     *
     * @param trademark the trademark to set
     */
    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    /**
     * 产品特色
     *
     * @return the feature
     */
    public ProductFeature getFeature() {
        return feature;
    }

    /**
     * 产品特色
     *
     * @param feature the feature to set
     */
    public void setFeature(ProductFeature feature) {
        this.feature = feature;
    }

    /**
     * 线路介绍
     *
     * @return the introduce
     */
    public LineIntroduce getIntroduce() {
        return introduce;
    }

    /**
     * 线路介绍
     *
     * @param introduce the introduce to set
     */
    public void setIntroduce(LineIntroduce introduce) {
        this.introduce = introduce;
    }

    /**
     * 费用说明
     *
     * @return the caption
     */
    public CostCaption getCaption() {
        return caption;
    }

    /**
     * 费用说明
     *
     * @param caption the caption to set
     */
    public void setCaption(CostCaption caption) {
        this.caption = caption;
    }

    /**
     * 预订须知
     *
     * @return the notice
     */
    public SubscribeNotice getNotice() {
        return notice;
    }

    /**
     * 预订须知
     *
     * @param notice the notice to set
     */
    public void setNotice(SubscribeNotice notice) {
        this.notice = notice;
    }

    /**
     * 封面介绍图片文件ID，用于获取图片的时候使用
     *
     * @return the imageIds
     */
    public List<String> getImageIds() {
        return imageIds;
    }

    /**
     * 封面介绍图片文件ID，用于获取图片的时候使用
     *
     * @param imageIds the imageIds to set
     */
    public void setImageIds(List<String> imageIds) {
        this.imageIds = imageIds;
    }

    /**
     * 出发时间和出发城市
     *
     * @return the startOuts
     */
    public List<StartOut> getStartOuts() {
        return startOuts;
    }

    /**
     * 出发时间和出发城市
     *
     * @param startOuts the startOuts to set
     */
    public void setStartOuts(List<StartOut> startOuts) {
        this.startOuts = startOuts;
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", type=" + type + ", imageIds=" + imageIds + ", price=" + price + ", travelLine=" + travelLine + ", startOuts=" + startOuts + ", travelDays=" + travelDays + ", trademark=" + trademark + ", feature=" + feature + ", introduce=" + introduce + ", caption=" + caption + ", notice=" + notice + '}';
    }
}
