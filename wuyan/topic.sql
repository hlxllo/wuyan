create table topic
(
    id          int auto_increment comment '主键'
        primary key,
    url         varchar(255)                         null comment '头像地址',
    name        varchar(20)                          not null comment '话题名称',
    follows     int        default 0                 not null comment '关注数',
    questions   int        default 0                 not null comment '问题数',
    status      tinyint(1) default 1                 not null comment '状态，0-删除，1-正常，其他待定',
    update_time datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    add_time    datetime   default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '话题表';

