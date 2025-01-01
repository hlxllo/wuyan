create table authenticate
(
    id            int auto_increment comment '主键'
        primary key,
    college_id    int                                  not null comment '学校 id',
    college_name  varchar(20)                          not null comment '学校名',
    study_card    varchar(255)                         null comment '学生证地址',
    email         varchar(50)                          null comment '邮箱号',
    major         varchar(20)                          not null comment '专业名',
    direction     tinyint(1)                           not null comment '方向，1-学术硕士，2-专业硕士',
    tutor         varchar(20)                          null comment '导师名',
    start_date    varchar(50)                          not null comment '入学时间',
    end_date      varchar(50)                          not null comment '毕业时间',
    authenticated tinyint(1) default 1                 not null comment '审核情况，0-未通过，1-待审核，2-通过',
    fail_message  text                                 null comment '审核未通过原因',
    status        tinyint(1) default 1                 not null comment '状态，0-删除，1-正常，其他待定',
    update_time   datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    add_time      datetime   default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '认证表';

