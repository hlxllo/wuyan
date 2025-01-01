create table follow
(
    id          int auto_increment comment '主键'
        primary key,
    follow_id   int                                  not null comment '关注人 id',
    followed_id int                                  not null comment '被关注人 id',
    status      tinyint(1) default 1                 not null comment '状态，0-删除，1-正常，其他待定',
    update_time datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    add_time    datetime   default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '关注关系表';

