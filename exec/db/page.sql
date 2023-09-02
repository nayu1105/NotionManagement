CREATE TABLE `page` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `title` varchar(50) NOT NULL,
                        `context` text     NOT NULL,
                        `parent_id` int ,
                        PRIMARY KEY (`id`)
);
