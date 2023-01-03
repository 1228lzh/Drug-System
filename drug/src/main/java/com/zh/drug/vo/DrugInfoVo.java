package com.zh.drug.vo;

import com.zh.drug.enums.DrugTypeEnum;
import lombok.Data;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.io.Serializable;

/**
 * @Author: ZH
 * @Date: 2022-12-31 22:24
 */
@Data
public class DrugInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private DrugTypeEnum drugType;

    private String drugName;
}
