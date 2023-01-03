package com.zh.drug.vo;

import com.zh.drug.enums.DrugTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZH
 * @Date: 2023-01-01 21:07
 */
@Data
public class ImportRecordVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String drugName;

    private String company;

    private Integer isRegister;
}
