package com.zh.drug;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.zh.drug.listener.DrugInfoListener;
import com.zh.drug.pojo.DrugInfo;
import com.zh.drug.service.IDrugInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@SpringBootTest
@Slf4j
class DrugApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    IDrugInfoService drugInfoService;

    @Test
    void read(){
        String fileName = "F:\\excel\\drug3.xls";
        List<DrugInfo> list = drugInfoService.list();
        EasyExcel.write(fileName,DrugInfo.class).sheet("药品信息").doWrite(list);
    }
}
