package com.ss.example.ssjdbc.service.impl;

import com.ss.example.ssjdbc.mapper.OrderMapper;
import com.ss.example.ssjdbc.po.OrderPo;
import com.ss.example.ssjdbc.service.IOrderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @classname: OrderserviceImpl
 * @description:
 * @author: sunxinbo
 * @time: 2020/12/8、19:42
 */
@Service
public class OrderServiceImpl implements IOrderservice {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderPo getOrderById(Long id) {
        return orderMapper.getById(id);
    }

    @Override
    public Long insertOrder(OrderPo orderPo) {

        return orderMapper.insert(orderPo);
    }
}
