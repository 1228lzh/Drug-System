package com.zh.drug.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
  public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 仓库名
     */
      private String warehouseName;

      /**
     * 药品名
     */
      private String drugName;

      /**
     * 库存
     */
      private Integer stock;

      /**
     * 单位
     */
      private String unit;

      /**
     * 逻辑删除：0-未删除，1-已删除
     */
      private Byte isDeleted;
}
