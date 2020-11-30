package com.tools.cchttp.api;

/**
 * @author AlienChao
 * @date 2019/12/31 10:03
 */
public class UpdateBeanNew {

    /**
     * CREATE_DATE : 2020-03-18 10:30:02
     * SITE_URL : http://58.61.30.200:8084/platform/wpsapk/app-v1.0.3.apk
     * SYSTEM_NUMBER : 1.0.3
     * SYSTEM_TYPE : 1
     * SYSTEM_UPDATELOG : 测试测试
     * VERNAME : 移动办公平台
     * popupNo : 1
     */

    private String CREATE_DATE;
    private String SITE_URL;
    private String SYSTEM_NUMBER;
    private String SYSTEM_TYPE;
    private String SYSTEM_UPDATELOG;
    private String VERNAME;
    private String popupNo;

    public String getCREATE_DATE() {
        return CREATE_DATE == null ? "" : CREATE_DATE;
    }

    public void setCREATE_DATE(String CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }

    public String getSITE_URL() {
        return SITE_URL == null ? "" : SITE_URL;
    }

    public void setSITE_URL(String SITE_URL) {
        this.SITE_URL = SITE_URL;
    }

    public String getSYSTEM_NUMBER() {
        return SYSTEM_NUMBER == null ? "" : SYSTEM_NUMBER;
    }

    public void setSYSTEM_NUMBER(String SYSTEM_NUMBER) {
        this.SYSTEM_NUMBER = SYSTEM_NUMBER;
    }

    public String getSYSTEM_TYPE() {
        return SYSTEM_TYPE == null ? "" : SYSTEM_TYPE;
    }

    public void setSYSTEM_TYPE(String SYSTEM_TYPE) {
        this.SYSTEM_TYPE = SYSTEM_TYPE;
    }

    public String getSYSTEM_UPDATELOG() {
        return SYSTEM_UPDATELOG == null ? "" : SYSTEM_UPDATELOG;
    }

    public void setSYSTEM_UPDATELOG(String SYSTEM_UPDATELOG) {
        this.SYSTEM_UPDATELOG = SYSTEM_UPDATELOG;
    }

    public String getVERNAME() {
        return VERNAME == null ? "" : VERNAME;
    }

    public void setVERNAME(String VERNAME) {
        this.VERNAME = VERNAME;
    }

    public String getPopupNo() {
        return popupNo == null ? "" : popupNo;
    }

    public void setPopupNo(String popupNo) {
        this.popupNo = popupNo;
    }
}
