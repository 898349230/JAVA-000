package com.ss.example.ssjdbc.controller;

import com.ss.example.ssjdbc.po.OrderPo;
import com.ss.example.ssjdbc.service.IOrderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @classname: OrderController
 * @description:
 * @author: sunxinbo
 * @time: 2021/1/10、19:47
 */
@Controller
public class OrderController {

    @Autowired
    private IOrderservice orderservice;

    @RequestMapping("getOrderById")
    @ResponseBody
    public OrderPo getOrderById(Long id){
        return orderservice.getOrderById(id);
    }

    @RequestMapping("putOrder")
    @ResponseBody
    public String putOrder(OrderPo po){
        orderservice.insertOrder(po);
        return "新增成功";
    }
}
