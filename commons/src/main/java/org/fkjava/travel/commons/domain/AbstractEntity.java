package org.fkjava.travel.commons.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.fkjava.travel.commons.CustomDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 所有实体的抽象父类，主要是定义数据的时间
 *
 * @author LuoWenqiang
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 数据的插入时间，该时间不会被修改
     */
    @Column(name = "INSERT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date insertTime = new Date();

    /**
     * 数据的插入时间，该时间不会被修改
     *
     * @return the insertTime
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * 数据的插入时间，该时间不会被修改
     *
     * @param insertTime the insertTime to set
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}
