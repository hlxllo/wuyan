create table college
(
    id          int auto_increment comment '主键'
        primary key,
    name        varchar(20)                          not null comment '学校名称，逻辑唯一',
    pinyin      varchar(50)                          not null comment '学校拼音',
    status      tinyint(1) default 1                 not null comment '状态，0-删除，1-正常，其他待定',
    update_time datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    add_time    datetime   default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '学校表';

