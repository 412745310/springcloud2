DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` varchar(64) NOT NULL,
  `REAL_NM` varchar(64) DEFAULT NULL,
  `WEB_FACE` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_user_login`;
CREATE TABLE `sys_user_login` (
  `USER_ID` varchar(64) NOT NULL,
  `LOGIN_EMAIL` varchar(64) DEFAULT NULL,
  `WEB_FACE` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(64) NOT NULL,
  `ROLE_NM` varchar(64) DEFAULT NULL,
  `IS_ADMIN` int(2) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `USER_ID` varchar(64) DEFAULT NULL,
  `ROLE_ID` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;