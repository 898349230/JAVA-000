create table goods
(
	id bigint(64) auto_increment,
	name varchar(255) null,
	category_id bigint(64) null comment '种类id',
	price long null comment '价格',
	create_by bigint(64) null comment '创建人',
	create_date datetime null,
	update_time datetime null,
	constraint goods_pk
		primary key (id)
)
comment '商品表';

