create table comment
(
    id               int auto_increment comment '主键'
        primary key,
    user_id          int                                  not null comment '评论人 id',
    answer_id        int                                  not null comment '关联的回答 id',
    first_comment_id int                                  null comment '关联的一级评论 id',
    content          text                                 not null comment '评论内容',
    likes            int        default 0                 not null comment '点赞数',
    second_comments  int                                  null comment '二级评论数',
    status           tinyint(1) default 1                 not null comment '状态，0-删除，1-正常，其他待定',
    update_time      datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    add_time         datetime   default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '评论关系表';

