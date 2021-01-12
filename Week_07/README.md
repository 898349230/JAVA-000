学习笔记

作业代码写在一起了

**2.（必做）**按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率

com.ab.week04.onmilliondata.service.impl.BatchInsertOneMillionDataImpl 批量插入数据（insert table() values()）

com.ab.week04.onmilliondata.service.impl.DataSourceBatchInsertOneMillionDataImpl 使用数据源批量插入数据 (insert table() values())

com.ab.week04.onmilliondata.service.impl.DataSourceOne2OneInsertOneMillionDataImpl 使用数据库连接池一条条插入数据

com.ab.week04.onmilliondata.service.impl.One2OneInsertMillionDataImpl 一条条插入数据

com.ab.InsertOneMillionDataTest 测试类

(图片为两种批量方式插入的时间，单条sql插入时间太长了，没有等到)

**2.（必做）**读写分离 - 动态切换数据源版本 1.0

com.ab.config.DataSourceConfiguration#MultiDataSource 配置读，写数据源

com.ab.config.MultiDataSource 动态获取master，slave数据源

com.ab.interceptor.MethodInterceptor 配置切面，根据service方法签名判断使用读，写数据源

com.ab.ReadWriteV1Test 测试类

**3.（必做）**读写分离 - 数据库框架版本 2.0

ss-jdbc 

sharding-jdbc (4.1.1) 实现的读写分离