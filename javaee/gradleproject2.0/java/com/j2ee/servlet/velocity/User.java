package com.j2ee.servlet.velocity;

/**
 * Created by zjm on 2018/3/9.
 */
public class User {
    private String name;
    private String sex;
    private String address;
    private  int age;
    private String[] interests;
    private String[] specialitys;

    public User() {
    }

    public User(String name, String sex, String address) {
        this.name = name;
        this.sex = sex;
        this.address = address;
    }

    public String[] getSpecialitys() {
        return specialitys;
    }

    public void setSpecialitys(String[] specialitys) {
        this.specialitys = specialitys;
    }

    public int getAge() {
        return age;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
