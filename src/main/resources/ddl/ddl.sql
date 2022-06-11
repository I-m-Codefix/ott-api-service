-- imcf.tbl_account definition

CREATE TABLE `tbl_account`
(
    `id`           bigint(20)   NOT NULL,
    `name`         varchar(255) NOT NULL,
    `accessToken`  varchar(255)          DEFAULT NULL,
    `email`        varchar(255) NOT NULL,
    `isAuth`       char(1)      NOT NULL DEFAULT 'N',
    `password`     varchar(255) NOT NULL,
    `platformType` varchar(10)  NOT NULL DEFAULT 'IMCF',
    `profileImage` varchar(255)          DEFAULT NULL,
    `refreshToken` varchar(255)          DEFAULT NULL,
    `createdDate`  datetime(6)  NOT NULL,
    `modifiedDate` datetime(6)           DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_bgtc7d72asv5uvpiucu8pif70` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;