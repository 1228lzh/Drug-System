package com.zh.drug.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.drug.common.result.Result;
import com.zh.drug.pojo.ImportRecord;
import com.zh.drug.vo.ImportRecordVo;
import com.zh.drug.vo.WarehouseVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.zh.drug.service.IWarehouseService;
import com.zh.drug.pojo.Warehouse;

import static com.zh.drug.common.result.Result.fail;
import static com.zh.drug.common.result.Result.ok;


/**
* <p>
*  前端控制器
* </p>
*
* @author lzh
* @since 2022-12-29
*/
@RestController
@RequestMapping("/drug/warehouse")
@CrossOrigin
public class WarehouseController {

    @Resource
    private IWarehouseService warehouseService;


    @PostMapping
    public Result saveOrUpdate(@RequestBody Warehouse warehouse){
        boolean isSave = warehouseService.saveOrUpdate(warehouse);
        return isSave?Result.ok():Result.fail();
    }

    @DeleteMapping("/{id}")
    public Result deleteWarehouseById(@PathVariable Integer id){
        boolean isDel = warehouseService.removeById(id);
        return isDel?Result.ok():Result.fail();
    }
    @DeleteMapping
    public Result batchDeleteWarehouse(@RequestBody List<Integer> ids){
        if (!ids.isEmpty()){
            warehouseService.removeByIds(ids);
            return Result.ok();
        }else {
            return fail();
        }
    }
    @GetMapping
    public Result getAll(){
        List<Warehouse> warehouseList = warehouseService.list();
        return !warehouseList.isEmpty()?Result.ok(warehouseList):Result.fail();
    }

    @GetMapping("/{id}")
    public Result getWarehouseById(@PathVariable Integer id){
        Warehouse warehouse = warehouseService.getById(id);
        return warehouse!=null?Result.ok(warehouse):Result.fail();
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
         return Result.ok(warehouseService.page(new Page<>(pageNum,pageSize)));
    }

    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result findPage(@PathVariable Integer pageNum,
                           @PathVariable Integer pageSize,
                           @RequestBody(required = false) WarehouseVo warehouseVo){
        QueryWrapper<Warehouse> wrapper = new QueryWrapper<>();

        if (warehouseVo!=null){
            String warehouseName = warehouseVo.getWarehouseName();
            wrapper.like(!StringUtils.isEmpty(warehouseName),"warehouse_name",warehouseName);
            String drugName = warehouseVo.getDrugName();
            wrapper.like(!StringUtils.isEmpty(drugName),"drug_name",drugName);

        }
        return ok(warehouseService.page(new Page<>(pageNum,pageSize),wrapper));
    }
}

