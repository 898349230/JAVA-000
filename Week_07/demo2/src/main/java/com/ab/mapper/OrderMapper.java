package com.ab.mapper;

import com.ab.po.OrderPo;

/**
 * @author root
 */
public interface OrderMapper {

    /**
     * @description
     * @param po
     * @return int
     * @author sunxinbo
     * @time 2020/12/9 23:14
     */
    Long insert(OrderPo po);
    
    /**
     * @description
     * @param id
     * @return com.ab.po.OrderPo
     * @author sunxinbo
     * @time 2020/12/9 23:14
     */   
    OrderPo getById(Long id);
}

