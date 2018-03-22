package com.smyy.sharetour.buyer.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 伍振飞 on 2018/3/19 09:39
 * E-Mail Address：wuzf2012@sina.com
 */
@Entity
public class Test {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String age;
    @NotNull
    private int cardNum;
    @Generated(hash = 828898221)
    public Test(Long id, @NotNull String name, @NotNull String age, int cardNum) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.cardNum = cardNum;
    }
    @Generated(hash = 372557997)
    public Test() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public int getCardNum() {
        return this.cardNum;
    }
    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }
}
