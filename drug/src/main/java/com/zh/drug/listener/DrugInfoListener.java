package com.zh.drug.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.zh.drug.mapper.DrugInfoMapper;
import com.zh.drug.pojo.DrugInfo;
import com.zh.drug.service.IDrugInfoService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ZH
 * @Date: 2023-01-03 0:04
 */
@Slf4j
public class DrugInfoListener extends AnalysisEventListener<DrugInfo> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    /**
     * 缓存的数据
     */
    private List<DrugInfo> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private DrugInfoMapper drugInfoMapper;
    /**
     * 构造器注入
     * @param drugInfoMapper drugInfoMapper
     */
    public DrugInfoListener(DrugInfoMapper drugInfoMapper) {
        this.drugInfoMapper = drugInfoMapper;
    }
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param drugInfo    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(DrugInfo drugInfo, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(drugInfo));
        cachedDataList.add(drugInfo);
//        if (cachedDataList.size() >= BATCH_COUNT) {
//            saveData();
//            // 存储完成清理 list
//            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库

        log.info("所有数据解析完成！");
    }


    public List<DrugInfo> getCachedDataList(){
        //把存储的数据通过此方法暴露出去
        return cachedDataList;
    }
}
