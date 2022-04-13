-- auto-generated definition
create table submit_records
(
    id            bigint auto_increment comment '自增主键',
    question_id   bigint       default 0                 null comment '问题id',
    question_name varchar(255) default ''                not null comment '题目名称',
    user_id       bigint       default 0                 not null comment '用户id',
    create_time   timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    lang          varchar(32)  default ''                not null comment '提交语言',
    result        int          default -1                not null comment '结果状态',
    time_cost     int          default -1                not null comment '消耗时间',
    memory_cost   double       default -1                null comment '内存消耗',
    comments      varchar(255) default ''                null comment '备注',
    codes         text                                   not null comment '代码',
    constraint submit_records_id_uindex
        unique (id)
)
    comment '提交记录';

create index submit_records_question_id_user_id_index
    on submit_records (question_id, user_id);

alter table submit_records
    add primary key (id);


-- auto-generated definition
create table testcase_question
(
    id            bigint auto_increment comment 'ID',
    question_id   bigint                                 null comment '题目ID',
    testcase      text                                   null comment '测试用例',
    result        text                                   null comment '测试用例结果',
    is_delete     tinyint(1)   default 0                 not null comment '是否删除',
    creator       bigint                                 not null comment '创建者',
    creator_name  varchar(20)                            not null comment '创建者姓名',
    modify_time   timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    modifier_name varchar(255) default ''                not null comment '修改人姓名',
    modifier      bigint       default 0                 not null comment '修改人id',
    create_time   timestamp    default CURRENT_TIMESTAMP null comment '创建时间',
    delete_time   timestamp    default CURRENT_TIMESTAMP null comment '删除时间',
    constraint testcase_question_id_uindex
        unique (id)
)
    comment '题目用例';

create index testcase_question_question_id_index
    on testcase_question (question_id);

alter table testcase_question
    add primary key (id);

-- auto-generated definition
create table user_base
(
    id            bigint auto_increment comment 'ID'
        primary key,
    is_delete     tinyint(1)   default 0                              null comment '是否删除',
    NAME          varchar(20)                                         not null comment '名字',
    create_time   timestamp    default CURRENT_TIMESTAMP              null comment '创建时间',
    delete_time   timestamp    default CURRENT_TIMESTAMP              null comment '删除时间',
    mail          varchar(255)                                        null comment '邮箱',
    phone_number  varchar(20)                                         null comment '电话号',
    score         int                                                 null comment '分数',
    photo_hash    text                                                null comment '头像hash',
    authority     tinyint                                             null comment '权限',
    password      varchar(128)                                        null comment '密码',
    ranking       int                                                 null comment '排名',
    solved_number int          default 0                              null comment '解决问题数量',
    easy_number   int          default 0                              null comment '简单题数量',
    medium_number int          default 0                              null comment '中等题数量',
    hard_number   int          default 0                              null comment '困难题数量',
    profile_photo text                                                null comment '头像',
    intro         varchar(255) default 'This is an self introduction editing by yourself.' null comment '自我介绍',
    constraint phone_number
        unique (phone_number)
)
    comment '用户';

create index user_id_index
    on user_base (id);



-- auto-generated definition
create table question
(
    id             bigint auto_increment comment 'ID',
    is_delete      tinyint(1)   default 0                 null comment '是否删除',
    NAME           varchar(20)                            not null comment '名字',
    create_time    timestamp    default CURRENT_TIMESTAMP null comment '创建时间',
    delete_time    timestamp    default CURRENT_TIMESTAMP null comment '删除时间',
    content        text                                   null comment '题目内容',
    creator        bigint       default 0                 not null comment '创建者',
    creator_name   varchar(20)  default ''                not null comment '创建者姓名',
    modifier       bigint       default 0                 not null comment '修改人id',
    modifier_name  varchar(255) default ''                not null comment '修改人姓名',
    modify_time    timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    is_hide        tinyint(1)   default 0                 not null comment '是否隐藏',
    question_level int          default 0                 not null comment '难度',
    tags           varchar(255) default ''                null,
    total          bigint       default 0                 not null comment '总提交次数',
    rate           double       default 0                 not null comment '通过率',
    accept         bigint       default 0                 not null comment '通过次数',
    constraint question_id_uindex
        unique (id)
)
    comment '题目';

alter table question
    add primary key (id);

-- auto-generated definition
create table question_competition
(
    id             int auto_increment comment '主键id',
    question_id    bigint               null comment '题目ID',
    competition_id bigint               null comment '比赛ID',
    is_delete      tinyint(1) default 0 null comment '是否删除',
    score          int                  null comment '分数',
    constraint question_competition_id_uindex
        unique (id)
)
    comment '题目-比赛映射';

create index question_competition_index
    on question_competition (competition_id);

alter table question_competition
    add primary key (id);


-- auto-generated definition
create table competition
(
    id                 bigint auto_increment comment 'ID',
    is_delete          tinyint(1) default 0                 null comment '是否删除',
    NAME               varchar(20)                          not null comment '名字',
    create_time        timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    delete_time        timestamp  default CURRENT_TIMESTAMP null comment '删除时间',
    brief_introduction tinytext                             null comment '简介',
    creator            bigint                               not null comment '创建者',
    creator_name       varchar(20)                          not null comment '创建者姓名',
    start_time         timestamp                            null comment '开始时间',
    end_time           timestamp                            null comment '结束时间',
    constraint competition_id_uindex
        unique (id)
)
    comment '比赛';

alter table competition
    add primary key (id);

