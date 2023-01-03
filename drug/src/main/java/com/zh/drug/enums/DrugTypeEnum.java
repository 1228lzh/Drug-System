package com.zh.drug.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Author: ZH
 * @Date: 2022-12-31 15:31
 */
@Getter
public enum DrugTypeEnum {
    /**
     * 药物类型枚举类
     */
    PRESCRIPTION(1,"处方药"),
    UN_PRESCRIPTION(2,"非处方药");

    @EnumValue
    private final Integer typeId;

    @JsonValue
    private final String typeName;

    DrugTypeEnum(Integer typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }
}
