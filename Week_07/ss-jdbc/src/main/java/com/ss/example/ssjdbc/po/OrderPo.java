package com.ss.example.ssjdbc.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @classname: OrderPo
 * @description:
 * @author: sunxinbo
 * @time: 2020/12/8、19:43
 */
@Setter
@Getter
@ToString
public class OrderPo {
    private Long id;
    private Long userId;
    private Long goodsId;
}
