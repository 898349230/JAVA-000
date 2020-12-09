package com.ab.week04.onmilliondata.service;

import java.sql.SQLException;

/**
 * @classname: one2one 一条条插入
 * @description:
 * @author: sunxinbo
 * @time: 2020/12/6、20:57
 */
public interface IInsertOneMillionData {
    Long insert() throws SQLException;
}
