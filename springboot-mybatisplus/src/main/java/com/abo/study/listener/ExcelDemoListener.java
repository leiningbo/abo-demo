package com.abo.study.listener;

import com.abo.study.model.EasyExcelDemo;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lnb
 * @date 2021/5/25 16:57
 * @description
 */
@Component
@Slf4j
@Scope(value = "prototype")
public class ExcelDemoListener extends AnalysisEventListener<EasyExcelDemo> {

    private List<EasyExcelDemo> data = new ArrayList<>();

    /**
     * 每读一行内容，都会调用一次 invoke ，在invoke可以操作读取到的数据
     * @param easyExcelDemo  每次读取到的数据封装的对象
     * @param analysisContext
     */
    @Override
    public void invoke(EasyExcelDemo easyExcelDemo, AnalysisContext analysisContext) {
        data.add(easyExcelDemo);
        System.out.println("EasyExcelDemo:"+ JSON.toJSONString(easyExcelDemo));
    }

    /**
     * 这里会一行行的返回头
     * 监听器只需要重写这个方法就可以读取到头信息
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析到一条头数据：{}", JSON.toJSONString(headMap));
    }

    /**
     * 读取完整个文档之后调用的方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<EasyExcelDemo> getData() {
        return data;
    }

}
