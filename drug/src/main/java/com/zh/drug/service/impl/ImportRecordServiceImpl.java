package com.zh.drug.service.impl;

import com.zh.drug.pojo.DrugInfo;
import com.zh.drug.pojo.ImportRecord;
import com.zh.drug.mapper.ImportRecordMapper;
import com.zh.drug.pojo.Warehouse;
import com.zh.drug.service.IDrugInfoService;
import com.zh.drug.service.IImportRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.drug.service.IWarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzh
 * @since 2022-12-29
 */
@Service
public class ImportRecordServiceImpl extends ServiceImpl<ImportRecordMapper, ImportRecord> implements IImportRecordService {

    @Resource
    IWarehouseService warehouseService;
    @Resource
    IDrugInfoService drugInfoService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveImportRecord(ImportRecord importRecord) {
        // 判断是否在drugInfo表中
        DrugInfo drug = drugInfoService.query().eq("drug_name", importRecord.getDrugName()).one();
        // 在就标记为已登记,默认未登记
        if (drug!=null){
            importRecord.setIsRegister(1);
        }
        // 入库
        // 判断仓库中是否有该药品
        Warehouse warehouse = warehouseService.query().eq("drug_name", importRecord.getDrugName()).one();
        if (warehouse!=null){
            //仓库中有该药品
            // 增加库存
            Integer importNum = importRecord.getImportNum();
            int newStock = warehouse.getStock()+importNum;
            warehouse.setStock(newStock);
            warehouseService.updateById(warehouse);
            // warehouseService.update().eq("drug_name", importRecord.getDrugName()).set("stock",newStock).update();
        }else {
            // 没有该药品，新增
            Warehouse newWarehouse = new Warehouse();
            newWarehouse.setDrugName(importRecord.getDrugName());
            newWarehouse.setWarehouseName(importRecord.getWarehouseName());
            newWarehouse.setStock(importRecord.getImportNum());
            newWarehouse.setUnit(importRecord.getUnit());
            warehouseService.save(newWarehouse);
        }
        // 加入进货表
        return save(importRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateImportRecord(ImportRecord importRecord) {
        // 只能修改库存库存
        Warehouse warehouse = warehouseService.query().eq("drug_name", importRecord.getDrugName()).one();
        System.out.println("warehouse = " + warehouse);
        System.out.println("importRecordNum = " + importRecord.getImportNum());
        System.out.println("importRecordOldNum = " + importRecord.getNewImportNum());
        if (warehouse!=null && importRecord.getNewImportNum()!=null){

            int diff = importRecord.getImportNum() - importRecord.getNewImportNum();
            System.out.println("diff = " + diff);
            if (diff>0) {
                // 减少了 减去正数
                warehouse.setStock(warehouse.getStock() - diff);
                System.out.println("warehouseStock = " + warehouse.getStock());
            }else {
                // 增加了 减去负数=加了正数
                warehouse.setStock(warehouse.getStock() - diff);
                System.out.println("warehouseStock = " + warehouse.getStock());
            }
            // 更新
            warehouseService.updateById(warehouse);
        }
        importRecord.setImportNum(importRecord.getNewImportNum());
        // 更新进货表
        return updateById(importRecord);
    }
}

