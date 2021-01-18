# boot_demo库
drop table if exists orm_demo;
create table orm_demo(
	id bigint primary key auto_increment comment '主键',
    info varchar(64) not null comment '信息',
    resume varchar(125) default null comment '简述',
    delete_flag tinyint(2) default 0 comment '逻辑删除 0-未删除，1-逻辑删除',
    create_time datetime not null default current_timestamp comment '创建时间',
    update_time datetime not null default current_timestamp on update current_timestamp comment '更新时间'
);

# boot_example库
drop table if exists orm_example;
create table orm_example(
	id bigint primary key auto_increment comment '主键',
    info varchar(64) not null comment '信息',
    resume varchar(125) default null comment '简述',
    delete_flag tinyint(2) default 0 comment '逻辑删除 0-未删除，1-逻辑删除',
    create_time datetime not null default current_timestamp comment '创建时间',
    update_time datetime not null default current_timestamp on update current_timestamp comment '更新时间'
);
