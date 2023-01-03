package com.zh.drug.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.drug.vo.DrugInfoVo;
import com.zh.drug.common.result.Result;
import com.zh.drug.enums.DrugTypeEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.zh.drug.service.IDrugInfoService;
import com.zh.drug.pojo.DrugInfo;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/drug/drugInfo")
@CrossOrigin
public class DrugInfoController {

    @Resource
    private IDrugInfoService drugInfoService;


    @PostMapping
    public Result saveOrUpdate(@RequestBody DrugInfo drugInfo){
        boolean isSave = drugInfoService.saveOrUpdate(drugInfo);
        return isSave? ok():Result.fail();
    }

    @DeleteMapping("/{id}")
    public Result deleteDrugInfoById(@PathVariable Integer id){
        boolean isDel = drugInfoService.removeById(id);
        return isDel? ok():Result.fail();
    }

    @DeleteMapping
    public Result batchDeleteDrugInfo(@RequestBody List<Integer> ids){
        System.out.println("ids = " + ids);
        if (!ids.isEmpty()){
            drugInfoService.removeByIds(ids);
            return Result.ok();
        }else {
            return fail();
        }
    }

    @GetMapping
    public Result getAll(){
        List<DrugInfo> drugInfoList = drugInfoService.list();
        return !drugInfoList.isEmpty()? ok(drugInfoList,drugInfoService.count()):Result.fail();
    }

    @GetMapping("/{id}")
    public Result getDrugInfoById(@PathVariable Integer id){
        DrugInfo drugInfo = drugInfoService.getById(id);
        return drugInfo!=null? ok(drugInfo):Result.fail();
    }

    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result findPage(@PathVariable Integer pageNum,
                           @PathVariable Integer pageSize,
                           @RequestBody(required = false)DrugInfoVo drugInfoVo){
        QueryWrapper<DrugInfo> wrapper = new QueryWrapper<>();
        System.out.println("drugInfoVo = " + drugInfoVo);
        if (drugInfoVo!=null){
            // 获取枚举的key
            DrugTypeEnum drugType = drugInfoVo.getDrugType();
            if (drugType!=null){
                Integer typeId = drugType.getTypeId();
                wrapper.eq("drug_type",typeId);
                System.out.println("typeId = " + typeId);
            }
            // 获得名字
            String drugName = drugInfoVo.getDrugName();
            // mp的每个条件查询都可以有条件
            wrapper.like(!StringUtils.isEmpty(drugName),"drug_name",drugName);
        }
        return ok(drugInfoService.page(new Page<>(pageNum,pageSize),wrapper));
    }

    @PostMapping("/page")
    public Result findPage2(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestBody(required = false)DrugInfoVo drugInfoVo){
        QueryWrapper<DrugInfo> wrapper = new QueryWrapper<>();
        System.out.println("drugInfoVo = " + drugInfoVo);
        if (drugInfoVo!=null){
            // 获取枚举的key
            DrugTypeEnum drugType = drugInfoVo.getDrugType();
            if (drugType!=null){
                Integer typeId = drugType.getTypeId();
                wrapper.eq("drug_type",typeId);
            }
            // 获得名字
            String drugName = drugInfoVo.getDrugName();
            // mp的每个条件查询都可以有条件
            wrapper.like(!StringUtils.isEmpty(drugName),"drug_name",drugName);
        }

        return ok(drugInfoService.page(new Page<>(pageNum,pageSize),wrapper));
    }
    /**
     * 导入字典文件
     * @param file 文件
     * @return ok
     */
    @PostMapping("import")
    public Result importDrugInfo(MultipartFile file) {
        drugInfoService.importDrugInfo(file);
        return Result.ok();
    }
    /**
     * 导出字典
     * @param httpServletResponse response
     */
    @GetMapping("export")
    public void exportDrugInfo(HttpServletResponse httpServletResponse) throws IOException {
        drugInfoService.exportDrugInfo(httpServletResponse);
        // 关闭返回 日志就不会报错了
    }
}

