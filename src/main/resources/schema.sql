-- create database shiro default character set utf8;

drop table if exists sys_users;
drop table if exists sys_roles;
drop table if exists sys_permissions;
drop table if exists sys_users_roles;
drop table if exists sys_roles_permissions;

create table sys_users (
  id bigint auto_increment comment '编号',
  username varchar(100) comment '用户名',
  password varchar(100) comment '密码',
  salt varchar(100) comment '盐值',
  role_id varchar(50) comment '角色列表',
  locked bool default false comment '是否锁定',
  constraint pk_sys_users primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_users_username on sys_users(username);

create table sys_roles (
  id bigint auto_increment comment '角色编号',
  role varchar(100) comment '角色名称',
  description varchar(100) comment '角色描述',
  pid bigint comment '父节点',
  available bool default false comment '是否锁定',
  constraint pk_sys_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_roles_role on sys_roles(role);

create table sys_permissions (
  id bigint auto_increment comment '编号',
  permission varchar(100) comment '权限编号',
  description varchar(100) comment '权限描述',
  rid bigint comment '此权限关联角色的id',
  available bool default false comment '是否锁定',
  constraint pk_sys_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_permissions_permission on sys_permissions(permission);

create table sys_users_roles (
  id  bigint auto_increment comment '编号',
  user_id bigint comment '用户编号',
  role_id bigint comment '角色编号',
  constraint pk_sys_users_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;

create table sys_roles_permissions (
  id bigint auto_increment comment '编号',
  role_id bigint comment '角色编号',
  permission_id bigint comment '权限编号',
  constraint pk_sys_roles_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;



-- userinfo table
CREATE TABLE userInfo
(
    id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    uuid varchar(255) DEFAULT NULL ,
    username varchar(255) DEFAULT NULL ,
    password varchar(255) DEFAULT NULL ,
    nickname varchar(255) DEFAULT NULL ,
    sex tinyint(3) DEFAULT '0',
    phone varchar(255) DEFAULT NULL ,
    email varchar(255) DEFAULT NULL ,
    iconPath varchar(255) DEFAULT NULL ,
    university varchar(255) DEFAULT NULL ,
    academy varchar(255) DEFAULT NULL ,
    grade integer DEFAULT NULL ,
    money integer DEFAULT NULL ,
    credit integer DEFAULT NULL ,
)
CREATE UNIQUE INDEX table_name_id_uindex ON table_name (id);
CREATE UNIQUE INDEX table_name_username_uindex ON table_name (username);
CREATE UNIQUE INDEX table_name_nickname_uindex ON table_name (nickname);
CREATE UNIQUE INDEX table_name_uuid_uindex ON table_name (uuid);
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- comment table
CREATE TABLE comments
(
    id bigint(20) PRIMARY KEY AUTO_INCREMENT,
    groupid varchar(255),
    username varchar(255),
    comment_detail varchar(255),
    comment_time DATETIME
);
CREATE UNIQUE INDEX comments_id_uindex ON comments (id);

---

CREATE TABLE `user_group`
(
    id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id bigint(20),
    group_id bigint(20)
);
CREATE UNIQUE INDEX user_group_id_uindex ON `user_group` (id);
ALTER TABLE `user_group` COMMENT = '用户小组关联表'

---

CREATE TABLE `group`
(
    id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(255),
    description varchar(255)
);
CREATE UNIQUE INDEX group_id_uindex ON `group` (id);
ALTER TABLE `group` COMMENT = '小组';



--- user and task

CREATE TABLE user_task
(
    id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id bigint(20),
    task_id bigint(20)
);
CREATE UNIQUE INDEX user_task_id_uindex ON user_task (id);
ALTER TABLE user_task COMMENT = '用户-任务关联';


--- task

CREATE TABLE task
(
    id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    taskname varchar(255),
    starttime datetime,
    endtime datetime,
    state integer DEFAULT -1,
    type integer DEFAULT -1,
    release_user bigint(20),
    accept_num_limit integer DEFAULT -1,
    has_target_limit tinyint(1) DEFAULT 0,
    description varchar(255),
    sex tinyint(3),
    grade integer,
    creditMin integer,
    group_id bigint(20)
);
CREATE UNIQUE INDEX task_id_uindex ON task (id);
ALTER TABLE task COMMENT = '任务';

---

-- CREATE TABLE task_limit
-- (
--     id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     task_id bigint(20),
--     limit_id bigint(20)
-- );
-- CREATE UNIQUE INDEX task_limit_id_uindex ON task_limit (id);
-- ALTER TABLE task_limit COMMENT = '任务-限制关联';

---

-- CREATE TABLE target_limit
-- (
--     id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     sex tinyint(3),
--     grade integer,
--     creditMin integer,
--     group_id bigint(20)
-- );
-- CREATE UNIQUE INDEX target_limit_id_uindex ON target_limit (id);
-- ALTER TABLE target_limit COMMENT = '限制信息';



