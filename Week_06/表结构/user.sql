create table user
(
	id bigint auto_increment,
	user_name varchar(64) not null,
	tel_phone integer(11) null,
	password varchar(255) not null,
	create_date datetime null,
	update_date datetime null,
	regist_ip varchar(20) null,
	primary key (id)
)
comment '用户表';