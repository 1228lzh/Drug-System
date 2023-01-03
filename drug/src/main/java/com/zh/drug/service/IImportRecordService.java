package com.zh.drug.service;

import com.zh.drug.pojo.ImportRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzh
 * @since 2022-12-29
 */
public interface IImportRecordService extends IService<ImportRecord> {

    /**
     * 修改
     * @param importRecord 实体
     * @return 布尔
     */
    boolean updateImportRecord(ImportRecord importRecord);

    boolean saveImportRecord(ImportRecord importRecord);
}
