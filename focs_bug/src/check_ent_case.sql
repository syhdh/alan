CREATE TABLE `check_ent_case` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '查询结果id',
  `check_date` date DEFAULT NULL COMMENT '查询日期',
  `respondent` varchar(400) DEFAULT NULL COMMENT '被告',
  `accuser` varchar(100) DEFAULT NULL COMMENT '原告',
  `caseNum` varchar(100) DEFAULT NULL COMMENT '案号',
  `court` varchar(100) DEFAULT NULL COMMENT '法院名/执行法院名',
  `companyName` varchar(200) DEFAULT NULL COMMENT '公司名',
  `register_case_time` varchar(100) DEFAULT NULL COMMENT '立案时间',
  `open_court_time` varchar(100) DEFAULT NULL COMMENT '开庭时间',
  `close_case_time` varchar(100) DEFAULT NULL COMMENT '结案时间',
  `case_status` varchar(100) DEFAULT NULL COMMENT '安装状态',
  `judge_person_first` varchar(100) DEFAULT NULL COMMENT '一审法官',
  `judge_person_second` varchar(100) DEFAULT NULL COMMENT '二审法官',
  `judge_person_third` varchar(100) DEFAULT NULL COMMENT '三审法官',
  `apply_person` varchar(400) DEFAULT NULL COMMENT '申请人',
  `carry_out_person` varchar(400) DEFAULT NULL COMMENT '被执行人',
  `mesg_orign` varchar(200) DEFAULT NULL COMMENT '信息来源',
  `case_type` varchar(100) DEFAULT NULL COMMENT '案件类型',
  `isNew` varchar(100) DEFAULT NULL COMMENT '是否最新数据',
  `case_book` varchar(200) DEFAULT NULL COMMENT '裁判文书路径',
  `search_param` varchar(200) DEFAULT NULL COMMENT '查询对象',
  `update_time` date DEFAULT NULL COMMENT '更新日期',
 `age` VARCHAR(40) DEFAULT NULL COMMENT '年龄',
 `cardNum` VARCHAR(100) DEFAULT NULL COMMENT '身份证号码/组织机构代码',
 `sex` VARCHAR(20) DEFAULT NULL COMMENT '性别',
 `lawperson` VARCHAR(200) DEFAULT NULL COMMENT '法定代表人或负责人姓名',
 `areaNameDetail` VARCHAR(40) DEFAULT NULL COMMENT '省份地区',
 `caseBookCode` VARCHAR(100) DEFAULT NULL COMMENT '执行依据文号',
 `gistUnitDetail` VARCHAR(200) DEFAULT NULL COMMENT '做出执行依据单位',
 `dutyDetail` VARCHAR(6000) DEFAULT NULL COMMENT '生效法律文书确定的义务',
 `performanceDetail` VARCHAR(2000) DEFAULT NULL COMMENT '被执行人履行的情况',
 `performedPart` VARCHAR(2000) DEFAULT NULL COMMENT '已履行部分',
 `unperformPart` VARCHAR(2000) DEFAULT NULL COMMENT '未履行部分',
 `disruptTypeNameDetail` VARCHAR(2000) DEFAULT NULL COMMENT '失信被执行人具体行为情景',
 `publishDateDetail` VARCHAR(100) DEFAULT NULL COMMENT '详情发布时间',
  `updateTimes` int(4) default null comment '数据更新次数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `case_book` (`case_book`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;