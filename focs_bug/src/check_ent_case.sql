CREATE TABLE `check_ent_case` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '��ѯ���id',
  `check_date` date DEFAULT NULL COMMENT '��ѯ����',
  `respondent` varchar(400) DEFAULT NULL COMMENT '����',
  `accuser` varchar(100) DEFAULT NULL COMMENT 'ԭ��',
  `caseNum` varchar(100) DEFAULT NULL COMMENT '����',
  `court` varchar(100) DEFAULT NULL COMMENT '��Ժ��/ִ�з�Ժ��',
  `companyName` varchar(200) DEFAULT NULL COMMENT '��˾��',
  `register_case_time` varchar(100) DEFAULT NULL COMMENT '����ʱ��',
  `open_court_time` varchar(100) DEFAULT NULL COMMENT '��ͥʱ��',
  `close_case_time` varchar(100) DEFAULT NULL COMMENT '�᰸ʱ��',
  `case_status` varchar(100) DEFAULT NULL COMMENT '��װ״̬',
  `judge_person_first` varchar(100) DEFAULT NULL COMMENT 'һ�󷨹�',
  `judge_person_second` varchar(100) DEFAULT NULL COMMENT '���󷨹�',
  `judge_person_third` varchar(100) DEFAULT NULL COMMENT '���󷨹�',
  `apply_person` varchar(400) DEFAULT NULL COMMENT '������',
  `carry_out_person` varchar(400) DEFAULT NULL COMMENT '��ִ����',
  `mesg_orign` varchar(200) DEFAULT NULL COMMENT '��Ϣ��Դ',
  `case_type` varchar(100) DEFAULT NULL COMMENT '��������',
  `isNew` varchar(100) DEFAULT NULL COMMENT '�Ƿ���������',
  `case_book` varchar(200) DEFAULT NULL COMMENT '��������·��',
  `search_param` varchar(200) DEFAULT NULL COMMENT '��ѯ����',
  `update_time` date DEFAULT NULL COMMENT '��������',
 `age` VARCHAR(40) DEFAULT NULL COMMENT '����',
 `cardNum` VARCHAR(100) DEFAULT NULL COMMENT '����֤����/��֯��������',
 `sex` VARCHAR(20) DEFAULT NULL COMMENT '�Ա�',
 `lawperson` VARCHAR(200) DEFAULT NULL COMMENT '���������˻���������',
 `areaNameDetail` VARCHAR(40) DEFAULT NULL COMMENT 'ʡ�ݵ���',
 `caseBookCode` VARCHAR(100) DEFAULT NULL COMMENT 'ִ�������ĺ�',
 `gistUnitDetail` VARCHAR(200) DEFAULT NULL COMMENT '����ִ�����ݵ�λ',
 `dutyDetail` VARCHAR(6000) DEFAULT NULL COMMENT '��Ч��������ȷ��������',
 `performanceDetail` VARCHAR(2000) DEFAULT NULL COMMENT '��ִ�������е����',
 `performedPart` VARCHAR(2000) DEFAULT NULL COMMENT '�����в���',
 `unperformPart` VARCHAR(2000) DEFAULT NULL COMMENT 'δ���в���',
 `disruptTypeNameDetail` VARCHAR(2000) DEFAULT NULL COMMENT 'ʧ�ű�ִ���˾�����Ϊ�龰',
 `publishDateDetail` VARCHAR(100) DEFAULT NULL COMMENT '���鷢��ʱ��',
  `updateTimes` int(4) default null comment '���ݸ��´���',
  PRIMARY KEY (`id`),
  UNIQUE KEY `case_book` (`case_book`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;