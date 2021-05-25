package com.abo.study.controller;

import com.abo.study.service.EasyExcelDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lnb
 * @date 2021/5/25 16:25
 * @description
 */
@RestController
@RequestMapping( value = "/excel" )
public class EasyExcelController {

    @Autowired
    private EasyExcelDemoService easyExcelDemoService;

    @RequestMapping( value = "/demoImport", method = RequestMethod.POST )
    public String demoImport(@RequestParam( value = "file" ) MultipartFile file) {
        easyExcelDemoService.saveExcelDemo(file);
        return "导入成功";
    }


}
