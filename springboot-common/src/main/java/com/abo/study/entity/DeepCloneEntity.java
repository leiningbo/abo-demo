package com.abo.study.entity;

import lombok.Data;

/**
 * 实现深拷贝
 *
 * @author lnb
 * @date 2021/4/1 16:38
 * @description
 */
@Data
public class DeepCloneEntity implements Cloneable {

    private String name;

    private Integer age;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
