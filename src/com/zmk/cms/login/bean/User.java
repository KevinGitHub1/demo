package com.zmk.cms.login.bean;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String loginName;//登录名
    private String icon;//照片
    private String mobileNumber;//手机号
    private String loginTime;//登录时间
    private String loginAdress;//登录地址
    private String userName;//真实姓名
    private String password;//密码
    private String emailAdress;//邮箱
    private String birthday;//生日
    private String province;//省
    private String city;//市
    private String county;//县/区
    private String adress;//详细地址
    private String motto;//座右铭
    private String hobby;//爱好

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setUserName(String name) {
        this.userName = name;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     *            the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    /**
     * @return the loginTime
     */
    public String getLoginTime() {
        return loginTime;
    }

    /**
     * @param loginTime
     *            the loginTime to set
     */
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * @return the loginAdress
     */
    public String getLoginAdress() {
        return loginAdress;
    }

    /**
     * @param loginAdress
     *            the loginAdress to set
     */
    public void setLoginAdress(String loginAdress) {
        this.loginAdress = loginAdress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
