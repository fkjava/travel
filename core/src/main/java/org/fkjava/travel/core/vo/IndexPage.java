package org.fkjava.travel.core.vo;

import org.fkjava.travel.core.domain.Advertise;
import java.util.List;
import org.fkjava.travel.core.domain.ProductType;

/**
 *
 * @author lwq
 */
public class IndexPage {

    /**
     * 横幅导航条的一级类型列表
     */
    private List<ProductType> bannerTypes;
    /**
     * 左侧导航菜单的一级类型列表
     */
    private List<ProductType> navigationTypes;

    /**
     * 首屏图片广告
     */
    private List<Advertise> advertises;

    /**
     * 横幅导航条的一级类型列表
     *
     * @return the bannerTypes
     */
    public List<ProductType> getBannerTypes() {
        return bannerTypes;
    }

    /**
     * 横幅导航条的一级类型列表
     *
     * @param bannerTypes the bannerTypes to set
     */
    public void setBannerTypes(List<ProductType> bannerTypes) {
        this.bannerTypes = bannerTypes;
    }

    /**
     * 左侧导航菜单的一级类型列表
     *
     * @return the navigationTypes
     */
    public List<ProductType> getNavigationTypes() {
        return navigationTypes;
    }

    /**
     * 左侧导航菜单的一级类型列表
     *
     * @param navigationTypes the navigationTypes to set
     */
    public void setNavigationTypes(List<ProductType> navigationTypes) {
        this.navigationTypes = navigationTypes;
    }

    /**
     * 首屏图片广告
     *
     * @return the advertises
     */
    public List<Advertise> getAdvertises() {
        return advertises;
    }

    /**
     * 首屏图片广告
     *
     * @param advertises the advertises to set
     */
    public void setAdvertises(List<Advertise> advertises) {
        this.advertises = advertises;
    }
}
