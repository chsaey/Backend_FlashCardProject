CREATE DATABASE  IF NOT EXISTS `flash_cards`;
USE `flash_cards`;
DROP TABLE IF EXISTS `flashcard`;
DROP TABLE IF EXISTS `flashcardset`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO users (user_name, password)
VALUES ('Dummy1','pwd'),
('Dummy2','pwd'),
('Dummy3','pwd');

CREATE TABLE `flashcardset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `userID` int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (userID) REFERENCES users(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO flashcardset (`name`, userID)
VALUES ('Java','1'),
('How to be cool','1'),
('Environmental studies','2'),
('World of Warcraft','3'),
('Music','3');

CREATE TABLE `flashcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(200) DEFAULT NULL,
  `answer` varchar(200) NOT NULL,
  `setID` int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (setID) REFERENCES flashcardset(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO flashcard (question, answer, setID)
VALUES ('What is OOP?','Object Oriented programming',1),
('What is Inheritance?','getting behaviors and attributes from a base class',1),
('What is Encapsuluation is?','Hiding data and providing methods to access them',1),
('What is Abstraction?','Hiding implementation, showing functionality',1),
('What is Polymorphism?','Changing behavior and attributes inherited from a super class',1),

('How do you greet someone?','Yo',2),
('What is your favorite game','MineCraft',2),
('What game was the biggest dissapointement in the last decade','Diablo III',2),

('Should you litter','No',3),
('What is recycling','Something you should do',3),

('What is Blizzards favorite class','Mage',4),
('What class do you play if you are an edge lord','Rogue',4),
('What class do you play if you like to wipe raids','Hunter',4),

('Is mayonnaise and instrument?','Yes',5),
('What song is this','Sandstorm - Darude',5);