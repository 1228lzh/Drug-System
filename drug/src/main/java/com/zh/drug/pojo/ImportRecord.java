package com.zh.drug.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
@TableName("import_record")
public class ImportRecord implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 进货id
     */
      @TableId(value = "import_id", type = IdType.AUTO)
      private Integer importId;

      /**
     * 药品名
     */
      private String drugName;

      /**
     * 仓库名
     */
      private String warehouseName;

      /**
     * 供应商
     */
      private String supplier;

      /**
     * 进货日期
     */
      private Date importDate;

      /**
     * 进货数量
     */
      private Integer importNum;

      /**
     * 进货单位
     */
      private String unit;

      /**
     * 是否登记:0-未登记；1-已登记
     */
      private Integer isRegister;

      /**
     * 逻辑删除：0-未删除，1-已删除
     */
      private Byte isDeleted;
      /**
       * 旧的数量
       */
      @TableField(exist = false)
      private Integer newImportNum;
}
