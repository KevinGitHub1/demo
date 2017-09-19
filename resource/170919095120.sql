/*
PGSQL Backup
Source Server Version: 9.4.10
Source Database: test
Date: 2017-09-19 09:51:20
*/


-- ----------------------------
--  Table structure for "public"."t_project_info"
-- ----------------------------
DROP TABLE "public"."t_project_info";
CREATE TABLE "public"."t_project_info" (
"id" varchar(50) COLLATE "default",
"name" varchar(100) COLLATE "default",
"abstract" varchar(500) COLLATE "default",
"type" varchar(20) COLLATE "default",
"stage" varchar(20) COLLATE "default",
"manager" varchar(20) COLLATE "default",
"members" varchar(1000) COLLATE "default",
"plan_start_data" date,
"plan_end_data" date,
"start_data" date,
"end_data" date,
"create_persion" varchar(10) COLLATE "default",
"create_tiem" time(6)
)
WITH (OIDS=FALSE)
;

COMMENT ON TABLE "public"."t_project_info" IS '项目信息';

COMMENT ON COLUMN "public"."t_project_info"."name" IS '项目名称';

COMMENT ON COLUMN "public"."t_project_info"."abstract" IS '项目摘要';

COMMENT ON COLUMN "public"."t_project_info"."type" IS '项目类型';

COMMENT ON COLUMN "public"."t_project_info"."stage" IS '项目阶段';

COMMENT ON COLUMN "public"."t_project_info"."manager" IS '项目经理';

COMMENT ON COLUMN "public"."t_project_info"."members" IS '项目成员？考虑各个阶段的变化，放入不同阶段管理';

COMMENT ON COLUMN "public"."t_project_info"."plan_start_data" IS '计划开始日期';

COMMENT ON COLUMN "public"."t_project_info"."plan_end_data" IS '计划结束日期';

COMMENT ON COLUMN "public"."t_project_info"."start_data" IS '实际开始日期';

COMMENT ON COLUMN "public"."t_project_info"."end_data" IS '实际结束日期';

COMMENT ON COLUMN "public"."t_project_info"."create_persion" IS '创建人';

COMMENT ON COLUMN "public"."t_project_info"."create_tiem" IS '创建时间';;

-- ----------------------------
--  Table structure for "public"."t_project_persion"
-- ----------------------------
DROP TABLE "public"."t_project_persion";
CREATE TABLE "public"."t_project_persion" (
"id" varchar(50) COLLATE "default",
"name" varchar(50) COLLATE "default",
"project_id" varchar(50) COLLATE "default",
"type" varchar(2) COLLATE "default",
"operator" varchar(50) COLLATE "default",
"operat_time" date,
"reason" varchar(500) COLLATE "default",
"role" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)
;

COMMENT ON TABLE "public"."t_project_persion" IS '项目人员管理，记录项目过程中人员更迭变更情况';

COMMENT ON COLUMN "public"."t_project_persion"."name" IS '人员姓名';

COMMENT ON COLUMN "public"."t_project_persion"."project_id" IS '项目ID';

COMMENT ON COLUMN "public"."t_project_persion"."type" IS '1，加入；2，退出';

COMMENT ON COLUMN "public"."t_project_persion"."operator" IS '操作人';

COMMENT ON COLUMN "public"."t_project_persion"."operat_time" IS '操作时间';

COMMENT ON COLUMN "public"."t_project_persion"."reason" IS '原因';

COMMENT ON COLUMN "public"."t_project_persion"."role" IS '项目组成员角色';;

-- ----------------------------
--  Table structure for "public"."t_project_product"
-- ----------------------------
DROP TABLE "public"."t_project_product";
CREATE TABLE "public"."t_project_product" (
"id" varchar(50) COLLATE "default",
"project_id" varchar(50) COLLATE "default",
"stage" varchar(10) COLLATE "default",
"product_name" varchar(100) COLLATE "default",
"adress" varchar(200) COLLATE "default",
"product_description" varchar(500) COLLATE "default",
"operate_persion" varchar(50) COLLATE "default",
"operate_time" date
)
WITH (OIDS=FALSE)
;

COMMENT ON TABLE "public"."t_project_product" IS '项目各阶段输出产物管理';

COMMENT ON COLUMN "public"."t_project_product"."project_id" IS '项目ID';

COMMENT ON COLUMN "public"."t_project_product"."stage" IS '项目阶段';

COMMENT ON COLUMN "public"."t_project_product"."product_name" IS '输出产品名称';

COMMENT ON COLUMN "public"."t_project_product"."adress" IS '存放地址';

COMMENT ON COLUMN "public"."t_project_product"."product_description" IS '产品简单说明';

