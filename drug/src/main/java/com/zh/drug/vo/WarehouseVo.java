package com.zh.drug.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZH
 * @Date: 2023-01-02 15:38
 */
@Data
public class WarehouseVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String warehouseName;
    private String drugName;
}
