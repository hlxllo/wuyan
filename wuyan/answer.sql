create table answer
(
    id          int auto_increment comment '主键'
        primary key,
    user_id     int                                  not null comment '作者 id',
    question_id int                                  not null comment '关联的问题 id',
    content     text                                 not null comment '回答内容',
    urls        text                                 null comment '图片地址，用逗号分隔（最多 9 张）',
    likes       int        default 0                 not null comment '点赞数',
    favorites   int        default 0                 not null comment '收藏数',
    comments    int        default 0                 not null comment '评论数',
    heat        int        default 0                 not null comment '热度（评论 * 10 + 点赞）',
    status      tinyint(1) default 1                 not null comment '状态，0-删除，1-正常，其他待定',
    update_time datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    add_time    datetime   default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '回答表';

