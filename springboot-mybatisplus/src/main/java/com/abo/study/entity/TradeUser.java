package com.abo.study.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class TradeUser {
    @TableId
    private Long userId;

    private String userName;

    private String userPassword;

    private String userMobile;

    private Integer userScore;

    private Date userRegTime;

    private Long userMoney;


}