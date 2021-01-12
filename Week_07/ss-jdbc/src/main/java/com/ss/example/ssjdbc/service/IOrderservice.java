package com.ss.example.ssjdbc.service;

import com.ss.example.ssjdbc.po.OrderPo;

public interface IOrderservice {

    OrderPo getOrderById(Long id);

    Long insertOrder(OrderPo orderPo);
}
