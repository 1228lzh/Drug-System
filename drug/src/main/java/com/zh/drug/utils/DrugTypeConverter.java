package com.zh.drug.utils;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.zh.drug.enums.DrugTypeEnum;

/**
 * @Author: ZH
 * @Date: 2023-01-03 1:52
 */
public class DrugTypeConverter implements Converter<DrugTypeEnum> {

    @Override
    public Class supportJavaTypeKey() {
        return null;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }


    @Override
    public DrugTypeEnum convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
                                          GlobalConfiguration globalConfiguration) throws Exception {
        String drugTypeStr = cellData.getStringValue();
        if ("处方药".equals(drugTypeStr)){
            return DrugTypeEnum.PRESCRIPTION;
        }else if ("非处方药".equals(drugTypeStr)){
            return DrugTypeEnum.UN_PRESCRIPTION;
        }else {
            return null;
        }
    }
    /**
     * 将从数据库中查到的数据转换为 Excel 展示的数据
     *
     * @param value 枚举对象
     * @return
     */
    @Override
    public WriteCellData<?> convertToExcelData(DrugTypeEnum value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        // 将枚举类型按照 key 传值
        if (value.getTypeId()==1){
            return new WriteCellData<>("处方药");
        }else {
            return new WriteCellData<>("非处方药");
        }

    }
}
