package com.yinrj.server.enums;

/**
 * @author: Yin
 * @date: 2021/1/13
 */
public enum FileUseEnum {
    COURSE("C", "课程"),
    TEACHER("T", "讲师");

    private String code;

    private String desc;

    FileUseEnum(String code, String desc) {
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

    public static FileUseEnum getByCode(String code) {
        for (FileUseEnum fileUseEnum : FileUseEnum.values()) {
            if (fileUseEnum.getCode().equals(code)) {
                return fileUseEnum;
            }
        }
        return null;
    }
}
