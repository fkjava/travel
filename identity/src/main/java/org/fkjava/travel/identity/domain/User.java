package org.fkjava.travel.identity.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.fkjava.travel.commons.domain.UUIDEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ID_USER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends UUIDEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 唯一，用户的姓名
     */
    @Column(name = "USER_NAME")
    private String userName;
    /**
     * 唯一，登录名
     */
    @Column(name = "LOGIN_NAME")
    private String loginName;
    /**
     * 用户的昵称，可重复
     */
    @Column(name = "NICK_NAME")
    private String nickName;
    /**
     * 加密后的密码，在比较用户的密码时，把用户的密码使用相同的加密算法进行加密，比较两个密文是否相同来判断密码是否一致。
     */
    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;
    /**
     * 用户的电邮地址，不可重复
     */
    @Column(name = "EMAIL")
    private String email;
    /**
     * 手机号码
     */
    @Column(name = "MOBILE_PHONE")
    private String mobilePhone;
    /**
     * 性别
     */
    @Column(name = "GENDER")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    /**
     * 年龄
     */
    // @Column(name = "AGE")
    @Transient
    private Integer age;
    /**
     * 生日
     */
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    /**
     * 账户过期时间，如果是null，表示永远有效
     */
    @Column(name = "ACT_EXPIRE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accountExpireTime;
    /**
     * 账户是否已过期，如果是true表示未过期
     */
    @Transient
    private boolean accountNonExpired = true;
    /**
     * 是否已锁定，如果是true表示未锁定
     */
    @Column(name = "ACT_NON_LOCKED")
    private boolean accountNonLocked = true;
    /**
     * 密码过期时间，如果null，表示永久有效
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PWD_EXPIRE_TIME")
    private Date passwordExpireTime;

    /**
     * 密码修改时间，记录最后一次修改时间。如果是新用户，则把用户的注册时间作为此时间。
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PWD_UPDATE_TIME")
    private Date passwordUpdateTime;

    /**
     * 注册时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REG_TIME")
    private Date registerTime;
    /**
     * 密码是否已过期，如果是true表示未过期
     */
    @Transient
    private boolean passwordNonExpired = true;
    /**
     * 是否已激活，默认未激活，需要使用激活链接进行激活后才能登陆、使用
     */
    @Column(name = "ENABLED")
    private boolean enabled = false;

    /**
     * 默认语言
     */
    @Column(name = "DEFAULT_LANG")
    private String defaultLanguage;

    /**
     * 头像附件的id
     */
    @JsonIgnore
    @Transient
    private String headPortraitImageId;

    /**
     * 用户具有的角色
     */
    @ManyToMany
    @JoinTable(name = "ID_USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
    @JsonIgnore
    private List<Role> roles;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.userName);
        hash = 37 * hash + Objects.hashCode(this.loginName);
        hash = 37 * hash + Objects.hashCode(this.email);
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
        final User other = (User) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.loginName, other.loginName)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", loginName=" + loginName + ", nickName=" + nickName + ", password="
                + password + ", email=" + email + ", mobilePhone=" + mobilePhone + ", gender=" + gender + ", age=" + age
                + ", birthday=" + birthday + ", accountExpireTime=" + accountExpireTime + ", accountNonExpired="
                + accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", passwordExpireTime="
                + passwordExpireTime + ", passwordUpdateTime=" + passwordUpdateTime + ", registerTime=" + registerTime
                + ", passwordNonExpired=" + passwordNonExpired + ", enabled=" + enabled + '}';
    }

    /**
     * 检查时间是否已过期
     *
     * @param time
     * @return 未过期返回true，已过期返回false
     */
    private boolean isNonExpried(Date expireTime) {
        if (expireTime != null) {
            // 如果过期时间不为空，判断过期时间是否已经到达，到达时间表示已经过期
            long time = expireTime.getTime();
            long currentTime = System.currentTimeMillis();
            if (time <= currentTime) {
                // 过期时间小于或等于当前时间，表示已经过期
                return false;
            }
        }
        return true;
    }

    /**
     * 唯一，用户的姓名
     *
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 唯一，用户的姓名
     *
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 唯一，登录名
     *
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 唯一，登录名
     *
     * @param loginName the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 用户的昵称，可重复
     *
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 用户的昵称，可重复
     *
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 加密后的密码，在比较用户的密码时，把用户的密码使用相同的加密算法进行加密，比较两个密文是否相同来判断密码是否一致。
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 加密后的密码，在比较用户的密码时，把用户的密码使用相同的加密算法进行加密，比较两个密文是否相同来判断密码是否一致。
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用户的电邮地址，不可重复
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 用户的电邮地址，不可重复
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 手机号码
     *
     * @return the mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 手机号码
     *
     * @param mobilePhone the mobilePhone to set
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 性别
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * 性别
     *
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * 年龄
     *
     * @return the age
     */
    public Integer getAge() {
        if (age == null && this.getBirthday() != null) {
            // 计算年龄
            Calendar currentCalendar = Calendar.getInstance();
            Calendar birthdayCalendar = Calendar.getInstance();
            birthdayCalendar.setTime(this.getBirthday());

            int oldYear = birthdayCalendar.get(Calendar.YEAR);
            int nowYear = currentCalendar.get(Calendar.YEAR);
            age = nowYear - oldYear;
        }
        return age;
    }

    /**
     * 生日
     *
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 生日
     *
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 账户过期时间，如果是null，表示永远有效
     *
     * @return the accountExpireTime
     */
    public Date getAccountExpireTime() {
        return accountExpireTime;
    }

    /**
     * 账户过期时间，如果是null，表示永远有效
     *
     * @param accountExpireTime the accountExpireTime to set
     */
    public void setAccountExpireTime(Date accountExpireTime) {
        this.accountExpireTime = accountExpireTime;
    }

    /**
     * 账户是否已过期，如果是true表示未过期
     *
     * @return the accountNonExpired
     */
    public boolean isAccountNonExpired() {
        accountNonExpired = isNonExpried(accountExpireTime);
        return accountNonExpired;
    }

    /**
     * 是否已锁定，如果是true表示未锁定
     *
     * @return the accountNonLocked
     */
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * 是否已锁定，如果是true表示未锁定
     *
     * @param accountNonLocked the accountNonLocked to set
     */
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    /**
     * 密码过期时间，如果null，表示永久有效
     *
     * @return the passwordExpireTime
     */
    public Date getPasswordExpireTime() {
        return passwordExpireTime;
    }

    /**
     * 密码过期时间，如果null，表示永久有效
     *
     * @param passwordExpireTime the passwordExpireTime to set
     */
    public void setPasswordExpireTime(Date passwordExpireTime) {
        this.passwordExpireTime = passwordExpireTime;
    }

    /**
     * 密码是否已过期，如果是true表示未过期
     *
     * @return the passwordNonExpired
     */
    public boolean isPasswordNonExpired() {
        passwordNonExpired = isNonExpried(passwordExpireTime);
        return passwordNonExpired;
    }

    /**
     * 是否已激活，默认未激活，需要使用激活链接进行激活后才能登陆、使用
     *
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 是否已激活，默认未激活，需要使用激活链接进行激活后才能登陆、使用
     *
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 密码修改时间，记录最后一次修改时间。如果是新用户，则把用户的注册时间作为此时间。
     *
     * @return the passwordUpdateTime
     */
    public Date getPasswordUpdateTime() {
        return passwordUpdateTime;
    }

    /**
     * 密码修改时间，记录最后一次修改时间。如果是新用户，则把用户的注册时间作为此时间。
     *
     * @param passwordUpdateTime the passwordUpdateTime to set
     */
    public void setPasswordUpdateTime(Date passwordUpdateTime) {
        this.passwordUpdateTime = passwordUpdateTime;
    }

    /**
     * 注册时间
     *
     * @return the registerTime
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 注册时间
     *
     * @param registerTime the registerTime to set
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 默认语言
     *
     * @return the defaultLanguage
     */
    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    /**
     * 默认语言
     *
     * @param defaultLanguage the defaultLanguage to set
     */
    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    /**
     * 用户具有的角色
     *
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * 用户具有的角色
     *
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * 获取账户过期天数
     *
     * @return
     */
    public int getAccountExpireDay() {
        return this.getExpireDay(this.accountExpireTime);
    }

    /**
     * 获取密码过期的天数
     *
     * @return
     */
    public int getPasswordExpireDay() {
        return this.getExpireDay(this.passwordExpireTime);
    }

    private int getExpireDay(Date date) {
        if (date == null) {
            // 如果没有过期时间，认为无过期时间
            return Integer.MAX_VALUE;
        } else {
            // 当前时间
            long time = System.currentTimeMillis();
            // 过期时间
            long expireTime = date.getTime();

            time = expireTime - time;

            // 失效天数
            long day = time / 1000 / 60 / 60 / 24;
            return (int) day;
        }
    }
}