# 简单的考勤管理系统，实现了考勤管理，管理员审批，成员信息管理功能

------
##**项目环境**
 - 系统：`Windows7 X64位系统`
 - IDE：`Intellij IDEA 14.0`
###**后端技术选型**

 - J D K 版 本：`JDK 1.8`
 - 数  据  库:`Mysql 5.7`
 - WEB容器：`Tomcat 7.0`
 - 视图框架:`SpringMVC 4.0`
 - 核心框架:`Spring Framework 4.0`
 - 持久层框架:`Hibernate4.0`
 - 数据库连接池:`C3P0`

###**工具类**

 - `Apache fileupload 文件上传组件`
 - `Apache commons-collections 封装好的各种集合类和集合工具类`
 - `Apache commons-io   Apache基金会创建并维护的Java函数库`
 - `Apache commons-logging 通用的日志接口`
 - `dom4j 优秀的JavaXML API  主要用于解析XML文档`
 - `poi组件 主要用于读取以及写入Microsoft Office格式档案 `
 - `JSR 303 为实体验证定义了一个元数据模型和API`
###**前端技术选型**
 - JS框架：`jQuery 1.8`
 - CSS框架：`Twitter Bootstrap`

<hr>
###**项目所需jar包列表**

 - `antlr-2.7.7.jar`
 - `classmate-1.0.0.jar`
 - `com.springsource.net.sf.cglib-2.2.0.jar  `
 - `com.springsource.org.aopalliance-1.0.0.jar  `
 - `com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar  `
 - `commons-collections4-4.0.jar  `
 - `commons-fileupload-1.2.2.jar `
 - `commons-io-2.0.1.jar `
 - `commons-logging-1.1.3.jar `
 - `dom4j-1.6.1.jar `
 - `hibernate-commons-annotations-4.0.5.Final.jar  `
 - `hibernate-core-4.3.11.Final.jar  `
 - `hibernate-entitymanager-4.3.11.Final.jar  `
 - `hibernate-jpa-2.1-api-1.0.0.Final.jar  `
 - `hibernate-validator-5.1.3.Final.jar  `
 - `hibernate-validator-annotation-processor-5.1.3.Final.jar  `
 - `jandex-1.1.0.Final.jar  javassist-3.18.1-GA.jar  `
 - `jboss-logging-3.1.3.GA.jar  `
 - `jboss-logging-annotations-1.2.0.Beta1.jar  `
 - `jboss-transaction-api_1.2_spec-1.0.0.Final.jar `
 - `jstl-1.2.jar `
 - `mysql-connector-java-5.1.7-bin.jar `
 - `poi-3.15.jar `
 - `poi-examples-3.15.jar `
 - `poi-excelant-3.15.jar `
 - `poi-ooxml-3.15.jar `
 - `poi-ooxml-schemas-3.15.jar `
 - `poi-scratchpad-3.15.jar `
 - `spring-aop-4.0.0.RELEASE.jar  使用Spring 的AOP 特性时所需的类和源码级元数据支持`
 - `spring-aspects-4.0.0.RELEASE.jar  提供对AspectJ的支持，以便可以方便的将面向方面的功能集成进IDE中`
 - `spring-beans-4.0.0.RELEASE.jar  所有应用都要用到的，它包含访问配置文件、创建和管理bean`
 - `spring-context-4.0.0.RELEASE.jar  Spring 核心提供了大量扩展 `
 - `spring-core-4.0.0.RELEASE.jar  Spring 框架基本的核心工具类，是其它组件的基本核心`
 - `spring-expression-4.0.0.RELEASE.jar Spring表达式语言`
 - `spring-jdbc-4.0.0.RELEASE.jar 对Spring 对JDBC 数据访问进行封装的所有类`
 - `spring-orm-4.0.0.RELEASE.jar  Spring对DAO特性集进行了扩展`
 - `spring-tx-4.0.0.RELEASE.jar 事务相关的类`
 - `spring-web-4.0.0.RELEASE.jar Web 应用开发时，用到Spring 框架时所需的核心类`
 - `spring-webmvc-4.0.0.RELEASE.jar 包含Spring MVC 框架相关的所有类`
 - `validation-api-1.1.0.Final.jar `
 - `xmlbeans-2.6.0.jar`
