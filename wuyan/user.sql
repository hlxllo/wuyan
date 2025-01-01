create table user
(
    id           int auto_increment comment '主键'
        primary key,
    url          text                                 not null comment '头像地址',
    follows      int        default 0                 not null comment '关注数',
    fans         int        default 0                 not null comment '粉丝数',
    college_id   int                                  not null comment '学校 id',
    college_name varchar(20)                          not null comment '学校名',
    major        varchar(20)                          not null comment '专业名',
    direction    tinyint(1)                           not null comment '方向，1-学术硕士，2-专业硕士',
    start_date   varchar(50)                          not null comment '入学时间',
    end_date     varchar(50)                          not null comment '毕业时间',
    status       tinyint(1) default 1                 not null comment '状态，0-删除，1-正常，其他待定',
    update_time  datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    add_time     datetime   default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '用户信息表';

