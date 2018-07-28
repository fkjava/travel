package org.fkjava.travel.commons.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

/**
 * 使用UUID作为主键的实体的超类
 *
 * @author LuoWenqiang
 */
@MappedSuperclass
public abstract class UUIDEntity extends AbstractEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @Column(name = "ID", length = 36)
    private String id;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UUIDEntity other = (UUIDEntity) obj;
        return Objects.equals(this.getId(), other.getId());
    }

}
