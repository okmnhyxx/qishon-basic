package com.qishon.common.model;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author kexia.lu on 2017/8/22.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractSimplyBaseDomain implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * 创建时间
     */
    @CreatedDate
    private long createTime;

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        return this.id == ((AbstractBaseDomain) obj).getId();
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