COMMENT ON COLUMN "public"."t_project_product"."operate_persion" IS '上传人';

COMMENT ON COLUMN "public"."t_project_product"."operate_time" IS '上传时间';;

-- ----------------------------
--  Table structure for "public"."t_sys_dictdata"
-- ----------------------------
DROP TABLE "public"."t_sys_dictdata";
CREATE TABLE "public"."t_sys_dictdata" (
"id" varchar(50) COLLATE "default" NOT NULL,
"dict_value" varchar(50) COLLATE "default" NOT NULL,
"dictdata_name" varchar(50) COLLATE "default" NOT NULL,
"dictdata_code" varchar(50) COLLATE "default" NOT NULL,
"status" varchar(5) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)
;;

-- ----------------------------
--  Table structure for "public"."t_sys_dictionary"
-- ----------------------------
DROP TABLE "public"."t_sys_dictionary";
CREATE TABLE "public"."t_sys_dictionary" (
"name" varchar(50) COLLATE "default" NOT NULL,
"code" varchar(50) COLLATE "default" NOT NULL,
"status" varchar(5) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)
;;

-- ----------------------------
--  Table structure for "public"."t_sys_login_log"
-- ----------------------------
DROP TABLE "public"."t_sys_login_log";
CREATE TABLE "public"."t_sys_login_log" (
"id" varchar(50) COLLATE "default" NOT NULL,
"login_name" varchar(50) COLLATE "default" NOT NULL,
"login_time" varchar(50) COLLATE "default" NOT NULL,
"login_ip" varchar(50) COLLATE "default",
"login_cilent" varchar(50) COLLATE "default",
"exit_time" date
)
WITH (OIDS=FALSE)
;;

-- ----------------------------
--  Table structure for "public"."t_sys_module"
-- ----------------------------
DROP TABLE "public"."t_sys_module";
CREATE TABLE "public"."t_sys_module" (
"id" varchar(50) COLLATE "default" NOT NULL,
"name" varchar(50) COLLATE "default" NOT NULL,
"code" varchar(50) COLLATE "default" NOT NULL,
"parent_id" varchar(50) COLLATE "default",
"order_number" varchar(50) COLLATE "default",
"operate_persion" varchar(50) COLLATE "default",
"operate_time" varchar(50) COLLATE "default",
"status" varchar(5) COLLATE "default"
)
WITH (OIDS=FALSE)
;

COMMENT ON COLUMN "public"."t_sys_module"."id" IS '主键ID';

COMMENT ON COLUMN "public"."t_sys_module"."name" IS '模块名称';

COMMENT ON COLUMN "public"."t_sys_module"."code" IS '模块编码';

COMMENT ON COLUMN "public"."t_sys_module"."parent_id" IS '父ID';

COMMENT ON COLUMN "public"."t_sys_module"."order_number" IS '显示序号';

COMMENT ON COLUMN "public"."t_sys_module"."operate_persion" IS '操作人';

COMMENT ON COLUMN "public"."t_sys_module"."operate_time" IS '操作时间';

COMMENT ON COLUMN "public"."t_sys_module"."status" IS '状态0，停用；1，启用';;

-- ----------------------------
--  Table structure for "public"."t_sys_operation_log"
-- ----------------------------
DROP TABLE "public"."t_sys_operation_log";
CREATE TABLE "public"."t_sys_operation_log" (
"id" varchar(50) COLLATE "default" NOT NULL,
"user_name" varchar(50) COLLATE "default" NOT NULL,
"operation_time" varchar(50) COLLATE "default" NOT NULL,
"operation_context" varchar(50) COLLATE "default",
"operation_client" varchar(50) COLLATE "default",
"operation_ip" varchar(50) COLLATE "default",
"operation_type" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)
;;

-- ----------------------------
--  Table structure for "public"."t_sys_organization"
-- ----------------------------
DROP TABLE "public"."t_sys_organization";
CREATE TABLE "public"."t_sys_organization" (
"id" varchar(50) COLLATE "default",
"name" varchar(50) COLLATE "default",
"type" varchar(10) COLLATE "default",
"parentid" varchar(50) COLLATE "default",
"deptno" varchar(10) COLLATE "default",
"create_persion" varchar(255) COLLATE "default",
"create_date" date,
"edite_persion" varchar(255) COLLATE "default",
"edite_date" date
)
WITH (OIDS=FALSE)
;

COMMENT ON COLUMN "public"."t_sys_organization"."type" IS '1单位；2部门或组';

COMMENT ON COLUMN "public"."t_sys_organization"."deptno" IS '部门编号';;

