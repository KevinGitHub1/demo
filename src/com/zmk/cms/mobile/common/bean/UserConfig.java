package com.zmk.cms.mobile.common.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
 
public class UserConfig {
 
	private String id;
	 
	private String username;// 用户名
	 
	private String skin;// 皮肤设置
	 
	private String monitorDepId;// 监测单位id
	 
	private String monitorDepName;//监测单位名称
	 
	private String monitorSbTime;// 设备监测时间段(x小时)
	 
	private String monitorSbLevel;// 设备监测等级(1:一级；2:二级；3:三级；12:一二级；13:一三级；23:二三级；123:一二三级)
	 
	private String monitorSbDo;// 设备监测处理情况(0:全部1:未处理2:已处理)
	 
	private String messageSbLevel;// 消息推送设备报警等级(1:一级；2:二级；3:三级；12:一二级；13:一三级；23:二三级；123:一二三级)
	 
	private String messageBbLevel;// 消息推送版本报警等级(1:一级；2:二级；3:三级；12:一二级；13:一三级；23:二三级；123:一二三级)
	 
	private String messageJcrk;// 消息推送机车入库提醒(0:提醒；1:不提醒)
 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getMonitorDepId() {
        return monitorDepId;
    }

    public void setMonitorDepId(String monitorDepId) {
        this.monitorDepId = monitorDepId;
    }

    public String getMonitorDepName() {
        return monitorDepName;
    }

    public void setMonitorDepName(String monitorDepName) {
        this.monitorDepName = monitorDepName;
    }

    public String getMonitorSbTime() {
        return monitorSbTime;
    }

    public void setMonitorSbTime(String monitorSbTime) {
        this.monitorSbTime = monitorSbTime;
    }

    public String getMonitorSbLevel() {
        return monitorSbLevel;
    }

    public void setMonitorSbLevel(String monitorSbLevel) {
        this.monitorSbLevel = monitorSbLevel;
    }

    public String getMonitorSbDo() {
        return monitorSbDo;
    }

    public void setMonitorSbDo(String monitorSbDo) {
        this.monitorSbDo = monitorSbDo;
    }

    public String getMessageSbLevel() {
        return messageSbLevel;
    }

    public void setMessageSbLevel(String messageSbLevel) {
        this.messageSbLevel = messageSbLevel;
    }

    public String getMessageBbLevel() {
        return messageBbLevel;
    }

    public void setMessageBbLevel(String messageBbLevel) {
        this.messageBbLevel = messageBbLevel;
    }

    public String getMessageJcrk() {
        return messageJcrk;
    }

    public void setMessageJcrk(String messageJcrk) {
        this.messageJcrk = messageJcrk;
    }

}
