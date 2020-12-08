CREATE DATABASE  IF NOT EXISTS `flash_cards`;
USE `flash_cards`;
DROP TABLE IF EXISTS `flashcard`;
DROP TABLE IF EXISTS `flashcardset`;
DROP TABLE IF EXISTS `users`;


CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `flashcardset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `userID` int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (userID) REFERENCES users(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



CREATE TABLE `flashcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(45) DEFAULT NULL,
  `answer` int(11) NOT NULL,
  `setID` int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (setID) REFERENCES users(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;