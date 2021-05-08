package com.abo.study.service.impl;

import com.abo.study.entity.TradeUser;
import com.abo.study.mapper.TradeUserMapper;
import com.abo.study.service.TradeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lnb
 * @date 2021/3/2 10:06
 * @description
 */
@Service
public class TradeUserServiceImpl implements TradeUserService {

    @Autowired
    private TradeUserMapper tradeUserMapper;

    @Override
    public List<TradeUser> list() {
        return tradeUserMapper.list();
    }
}
