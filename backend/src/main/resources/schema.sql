DROP TABLE IF EXISTS `page` CASCADE;

CREATE TABLE `page`
(
    `id`        BIGINT      NOT NULL AUTO_INCREMENT,
    `title`     VARCHAR(50) NOT NULL,
    `context`   TEXT        NOT NULL,
    `parent_id` BIGINT,
    PRIMARY KEY (`id`)
);