-- ----------------------------
--  Table structure for "public"."t_sys_role"
-- ----------------------------
DROP TABLE "public"."t_sys_role";
CREATE TABLE "public"."t_sys_role" (
"id" varchar(50) COLLATE "default" NOT NULL,
"name" varchar(50) COLLATE "default" NOT NULL,
"create_time" date,
"create_persion" varchar(50) COLLATE "default",
"edite_time" date,
"edite_persion" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)
;

COMMENT ON COLUMN "public"."t_sys_role"."name" IS '角色名称';

COMMENT ON COLUMN "public"."t_sys_role"."create_time" IS '创建日期';

COMMENT ON COLUMN "public"."t_sys_role"."create_persion" IS '创建人';

COMMENT ON COLUMN "public"."t_sys_role"."edite_time" IS '修改日期';

COMMENT ON COLUMN "public"."t_sys_role"."edite_persion" IS '修改人';;

-- ----------------------------
--  Table structure for "public"."t_sys_role_module_rel"
-- ----------------------------
DROP TABLE "public"."t_sys_role_module_rel";
CREATE TABLE "public"."t_sys_role_module_rel" (
"id" varchar(50) COLLATE "default" NOT NULL,
"role_id" varchar(50) COLLATE "default" NOT NULL,
"module_id" varchar(50) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)
;;

-- ----------------------------
--  Table structure for "public"."t_sys_user"
-- ----------------------------
DROP TABLE "public"."t_sys_user";
CREATE TABLE "public"."t_sys_user" (
"id" varchar(50) COLLATE "default" NOT NULL,
"login_name" varchar(50) COLLATE "default" NOT NULL,
"password" varchar(50) COLLATE "default" NOT NULL,
"name" varchar(50) COLLATE "default",
"status" varchar(5) COLLATE "default" NOT NULL,
"login_adress" varchar(50) COLLATE "default",
"login_time" date,
"icon" varchar(200) COLLATE "default",
"mobile_number" varchar(50) COLLATE "default",
"email_adress" varchar(50) COLLATE "default",
"create_time" date,
"birthday" date,
"province" varchar(5) COLLATE "default",
"city" varchar(5) COLLATE "default",
"county" varchar(5) COLLATE "default",
"adress" varchar(200) COLLATE "default",
"motto" varchar(200) COLLATE "default",
"hobby" varchar(200) COLLATE "default"
)
WITH (OIDS=FALSE)
;;

-- ----------------------------
--  Table structure for "public"."test"
-- ----------------------------
DROP TABLE "public"."test";
CREATE TABLE "public"."test" (
"ID" varchar(40) COLLATE "default" DEFAULT ''::character varying NOT NULL,
"name" varchar(255) COLLATE "default",
"value" jsonb
)
WITH (OIDS=FALSE)
;

