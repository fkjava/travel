package org.fkjava.travel.core.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.fkjava.travel.commons.CustomDateSerializer;
import org.fkjava.travel.commons.domain.UUIDEntity;

/**
 * 行程出发信息
 *
 * @author lwq
 */
@Entity
@Table(name = "PRODUCT_START_OUT")
public class StartOut extends UUIDEntity {

    /**
     * 出发时间
     */
    @Column(name = "`date`")
    @Temporal(value = TemporalType.DATE)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date date;
    /**
     * 出发位置（城市、地区）
     */
    private String location;

    /**
     * 出发时间
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * 出发时间
     *
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 出发位置（城市、地区）
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * 出发位置（城市、地区）
     *
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "StartOut{" + "date=" + date + ", location=" + location + '}';
    }
}
