package com.zh.drug.service.impl;

import com.alibaba.excel.EasyExcel;
import com.zh.drug.listener.DrugInfoListener;
import com.zh.drug.pojo.DrugInfo;
import com.zh.drug.mapper.DrugInfoMapper;
import com.zh.drug.service.IDrugInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzh
 * @since 2022-12-29
 */
@Service
public class DrugInfoServiceImpl extends ServiceImpl<DrugInfoMapper, DrugInfo> implements IDrugInfoService {

    @Override
    public void importDrugInfo(MultipartFile file) {
        try {
            // baseMapper就是dictMapper，在impl中，可不注入dictMapper而直接使用baseMapper
            DrugInfoListener drugInfoListener = new DrugInfoListener(baseMapper);
            EasyExcel.read(file.getInputStream(),DrugInfo.class,drugInfoListener)
                    .sheet()
                    .doRead();
            System.out.println("解析完之后的数据：---"+drugInfoListener.getCachedDataList());
            //获取数据
            List<DrugInfo> cachedDataList = drugInfoListener.getCachedDataList();
            //批量添加到数据库
            saveBatch(cachedDataList);
            //结束后销毁不用的资源
            cachedDataList.clear();
            //查看销毁后的结果
            System.out.println("cachedDataList = " + cachedDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportDrugInfo(HttpServletResponse httpServletResponse) throws IOException {
        // 设置下载信息
        httpServletResponse.setContentType("application/vnd.ms-excel");
        httpServletResponse.setCharacterEncoding("utf-8");
        String fileName = "DrugInfo";
        httpServletResponse.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
        List<DrugInfo> drugInfoList = list();
        EasyExcel.write(httpServletResponse.getOutputStream(), DrugInfo.class)
                .sheet("drugInfo")
                .doWrite(drugInfoList);
    }

    @Override
    public void save(List<DrugInfo> cachedDataList) {
        saveBatch(cachedDataList);
    }
}
