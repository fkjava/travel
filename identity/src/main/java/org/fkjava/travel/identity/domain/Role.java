package org.fkjava.travel.identity.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.fkjava.travel.commons.domain.UUIDEntity;

@Entity
@Table(name = "ID_ROLE")
public class Role extends UUIDEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @Column(unique = true, name = "NAME")
    private String name;
    /**
     * 针对Spring Security定制的KEY，满足Spring Security要求
     */
    @Column(name = "ROLE_KEY")
    private String roleKey;

    /**
     * 关联的用户对象
     */
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    /**
     * 角色名称
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 角色名称
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 关联的用户对象
     *
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * 关联的用户对象
     *
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role other = (Role) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "Role{" + "name=" + name + ", users=" + users + '}';
    }

    /**
     * 针对Spring Security定制的KEY，满足Spring Security要求
     * 
     * @return
     */
    public String getRoleKey() {
        return roleKey;
    }

    /**
     * 针对Spring Security定制的KEY，满足Spring Security要求
     * 
     * @param roleKey
     */
    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }
}