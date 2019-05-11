create database abcpizzaorderservice;
GRANT ALL PRIVILEGES ON abcpizzaorderservice.* TO 'mysqluser'@'%' WITH GRANT OPTION;

USE abcpizzaorderservice;
DROP table IF EXISTS orderstatus;
DROP table IF EXISTS  auditlog;
DROP table IF EXISTS  statusmaster;

create table orderstatus (
  order_status_id BIGINT PRIMARY KEY,
  status_id BIGINT,
  order_data VARCHAR(1000) NOT NULL,
  phone_number VARCHAR(20) NOT NULL,
  orderid VARCHAR(20) NOT NULL,
  updatedat TIMESTAMP NOT NULL,
  updatedby VARCHAR(20) NOT NULL,
  createdat TIMESTAMP NOT NULL,
  createdby VARCHAR(20) NOT NULL,
  is_active CHAR(1)
);

CREATE INDEX orderstatus_idx ON orderstatus(order_id, phone_number, status_id);

create table statusmaster (
  status_id BIGINT,
  statusdesc VARCHAR(500),
  entity_version VARCHAR(100) NOT NULL,
  is_active CHAR(1),
  PRIMARY KEY(status_id, entity_version)
);

create table auditlog (
  auditlog_id VARCHAR(1000),
  activity VARCHAR(1000),
  PRIMARY KEY(auditlog_id)
);

INSERT INTO `abcpizzaorderservice`.`statusmaster` (`status_id`,`statusdesc`,`entity_version`,`is_active`) VALUES(1, 'MAKING', '1.0', '1');
INSERT INTO `abcpizzaorderservice`.`statusmaster` (`status_id`,`statusdesc`,`entity_version`,`is_active`) VALUES(2, 'BAKING', '1.0', '1');
INSERT INTO `abcpizzaorderservice`.`statusmaster` (`status_id`,`statusdesc`,`entity_version`,`is_active`) VALUES(3, 'BOXING', '1.0', '1');
INSERT INTO `abcpizzaorderservice`.`statusmaster` (`status_id`,`statusdesc`,`entity_version`,`is_active`) VALUES(4, 'ON ITS WAY', '1.0', '1');
INSERT INTO `abcpizzaorderservice`.`statusmaster` (`status_id`,`statusdesc`,`entity_version`,`is_active`) VALUES(5, 'DELIVERED', '1.0', '1');
commit;

INSERT INTO `abcpizzaorderservice`.`orderstatus`(`order_status_id`,`status_id`,`order_data`,`phone_number`,`orderid`,`updatedat`,`updatedby`,`createdat`,`createdby`,`is_active`) VALUES(1,1,'{test}','919674683463','12345',CURRENT_TIMESTAMP,'test',CURRENT_TIMESTAMP,'test','1');
commit;
