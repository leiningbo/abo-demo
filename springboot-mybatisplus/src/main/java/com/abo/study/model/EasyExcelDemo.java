package com.abo.study.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author lnb
 * @date 2021/5/25 16:47
 * @description
 */
@Data
public class EasyExcelDemo {

    @ExcelProperty( value = "姓名", index = 0 )
    private String name;

    @ExcelProperty( value = "年龄", index = 1 )
    private int age;

    @ExcelProperty( value = "出生日期", index = 2 )
    private Date birthTime;


}
