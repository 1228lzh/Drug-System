package com.zh.drug.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.zh.drug.enums.DrugTypeEnum;
import com.zh.drug.utils.DrugTypeConverter;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lzh
 * @since 2022-12-29
 */
@Getter
@Setter
@TableName("drug_info")
public class DrugInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 药品id
     */
      @TableId(value = "drug_id", type = IdType.AUTO)
      @ExcelProperty(value = "DrugId",index = 0)
      private Integer drugId;

      /**
     * 药品类型id:1-处方药；2-非处方药
     */
      @ExcelProperty(value = "药品类型",index = 1,converter = DrugTypeConverter.class)
      private DrugTypeEnum drugType;

      /**
     * 药品名
     */
      @ExcelProperty(value = "药品名称",index = 2)
      private String drugName;

      /**
     * 药品规格 eg：24片/盒
     */
      @ExcelProperty(value = "药品规格",index = 3)
      private String drugFormat;

      /**
     * 生产日期
     * 日期格式化 @DateTimeFormat("yyyy-MM-dd")
     */
      @ExcelProperty(value = "生产日期",index = 4)
      @DateTimeFormat("yyyy-MM-dd")
      private Date productionDate;

      /**
     * 有效期
     */
      @ExcelProperty(value = "保质期",index = 5)
      private String validTime;

      /**
     * 价格
     */
      @ExcelProperty(value = "价格",index = 6)
      private Long price;

      /**
     * 药品批号
     */
      @ExcelProperty(value = "批号",index = 7)
      private String batchNum;

      /**
     * 生产公司
     */
      @ExcelProperty(value = "生产公司",index = 8)
      private String company;

      /**
     * 逻辑删除：0-未删除，1-已删除
     */
      @ExcelIgnore
      private Integer isDeleted;
}