###**项目数据库**

###**项目数据库**

总共分为5张数据库表
----------

 - **application**表   (*申请修改考勤表*)

| Field | Type  | Comment  |
| ------| -------|---------|     |
| app_id  | int(11)   | 主键标示id   |
| app_reason | varchar(255) |  申请修改考勤类型的理由  |
| app_result | tinyint(1)  | 该申请是否被处理  |
| attend_id | int(11)  | 关联的考勤记录    |
| type_id | int(11)   |  希望更改为什么考勤类型  |
***外键关联：***
|Indexes |Columns | Index_Type|Comment |
|-------|------|------|-----|
|PRIMARY|app_id|Unique| 关联CheckBack表中的app_id字段 |
|FK_9wx0gm9e04sxa4spsnnvshr2v|attend_id|| 关联Attend表中的attend_id字段|
|FK_ncy74ck30mee5rdkk2uhl76r4 |type_id||关联Attend_Type表中的id字段|

***数据库语句：***

create table
CREATE TABLE `application` (
   `app_id` int(11) NOT NULL AUTO_INCREMENT,
   `app_reason` varchar(255) DEFAULT NULL,
   `app_result` tinyint(1) DEFAULT '0',
   `attend_id` int(11) NOT NULL,
   `type_id` int(11) NOT NULL,
   PRIMARY KEY (`app_id`),
   KEY `FK_9wx0gm9e04sxa4spsnnvshr2v` (`attend_id`),
   KEY `FK_ncy74ck30mee5rdkk2uhl76r4` (`type_id`),
   CONSTRAINT `FK_9wx0gm9e04sxa4spsnnvshr2v` FOREIGN KEY (`attend_id`) REFERENCES `attend` (`attend_id`),
   CONSTRAINT `FK_ncy74ck30mee5rdkk2uhl76r4` FOREIGN KEY (`type_id`) REFERENCES `attend_type` (`type_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8


----------

 - **attend**表     (*考勤记录表*)

  | Field | Type  | Comment  |
| ------| -------|---------|     |
| attend_id| int(11)| 主键标示id    |
| duty_day| varchar(50)| 考勤日期|
| is_come| tinyint(1)| 是否签到|
| punch_time| datetime| 签到时间|
| type_id| int(11)| 考勤类型|
| emp_id| int(11)| 考勤对应的成员id|
***外键关联：***

| Indexes| Columns| Index_Type| Comment|
| --- | --- | --- | ---|
| PRIMARY| attend_id| Unique|  Application表中attednd_id |
| FK_sxsglsafwbl3kjyu20pulbv7| type_id| |Attend_Type表id |
| FK_e5w782m7lqroclf83divt40iv| emp_id|   |对应的员工id |



*数据库语句：*
CREATE TABLE `attend` (
   `attend_id` int(11) NOT NULL AUTO_INCREMENT,
   `duty_day` varchar(50) NOT NULL,
   `is_come` tinyint(1) DEFAULT NULL,
   `punch_time` datetime DEFAULT NULL,
   `type_id` int(11) NOT NULL,
   `emp_id` int(11) DEFAULT NULL,
   PRIMARY KEY (`attend_id`),
   KEY `FK_sxsglsafwbl3kjyu20pulbv7` (`type_id`),
   KEY `FK_e5w782m7lqroclf83divt40iv` (`emp_id`),
   CONSTRAINT `FK_e5w782m7lqroclf83divt40iv` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`),
   CONSTRAINT `FK_sxsglsafwbl3kjyu20pulbv7` FOREIGN KEY (`type_id`) REFERENCES `attend_type` (`type_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=378 DEFAULT CHARSET=utf8

----------

 - **attend_type表**    (*考勤类型表*)
 -
| Field | Type  | Comment  |
| ------| -------|  -----   |
 | type_id | int(11) |  主键标示id   |
 | type_name | varchar(50)    | 出勤类型名称  |

外键关联：
|Indexes |Columns | Index_Type|Comment |
 | ----| ----| ---| ----  |
|PRIMARY|type_id|Unique| 主键标示id  |

***数据库语句：***


create table
CREATE TABLE `attend_type` (
   `type_id` int(11) NOT NULL AUTO_INCREMENT,
   `type_name` varchar(50) NOT NULL,
   PRIMARY KEY (`type_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8


----------
 - **check_back表**    (*申请修改考勤类型结果表*)
|    Field|    Type|    Comment|
|    --|    ---|    --|    --|
|    check_id|    int(11)  |  主键标示id    |
|    check_reason|    varchar(255)| 审批的理由   |
|    check_result|    tinyint(1)|  审批的结果  |
|    app_id|    int(11)|   对应的申请表中的id    |
    |    mgr_id|    int(11)|   哪个管理员审批的，对应管理员id    |

***外键关联：***
|    Indexes    |    Columns    |    Index_Type    | Commment|
|    ---    |    ---    | ---       | ---    |
| PRIMARY |    check_id    |        |    主键标示id    |
|    FK_939cwdph0r4ia2okhjqn7f92y|app_id||对应applicaion表中的id |
|    FK_cfkusgfp28so2n7lyvat4byju |    mgr_id    |     |对应employee表中的id  |

***数据库语句：***

CREATE TABLE `check_back` (
   `check_id` int(11) NOT NULL AUTO_INCREMENT,
   `check_reason` varchar(255) DEFAULT NULL,
   `check_result` tinyint(1) NOT NULL,
   `app_id` int(11) DEFAULT NULL,
   `mgr_id` int(11) NOT NULL,
   PRIMARY KEY (`check_id`),
   KEY `FK_939cwdph0r4ia2okhjqn7f92y` (`app_id`),
   KEY `FK_cfkusgfp28so2n7lyvat4byju` (`mgr_id`),
   CONSTRAINT `FK_939cwdph0r4ia2okhjqn7f92y` FOREIGN KEY (`app_id`) REFERENCES `application` (`app_id`),
   CONSTRAINT `FK_cfkusgfp28so2n7lyvat4byju` FOREIGN KEY (`mgr_id`) REFERENCES `employee` (`emp_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8



----------


 - **employee表**  (成员信息表)
|    Field|    Type|    Comment|
|    --|    --|    --|
|    emp_id|    int(11)|    主键标示id|
|    emp_account|    varchar(50)|  登陆的账号 |
|    emp_password|    varchar(50)|  登陆的密码  |
|    emp_name|    varchar(255)| 姓名    |
|    emp_className|    varchar(255)|    班级名字|
|    emp_tel|    varchar(255)|  电话号码  |
|    emp_bedroom|    varchar(255)|  房间号  |
|    dept_name|    varchar(50)|  部门名字  |
|    mgr_id|    int(11)|  该成员管理人员的id    |
|    emp_type|    int(11) |  角色  |

***外键关联：***

|     Indexes|     Columns|     Index_Type  | Comment |
|     --|     --|     --|  --  |
|     PRIMARY|     emp_id |     Unique|  主键标示id |
|     UK_5tltp1u3mevdum1ilp1ft71rg|     emp_account|Unique|对应的账号|
|     FK_5f44y8jo1j00uhenw2naboii8|     mgr_id| 对应的管理人员id    |


***数据库语句：***
CREATE TABLE `employee` (
   `emp_id` int(11) NOT NULL AUTO_INCREMENT,
   `emp_account` varchar(50) NOT NULL,
   `emp_password` varchar(50) NOT NULL DEFAULT '123456',
   `emp_name` varchar(255) DEFAULT NULL,
   `emp_className` varchar(255) DEFAULT NULL,
   `emp_tel` varchar(255) DEFAULT NULL,
   `emp_bedroom` varchar(255) DEFAULT NULL,
   `dept_name` varchar(50) DEFAULT NULL,
   `mgr_id` int(11) DEFAULT NULL,
   `emp_type` int(11) NOT NULL DEFAULT '1',
   PRIMARY KEY (`emp_id`),
   UNIQUE KEY `UK_5tltp1u3mevdum1ilp1ft71rg` (`emp_account`),
   KEY `FK_5f44y8jo1j00uhenw2naboii8` (`mgr_id`),
   CONSTRAINT `FK_5f44y8jo1j00uhenw2naboii8` FOREIGN KEY (`mgr_id`) REFERENCES `employee` (`emp_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8
