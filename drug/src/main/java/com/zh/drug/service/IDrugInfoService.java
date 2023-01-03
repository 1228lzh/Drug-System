package com.zh.drug.service;

import com.zh.drug.pojo.DrugInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzh
 * @since 2022-12-29
 */
public interface IDrugInfoService extends IService<DrugInfo> {
    /**
     * 导入药品信息
     * @param file 文件
     */
    void importDrugInfo(MultipartFile file);
    /**
     * 导出药品信息
     * @param httpServletResponse httpServletResponse
     */
    void exportDrugInfo(HttpServletResponse httpServletResponse) throws IOException;

    void save(List<DrugInfo> cachedDataList);
}
