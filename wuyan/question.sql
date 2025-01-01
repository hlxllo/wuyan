create table question
(
    id          int auto_increment comment '主键'
        primary key,
    user_id     int                                  not null comment '作者 id',
    title       varchar(34)                          not null comment '问题题目',
    type        tinyint(1)                           not null comment '所属类别',
    content     text                                 null comment '正文内容',
    urls        text                                 null comment '图片地址（最多5张）',
    answers     int        default 0                 not null comment '回答数',
    browses     int        default 0                 not null comment '浏览数',
    status      tinyint(1) default 1                 not null comment '状态，0-删除，1-正常，其他待定',
    update_time datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    add_time    datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    heat        int        default 0                 not null comment '热度'
)
    comment '问题表';