COMMENT ON COLUMN "public"."test"."ID" IS 'EE';;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO "public"."t_sys_organization" VALUES ('062C500297B8496C8B6062F26F6E3502','测试1部','2','6C0D2354A7E64ED0A781D214098AF9F8','21',NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('1','单位1','1','0','01',NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('2','单位2233','1','0','02',NULL,NULL,'admin','2017-08-09'); INSERT INTO "public"."t_sys_organization" VALUES ('3','单位3','1','0','03',NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('3D9C1E6ED3B94414A8C44B9E6004EEA2','测试2部','2','6C0D2354A7E64ED0A781D214098AF9F8','22','admin','2017-08-09',NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('4','部门1','2','1','0101',NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('5','部门2','2','1','0102',NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('5447B8521FC8469E8214002A9C1AD8BE','测试一组','2','062C500297B8496C8B6062F26F6E3502','222','admin','2017-08-09',NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('6','部门3','2','1','0103',NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('6C0D2354A7E64ED0A781D214098AF9F8','ceshi ','1','0','11','admin','2017-08-09',NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('7','小组1','2','4','010101',NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('8','小组2','2','4','010102',NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('B768B130008C4AFCB22E74939BC45CB5','单位11','1','0','11','admin','2017-08-09',NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('BB7FCDAF48B84C27A2C926A6AB28A9EA','我好','2','2','123','admin_zmk','2017-08-10','admin_zmk','2017-08-10'); INSERT INTO "public"."t_sys_organization" VALUES ('C8B10202F83D41DA9C084CC7BECF199D','测试3组','2','062C500297B8496C8B6062F26F6E3502','224','admin','2017-08-09',NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('D03CA6825BB44D5A9560A142399EEC86','部门11','2','B768B130008C4AFCB22E74939BC45CB5','11','admin','2017-08-09',NULL,NULL); INSERT INTO "public"."t_sys_organization" VALUES ('FF6F79AF9C284F33AD4C5DA9F6A0661F','测试2组','2','062C500297B8496C8B6062F26F6E3502','223','admin','2017-08-09',NULL,NULL);
INSERT INTO "public"."t_sys_role" VALUES ('050FCEAA84CA420AAFFBA8714111A5D9','rtgrgrfg','2017-08-08','log',NULL,NULL); INSERT INTO "public"."t_sys_role" VALUES ('2546C8BA92E24FBAA908570041E6783C','gdrrgr','2017-08-08','log',NULL,NULL); INSERT INTO "public"."t_sys_role" VALUES ('3B54C07E2B7E48B185689F30C22E9730','如同仁堂','2017-08-07','log',NULL,NULL); INSERT INTO "public"."t_sys_role" VALUES ('6C3CDD70309247C191935E77FA658256','dgdrgdrg','2017-08-08','log',NULL,NULL); INSERT INTO "public"."t_sys_role" VALUES ('7D7EF216F0834FA4AAB8B0DF6820194E','fththth','2017-08-08','log',NULL,NULL); INSERT INTO "public"."t_sys_role" VALUES ('D17142809AA84838A5794E13F61907E2','drgdrgrd','2017-08-08','log',NULL,NULL);
INSERT INTO "public"."t_sys_user" VALUES ('19F20E06F89A4BE5A33E0B3D890F9753','qweqwe','E10ADC3949BA59ABBE56E057F20F883E',NULL,'1',NULL,NULL,NULL,NULL,'123@163.com','2017-08-08',NULL,NULL,NULL,NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_user" VALUES ('1A242458A628496BA57BCC123420C5AC','ssssdds','E10ADC3949BA59ABBE56E057F20F883E',NULL,'1',NULL,NULL,NULL,NULL,'1233@123.com','2017-08-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_user" VALUES ('437B6748759B403691EEE6371BA7ED6B','ssssss','E10ADC3949BA59ABBE56E057F20F883E',NULL,'1',NULL,NULL,NULL,NULL,'123@163.com','2017-08-09',NULL,NULL,NULL,NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_user" VALUES ('584B0F3279824A4AB57146E09D4F6D04','qqqqqwww','E10ADC3949BA59ABBE56E057F20F883E',NULL,'1','null','2017-08-14',NULL,NULL,'123@163.com','2017-08-08',NULL,NULL,NULL,NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_user" VALUES ('66D11E29E5A745FAABD3B9ED845DE8BD','ww1111111111111111','E10ADC3949BA59ABBE56E057F20F883E',NULL,'1',NULL,NULL,NULL,NULL,'123@163.com','2017-08-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_user" VALUES ('81B241CB96CE4D23A693D26F24D93A5C','q2e22321','E10ADC3949BA59ABBE56E057F20F883E',NULL,'1',NULL,NULL,NULL,NULL,'123@163.com','2017-08-08',NULL,NULL,NULL,NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_user" VALUES ('97FD326B42C64D0EA558A5A899B2A13F','testeee','E10ADC3949BA59ABBE56E057F20F883E',NULL,'0',NULL,NULL,NULL,NULL,'ashdg@123.com','2017-08-08',NULL,NULL,NULL,NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_user" VALUES ('E7637F3194924A12A0DFE7EB1D0C6E5C','tewrtert','E10ADC3949BA59ABBE56E057F20F883E',NULL,'1',NULL,NULL,NULL,NULL,'123@163.com','2017-08-08',NULL,NULL,NULL,NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_user" VALUES ('ECA543E6F43544A4BB94E6C05F511787','qqqqqwww','E10ADC3949BA59ABBE56E057F20F883E',NULL,'1','null','2017-08-14',NULL,NULL,'123@163.com','2017-08-09',NULL,NULL,NULL,NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_user" VALUES ('FC7C4FE7AD5C4AAA9FF7C5E0EB0C85F1','dddddee','E10ADC3949BA59ABBE56E057F20F883E',NULL,'1',NULL,NULL,NULL,NULL,'123@163.com','2017-08-09',NULL,NULL,NULL,NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_user" VALUES ('FFC6DEAB708C42919D5215C92B219B48','eeeewwww','E10ADC3949BA59ABBE56E057F20F883E',NULL,'1',NULL,NULL,NULL,NULL,'123@163.com','2017-08-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL); INSERT INTO "public"."t_sys_user" VALUES ('a4abf9b9a67647b3aa347de6e36cf1fb','admin_zmk','E10ADC3949BA59ABBE56E057F20F883E','张明凯','1','null','2017-08-18',NULL,'17792606001','q22@11.com','2017-07-03','2010-07-03','河南省','许昌市','长葛市','秦庄村二组','我爱你eeee','篮球、足球');
INSERT INTO "public"."test" VALUES ('aa','aa','{"a": "", "b": "b"}');
