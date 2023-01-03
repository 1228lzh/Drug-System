package com.zh.drug.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.drug.common.result.Result;
import com.zh.drug.enums.DrugTypeEnum;
import com.zh.drug.pojo.DrugInfo;
import com.zh.drug.vo.DrugInfoVo;
import com.zh.drug.vo.ImportRecordVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.zh.drug.service.IImportRecordService;
import com.zh.drug.pojo.ImportRecord;

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
@RequestMapping("/drug/import")
@CrossOrigin
public class ImportRecordController {

    @Resource
    private IImportRecordService importRecordService;


    @PostMapping
    public Result saveImportRecord(@RequestBody ImportRecord importRecord){
        boolean isSave = importRecordService.saveImportRecord(importRecord);
        return isSave?Result.ok():Result.fail();
    }

    @PutMapping
    public Result updateImportRecord(@RequestBody ImportRecord importRecord){
        boolean isUpdate = importRecordService.updateImportRecord(importRecord);
        return isUpdate?Result.ok():Result.fail();
    }

    @DeleteMapping("/{id}")
    public Result deleteImportRecordById(@PathVariable Integer id){
        boolean isDel = importRecordService.removeById(id);
        return isDel?Result.ok():Result.fail();
    }
    @DeleteMapping
    public Result batchDeleteImportRecord(@RequestBody List<Integer> ids){
        System.out.println("ids = " + ids);
        if (!ids.isEmpty()){
            importRecordService.removeByIds(ids);
            return Result.ok();
        }else {
            return fail();
        }
    }
    @GetMapping
    public Result getAll(){
        List<ImportRecord> importRecordList = importRecordService.list();
        return !importRecordList.isEmpty()?Result.ok(importRecordList):Result.fail();
    }

    @GetMapping("/{id}")
    public Result getImportRecordById(@PathVariable Integer id){
        ImportRecord importRecord = importRecordService.getById(id);
        return importRecord!=null?Result.ok(importRecord):Result.fail();
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
         return Result.ok(importRecordService.page(new Page<>(pageNum,pageSize)));
    }

    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result findPage(@PathVariable Integer pageNum,
                           @PathVariable Integer pageSize,
                           @RequestBody(required = false) ImportRecordVo importRecordVo){
        QueryWrapper<ImportRecord> wrapper = new QueryWrapper<>();
        System.out.println("importRecordVo = " + importRecordVo);
        if (importRecordVo!=null){
            String drugName = importRecordVo.getDrugName();
            wrapper.like(!StringUtils.isEmpty(drugName),"drug_name",drugName);
            String company = importRecordVo.getCompany();
            // mp的每个条件查询都可以有条件
            wrapper.like(!StringUtils.isEmpty(drugName),"company",company);
            Integer isRegister = importRecordVo.getIsRegister();
            wrapper.eq(isRegister!=null,"is_register",isRegister);
        }
        return ok(importRecordService.page(new Page<>(pageNum,pageSize),wrapper));
    }
}

