## 之类创建的是数据库名字,定义在applicationContext.xml 中jdbc地址中,可以自行更改
CREATE DATABASE employeeManager;

CREATE TABLE application (
  app_id     INT(11) NOT NULL AUTO_INCREMENT,
  app_reason VARCHAR(255)     DEFAULT NULL,
  app_result TINYINT(1)       DEFAULT '0',
  attend_id  INT(11) NOT NULL,
  type_id    INT(11) NOT NULL,
  PRIMARY KEY (app_id),
  KEY FK_9wx0gm9e04sxa4spsnnvshr2v (attend_id),
  KEY FK_ncy74ck30mee5rdkk2uhl76r4 (type_id),
  CONSTRAINT FK_9wx0gm9e04sxa4spsnnvshr2v FOREIGN KEY (attend_id) REFERENCES attend (attend_id),
  CONSTRAINT FK_ncy74ck30mee5rdkk2uhl76r4 FOREIGN KEY (type_id) REFERENCES attend_type (type_id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8


CREATE TABLE attend (
  attend_id  INT(11)     NOT NULL AUTO_INCREMENT,
  duty_day   VARCHAR(50) NOT NULL,
  is_come    TINYINT(1)           DEFAULT NULL,
  punch_time DATETIME             DEFAULT NULL,
  type_id    INT(11)     NOT NULL,
  emp_id     INT(11)              DEFAULT NULL,
  PRIMARY KEY (attend_id),
  KEY FK_sxsglsafwbl3kjyu20pulbv7 (type_id),
  KEY FK_e5w782m7lqroclf83divt40iv (emp_id),
  CONSTRAINT FK_e5w782m7lqroclf83divt40iv FOREIGN KEY (emp_id) REFERENCES employee (emp_id),
  CONSTRAINT FK_sxsglsafwbl3kjyu20pulbv7 FOREIGN KEY (type_id) REFERENCES attend_type (type_id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 378
  DEFAULT CHARSET = utf8


CREATE TABLE attend_type (
  type_id   INT(11)     NOT NULL AUTO_INCREMENT,
  type_name VARCHAR(50) NOT NULL,
  PRIMARY KEY (type_id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8


CREATE TABLE check_back (
  check_id     INT(11)    NOT NULL AUTO_INCREMENT,
  check_reason VARCHAR(255)        DEFAULT NULL,
  check_result TINYINT(1) NOT NULL,
  app_id       INT(11)             DEFAULT NULL,
  mgr_id       INT(11)    NOT NULL,
  PRIMARY KEY (check_id),
  KEY FK_939cwdph0r4ia2okhjqn7f92y (app_id),
  KEY FK_cfkusgfp28so2n7lyvat4byju (mgr_id),
  CONSTRAINT FK_939cwdph0r4ia2okhjqn7f92y FOREIGN KEY (app_id) REFERENCES application (app_id),
  CONSTRAINT FK_cfkusgfp28so2n7lyvat4byju FOREIGN KEY (mgr_id) REFERENCES employee (emp_id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 42
  DEFAULT CHARSET = utf8


CREATE TABLE employee (
  emp_id        INT(11)     NOT NULL AUTO_INCREMENT,
  emp_account   VARCHAR(50) NOT NULL,
  emp_password  VARCHAR(50) NOT NULL DEFAULT '123456',
  emp_name      VARCHAR(255)         DEFAULT NULL,
  emp_className VARCHAR(255)         DEFAULT NULL,
  emp_tel       VARCHAR(255)         DEFAULT NULL,
  emp_bedroom   VARCHAR(255)         DEFAULT NULL,
  dept_name     VARCHAR(50)          DEFAULT NULL,
  mgr_id        INT(11)              DEFAULT NULL,
  emp_type      INT(11)     NOT NULL DEFAULT '1',
  PRIMARY KEY (emp_id),
  UNIQUE KEY UK_5tltp1u3mevdum1ilp1ft71rg (emp_account),
  KEY FK_5f44y8jo1j00uhenw2naboii8 (mgr_id),
  CONSTRAINT FK_5f44y8jo1j00uhenw2naboii8 FOREIGN KEY (mgr_id) REFERENCES employee (emp_id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 101
  DEFAULT CHARSET = utf8