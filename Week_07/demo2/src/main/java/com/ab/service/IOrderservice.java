package com.ab.service;

import com.ab.po.OrderPo;

public interface IOrderservice {

    OrderPo getOrderById(Long id);

    Long insertOrder(OrderPo orderPo);
}
