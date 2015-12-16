CREATE TABLE `cubeit_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(500) NOT NULL,
  `owner_user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_content_owner_user_id` (`owner_user_id`),
  CONSTRAINT `fk_content_owner_user_id` FOREIGN KEY (`owner_user_id`) REFERENCES `cubeit_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `cube` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `owner_user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_cube_owner_user_id` (`owner_user_id`),
  CONSTRAINT `fk_cube_owner_user_id` FOREIGN KEY (`owner_user_id`) REFERENCES `cubeit_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `cube_content_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cube_id` int(11) NOT NULL,
  `content_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_cube_content_mapping_cube_id` (`cube_id`),
  CONSTRAINT `fk_cube_content_mapping_cube_id` FOREIGN KEY (`cube_id`) REFERENCES `cube` (`id`),
  KEY `fk_cube_content_mapping_content_id` (`content_id`),
  CONSTRAINT `fk_cube_content_mapping_content_id` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_shared_cube` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `cube_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_user_shared_cube_user_id` (`user_id`),
  CONSTRAINT `fk_user_shared_cube_user_id` FOREIGN KEY (`user_id`) REFERENCES `cubeit_user` (`id`),
  KEY `fk_user_shared_cube_cube_id` (`cube_id`),
  CONSTRAINT `fk_user_shared_cube_cube_id` FOREIGN KEY (`cube_id`) REFERENCES `cube` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_shared_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `content_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_user_shared_content_user_id` (`user_id`),
  CONSTRAINT `fk_user_shared_content_user_id` FOREIGN KEY (`user_id`) REFERENCES `cubeit_user` (`id`),
  KEY `fk_user_shared_content_content_id` (`content_id`),
  CONSTRAINT `fk_user_shared_content_content_id` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;