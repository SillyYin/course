package com.yinrj.server.enums;

/**
 * @author: Yin
 * @date: 2021/1/9
 */
public enum CourseChargeEnum {
    CHARGE("C", "收费"),
    FREE("F", "免费");

    private String code;

    private String desc;

    CourseChargeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
