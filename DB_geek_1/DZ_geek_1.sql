CREATE TABLE `country` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `Country` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `district` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `District` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `region` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `Region` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `town` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `Town` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `world` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `country_id` int unsigned NOT NULL,
  `district_id` int unsigned NOT NULL,
  `region_id` int unsigned NOT NULL,
  `town_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `country_id_idx` (`country_id`),
  KEY `district_id_idx` (`district_id`),
  KEY `region_id_fk_idx` (`region_id`),
  KEY `town_id__fk_idx` (`town_id`),
  CONSTRAINT `country_id_fk` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  CONSTRAINT `district_id_fk` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`),
  CONSTRAINT `region_id_fk` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `town_id__fk` FOREIGN KEY (`town_id`) REFERENCES `town` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
SELECT * FROM dz_geekbrains1.world;