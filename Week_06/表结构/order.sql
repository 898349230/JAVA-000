create table `order`
(
	id bigint(64) auto_increment,
	goods_id bigint(64) null comment '商品id',
	user_id bigint(64) null,
	create_date datetime not null ,
	update_date datetime null,
	primary key (id)
)
comment '订单表';