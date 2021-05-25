package com.abo.study.service.impl;

import com.abo.study.listener.ExcelDemoListener;
import com.abo.study.model.EasyExcelDemo;
import com.abo.study.service.EasyExcelDemoService;
import com.alibaba.excel.EasyExcel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author lnb
 * @date 2021/5/25 17:11
 * @description
 */
@Service
public class EasyExcelDemoServiceImpl implements EasyExcelDemoService {


    @Override
    public void saveExcelDemo(MultipartFile file) {
        try {
            InputStream is = file.getInputStream();
            ExcelDemoListener demoListener = new ExcelDemoListener();
            EasyExcel.read(is, EasyExcelDemo.class, demoListener).sheet().doRead();
            List<EasyExcelDemo> data = demoListener.getData();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
