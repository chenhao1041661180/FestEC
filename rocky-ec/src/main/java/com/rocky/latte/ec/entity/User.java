package com.rocky.latte.ec.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/25
 */
@Entity(nameInDb = "user")
public class User {
    @Id
    private long userId;//用户唯一id
    private String name;//姓名
    private String avatar;//头像
    private String gender;//性别
    private String address;//地址
    @Generated(hash = 1394864631)
    public User(long userId, String name, String avatar, String gender,
            String address) {
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
        this.address = address;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

}
