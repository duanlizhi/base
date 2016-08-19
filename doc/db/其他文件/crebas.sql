/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/1/28 18:54:22                           */
/*==============================================================*/


drop table if exists chapter;

drop table if exists chapter_page;

drop table if exists course;

drop table if exists course_ware;

drop table if exists root.domain;

drop table if exists domain_auth;

drop table if exists menu;

drop table if exists menu_page;

drop table if exists scores;

drop table if exists study_log;

drop table if exists study_result;

drop table if exists tag;

drop table if exists total_study_info;

/*==============================================================*/
/* User: root                                                   */
/*==============================================================*/
create user root;

/*==============================================================*/
/* Table: chapter                                               */
/*==============================================================*/
create table chapter
(
   id                   int not null,
   name                 varchar(20),
   url                  varchar(100),
   rank                 int not null,
   type                 varchar(20),
   create_date          timestamp not null,
   modify_date          timestamp not null,
   course_ware_id       int not null,
   primary key (id)
);

alter table chapter comment '章节';

/*==============================================================*/
/* Table: chapter_page                                          */
/*==============================================================*/
create table chapter_page
(
   id                   int not null,
   url                  varchar(100) not null,
   is_commit            int(1) not null,
   can_book             int(1) not null,
   can_tool             int(1) not null,
   can_failure          int(1) not null,
   can_history          int(1) not null,
   rank                 int not null,
   create_date          timestamp not null,
   modify_date          timestamp not null,
   chapter_id           int not null,
   primary key (id)
);

alter table chapter_page comment '章节page';

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
create table course
(
   id                   int not null auto_increment,
   name                 varchar(100),
   play_time            timestamp,
   plays                int,
   xml_name             varchar(100),
   enable               bigint,
   create_date          timestamp,
   modify_date          timestamp,
   tag_id               int,
   primary key (id)
);

/*==============================================================*/
/* Table: course_ware                                           */
/*==============================================================*/
create table course_ware
(
   id                   int not null,
   "schema"             varchar(20) not null,
   schemaversion        varchar(20) not null,
   name                 varchar(100) not null,
   course_number        varchar(20) not null,
   fault_number         varchar(20),
   type                 varchar(20) not null,
   purpose              varchar(100),
   "require"            varchar(100),
   keyword              varchar(100),
   description          varchar(200),
   duration             varchar(20),
   pass_score           int,
   entry                varchar(40) not null,
   client               varchar(40),
   version              verchar(20) not null,
   language             varchar(20),
   difficulty           int(1),
   kp                   varchar(100),
   category             varchar(20) not null,
   create_date          timestamp,
   modify_date          timestamp,
   primary key (id)
);

alter table course_ware comment '课件基本信息';

/*==============================================================*/
/* Table: domain                                                */
/*==============================================================*/
create table root.domain
(
   id                   bigint not null comment 'ID',
   username             varbinary(20) not null comment '账号',
   password             varbinary(20) not null comment '密码',
   name                 varbinary(50) not null comment '名称',
   description          varbinary(500) comment '描述',
   domain_number        varbinary(50) not null comment '域编号',
   url                  varbinary(100) not null comment '访问路径',
   enable               int(1) not null comment '是否可用',
   create_date          timeStamp not null comment '创建日期',
   modify_date          timestamp not null comment '修改时间',
   primary key (id)
);

alter table root.domain comment '域管理表';

/*==============================================================*/
/* Table: domain_auth                                           */
/*==============================================================*/
create table domain_auth
(
   id                   int not null,
   type                 varchar(20),
   domain_id            int,
   primary key (id)
);

alter table domain_auth comment '域权限内容';

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   id                   int not null,
   cid                  varchar(20) not null,
   name                 varchar(20),
   url                  varchar(40),
   type                 varchar(20),
   rank                 int not null,
   create_date          timestamp not null,
   modify_date          timestamp not null,
   course_ware_id       int not null,
   primary key (id)
);

alter table menu comment '菜单';

/*==============================================================*/
/* Table: menu_page                                             */
/*==============================================================*/
create table menu_page
(
   id                   int not null,
   pageid               varchar(20),
   style                varchar(40),
   name                 varchar(40),
   url                  varchar(100),
   is_commit            int(1),
   rank                 int not null,
   create_date          timestamp not null,
   modify_date          timestamp not null,
   menu_id              int not null,
   primary key (id)
);

alter table menu_page comment '菜单page';

/*==============================================================*/
/* Table: scores                                                */
/*==============================================================*/
create table scores
(
   id                   int not null,
   qid                  varchar(20) not null,
   pageid               varchar(20) not null,
   type                 varchar(20),
   rate                 varchar(20),
   kp                   varchar(100),
   rank                 int not null,
   create_date          timestamp not null,
   modify_date          timestamp not null,
   course_ware_id       int not null,
   primary key (id)
);

alter table scores comment '分数';

/*==============================================================*/
/* Table: study_log                                             */
/*==============================================================*/
create table study_log
(
   id                   int not null,
   course_number        varchar(40) not null,
   user_id              int not null,
   domain_number        varchar(40) not null,
   session_id           varchar(50) not null,
   start_time           varchar(20) not null,
   action_time          varchar(20) not null,
   receive              varchar(2000) not null,
   send                 varchar(2000) not null,
   create_date          timestamp not null,
   modify_date          timestamp not null,
   primary key (id)
);

alter table study_log comment '学习日志表';

/*==============================================================*/
/* Table: study_result                                          */
/*==============================================================*/
create table study_result
(
   id                   int not null,
   course_number        int not null,
   user_id              int not null,
   domain_number        varchar(40) not null,
   session_id           varchar(40) not null,
   start_time           varchar(20) not null,
   progress             int not null,
   score                int not null,
   seconds              int not null,
   character            varchar(4000) not null,
   is_complete          int(1) not null,
   is_pass              int(1) not null,
   domain_id            int not null,
   primary key (id)
);

alter table study_result comment '学习成绩';

/*==============================================================*/
/* Table: tag                                                   */
/*==============================================================*/
create table tag
(
   id                   int not null,
   name                 varchar(40) not null,
   icon                 varchar(100),
   tag_number           varchar(40) not null,
   grade                int(1) not null,
   create_date          timestamp not null,
   modify_date          timestamp not null,
   primary key (id)
);

alter table tag comment '标签管理表';

/*==============================================================*/
/* Table: total_study_info                                      */
/*==============================================================*/
create table total_study_info
(
   id                   int not null,
   course_number        varchar(40) not null,
   user_id              int not null,
   domain_number        varchar(40) not null,
   top_progress         int not null,
   top_score            int not null,
   seconds              int not null,
   is_complete          int(1) not null,
   is_pass              int(1) not null,
   complete_time        varchar(20) not null,
   pass_time            varchar(20) not null,
   primary key (id)
);

alter table total_study_info comment '学习总计信息';

alter table chapter add constraint FK_Reference_5 foreign key (id)
      references course_ware (id) on delete restrict on update restrict;

alter table chapter_page add constraint FK_Reference_6 foreign key (id)
      references chapter (id) on delete restrict on update restrict;

alter table domain_auth add constraint FK_fk_domain_domain_auth_id foreign key (domain_id)
      references root.domain (id) on delete restrict on update restrict;

alter table menu add constraint FK_Reference_3 foreign key (id)
      references course_ware (id) on delete restrict on update restrict;

alter table menu_page add constraint FK_Reference_4 foreign key (id)
      references menu (id) on delete restrict on update restrict;

alter table scores add constraint FK_Reference_2 foreign key (id)
      references course_ware (id) on delete restrict on update restrict;

