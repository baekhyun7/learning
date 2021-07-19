package com.zh.learning.constants;

/**
 * @author zh
 * @date 2021-07-16 9:39
 */
public enum RedisLockTypeEnum {
    /**
     * 业务1
     */
    BUSSINESS("business1", "1");

    public String business;
    public String code;

    RedisLockTypeEnum(String business, String code) {
        this.business = business;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getBusiness() {
        return business;
    }

    public String getUniqueValue(String unique) {
        return String.format("%s:%s", this.getCode(), this.getBusiness()+unique);
    }
}
