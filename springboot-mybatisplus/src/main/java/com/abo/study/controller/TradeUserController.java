package com.abo.study.controller;

import com.abo.study.entity.TradeUser;
import com.abo.study.service.TradeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lnb
 * @date 2021/5/8 10:20
 * @description
 */
@RestController
@RequestMapping(value = "trade/user")
public class TradeUserController {

    @Autowired
    private TradeUserService tradeUserService;

    @RequestMapping( value = "/list", method = RequestMethod.GET )
    public List<TradeUser> list() {
        return tradeUserService.list();
    }


}
