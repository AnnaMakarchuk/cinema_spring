DROP TABLE IF EXISTS `hall`;
CREATE TABLE `hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `max_row` int(11) NOT NULL,
  `places_in_row` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
INSERT INTO `hall` VALUES (1,'Blue',6,9),(2,'Red',10,11),(3,'Yellow',5,6);

DROP TABLE IF EXISTS `movie_genre`;
CREATE TABLE `movie_genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genre` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
INSERT INTO `movie_genre` VALUES (1,'comedy'),(2,'fantasy'),(3,'action'),(4,'thriller'),(5,'melodrama'),(6,'cartoon');

DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `genre_id` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `age_limit` int(11) DEFAULT NULL,
  `description` mediumtext,
  `is_active` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`genre_id`) REFERENCES `movie_genre`
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
INSERT INTO `movie` VALUES (1,'Avengers11: EndGame',3,182,12,'After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to undo Thanos actions and restore order to the universe.',1),(2,'Alladin',2,128,12,'	In the heart of an enchanted city, a commoner named Aladdin and his mischievous monkey, Abu, battle to save the free-spirited Princess Jasmine. Aladdins life changes with one rub of a magic lamp as a fun-loving, shape-shifting Genie appears and grants him three wishes.',1),(3,'KuToppen',6,66,0,'Klara, a young city-calf, dreams of becoming a big star. One day she receives a letter from her father, Mosk, who wants her to visit his farm. Her mom says Mosk is a rock star, but when Clara arrives, she discovers her father is only a regular apple pie farmer.',1),(4,'Dark Phoenix',3,120,16,'Jean Grey begins to develop incredible powers that corrupt and turn her into a Dark Phoenix. Now the X-Men will have to decide if the life of a team member is worth more than all the people living in the world.',1),(5,'A Star Is Born',5,135,16,'A musician helps a young singer find fame as age and alcoholism send his own career into a downward spiral.',1),(6,'Made In Italy',5,100,16,'Riko works around a pigment than people. To shake things up, he decides to drive to Rome with his friends. Together with Sarah they take part in a protest during which Riko receives a blow to the head.',1),(7,'Poison Rose',4,120,16,'Inspired by classic film noir, Carson Phillips, an ex-football star turned PI, has a soft spot for a lady in distress.',0),(9,'Shazam!',1,132,12,'We all have a superhero inside us, it just takes a bit of magic to bring it out. In Billy Batsons case, by shouting out one word - SHAZAM. - this streetwise fourteen-year-old foster kid can turn into the grown-up superhero Shazam.',1),(10,'The Secret Life of Pets 2',6,86,0,'Continuing the story of Max and his pet friends, following their secret lives after their owners leave them for work or school each day.',1);

DROP TABLE IF EXISTS `price_type`;
CREATE TABLE `price_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `row_number` int(11) NOT NULL,
  `price` double NOT NULL,
  `hall_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`hall_id`) REFERENCES `hall`
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
INSERT INTO `price_type` VALUES (1,1,50,1),(2,2,50,1),(3,3,50,1),(4,4,75,1),(5,5,75,1),(6,6,150,1);

DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day_of_week` varchar(45) NOT NULL,
  `time` time NOT NULL,
  `hall_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `is_active` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`hall_id`) REFERENCES `hall`,
  FOREIGN KEY (`movie_id`) REFERENCES `movie`
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4;
INSERT INTO `schedule` VALUES (1,'MONDAY','09:00:00',1,3,1),(2,'MONDAY','10:30:00',1,6,1),(3,'MONDAY','12:30:00',1,9,1),(4,'MONDAY','15:00:00',1,10,1),(5,'MONDAY','18:00:00',1,7,0),(6,'MONDAY','22:00:00',1,1,1),(7,'TUESDAY','09:00:00',1,3,1),(8,'TUESDAY','10:30:00',1,6,1),(9,'TUESDAY','12:00:00',1,9,1),(10,'TUESDAY','15:00:00',1,5,1),(11,'TUESDAY','18:00:00',1,4,1),(12,'TUESDAY','22:00:00',1,1,1),(13,'WEDNESDAY','09:00:00',1,3,1),(14,'WEDNESDAY','10:30:00',1,6,1),(15,'WEDNESDAY','12:00:00',1,9,1),(16,'WEDNESDAY','15:00:00',1,5,1),(17,'WEDNESDAY','18:00:00',1,7,0),(18,'WEDNESDAY','22:00:00',1,1,1),(19,'THURSDAY','09:00:00',1,3,1),(20,'THURSDAY','10:30:00',1,4,1),(21,'THURSDAY','13:00:00',1,7,0),(22,'THURSDAY','15:30:00',1,1,1),(23,'THURSDAY','19:30:00',1,2,1),(24,'THURSDAY','22:00:00',1,4,1),(25,'FRIDAY','09:00:00',1,3,1),(26,'FRIDAY','10:30:00',1,4,1),(27,'FRIDAY','13:00:00',1,7,0),(28,'FRIDAY','15:30:00',1,1,1),(29,'FRIDAY','19:30:00',1,2,1),(30,'FRIDAY','22:00:00',1,4,1),(31,'SATURDAY','09:00:00',1,3,1),(32,'SATURDAY','10:30:00',1,4,1),(33,'SATURDAY','13:00:00',1,7,0),(34,'SATURDAY','15:30:00',1,1,1),(35,'SATURDAY','19:30:00',1,2,1),(36,'SATURDAY','22:00:00',1,4,1),(37,'SUNDAY','09:00:00',1,3,1),(38,'SUNDAY','10:30:00',1,4,1),(39,'SUNDAY','13:00:00',1,7,0),(40,'SUNDAY','15:30:00',1,1,0),(41,'SUNDAY','19:30:00',1,2,1),(42,'SUNDAY','22:00:00',1,4,0);


DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
INSERT INTO `user_role` VALUES (1,'administrator'),(2,'client');

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `email_address` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role_id` int(10) NOT NULL,
  `salary` double DEFAULT NULL,
  `working_hours_week` int(11) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_role`) REFERENCES `user_role`,
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
INSERT INTO `user` VALUES (1,'Anna','Makarchuk','FEMALE','anna','aniki.das@gmail.com','111',1,1000,40,NULL),(3,'Petro','Petrov','MALE','Petrov','petrov@i.ua','111',2,NULL,NULL,NULL),(4,'Ivan','Ivanov','MALE','IIva','ivanov@gmail.com','698d51a19d8a121ce581499d7b701668',2,NULL,NULL,NULL),(6,'Olga','Ospanova','FEMALE','ospOlga','o.osp@i.ua','111',1,1000,20,NULL),(7,'Volodymyr','Konoval','MALE','v.ko@gmail.com','v.kon','698d51a19d8a121ce581499d7b701668',2,NULL,NULL,NULL),(8,'Inna','Veselka','FEMALE','veselka@ukr.net','veselka','698d51a19d8a121ce581499d7b701668',2,NULL,NULL,NULL),(9,'Katya','Kot','FEMALE','kot@ukr.net','kot.k','698d51a19d8a121ce581499d7b701668',2,NULL,NULL,NULL),(10,'Maksym','Marsymov','MALE','mak.mak@ukr.net','maker','698d51a19d8a121ce581499d7b701668',2,NULL,NULL,NULL),(11,'Igor','Zhuk','MALE','zhyk.i@gmail.com','zhyk.i','698d51a19d8a121ce581499d7b701668',2,NULL,NULL,NULL),(12,'Alisa','Osipova','FEMALE','Ali@ukr.net','alisa','698d51a19d8a121ce581499d7b701668',2,NULL,NULL,NULL),(13,'Andrey','Titov','MALE','titovk@ukr.net','a.tit','698d51a19d8a121ce581499d7b701668',2,NULL,NULL,NULL),(14,'Anna','Anna','FEMALE','aaa','aniki.das@gmail.com','698d51a19d8a121ce581499d7b701668',2,NULL,NULL,NULL),(15,'Alisa','Alisenko','FEMALE','ali','a@i.ua','111',2,NULL,NULL,2);

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL,
  `place_row` int(11) NOT NULL,
  `place_number` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`schedule_id`) REFERENCES `schedule`,
  FOREIGN KEY (`user_id`) REFERENCES `user`
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;
INSERT INTO `ticket` VALUES (1,3,6,5,4,75),(4,4,1,2,3,50),(5,4,1,2,4,50),(6,4,1,2,5,50),(7,9,30,6,5,150),(8,9,30,6,6,150),(9,12,34,6,5,150),(10,12,34,6,6,150),(11,8,37,2,3,50),(12,8,37,2,4,50),(13,8,37,2,5,50),(14,11,12,5,4,75),(15,11,12,5,3,75),(17,12,34,6,4,150),(18,12,34,6,3,150),(19,3,1,5,4,75),(20,3,1,5,5,75),(21,4,2,3,4,50),(22,4,2,3,5,50),(23,4,2,3,6,50),(24,8,2,4,5,75),(25,8,2,4,8,75),(26,8,2,4,7,75),(27,3,2,4,8,75),(28,3,2,5,9,75),(29,3,2,4,8,75),(30,3,2,4,9,75),(31,3,2,3,9,50),(32,3,2,3,1,50),(33,3,2,6,9,150);
