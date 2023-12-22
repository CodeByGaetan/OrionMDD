# Tables creation
CREATE TABLE `USERS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50) UNIQUE,
  `email` VARCHAR(50) UNIQUE,
  `password` VARCHAR(255)
);

CREATE TABLE `TOPICS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(255),
  `description` VARCHAR(2000)
);

CREATE TABLE `POSTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(255),
  `content` VARCHAR(2000),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT,
  `topic_id` INT
);

CREATE TABLE `COMMENTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `content` VARCHAR(2000),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT,
  `post_id` INT
);

CREATE TABLE `SUBSCRIPTIONS` (
  `user_id` INT, 
  `topic_id` INT
);


# Relation constraints between tables
ALTER TABLE `POSTS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `POSTS` ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPICS` (`id`);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`post_id`) REFERENCES `POSTS` (`id`);
ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPICS` (`id`);


# Example values
INSERT INTO `USERS` (`name`, `email`, `password`) VALUES
("gaetanls", "gaetanls@gmail.com", "azerty123");

INSERT INTO `TOPICS` (`title`, `description`) VALUES
("Python", "Python est un langage de programmation largement utilisé dans les applications Web, le développement de logiciels, la science des données et le machine learning (ML)."),
("IA", "L'intelligence artificielle (IA) est un processus d'imitation de l'intelligence humaine qui repose sur la création et l'application d'algorithmes exécutés dans un environnement informatique dynamique.");

INSERT INTO `POSTS` (`title`, `content`, `created_at`, `user_id`, `topic_id`) VALUES
("L'API Revit avec Python !", "Le logiciel de BIM, Revit, ouvre son API afin de créer des automatisations grâce à Python et Dynamo.", "2023-12-15", 1, 1);

INSERT INTO `COMMENTS` (`content`, `created_at`, `user_id`, `post_id`) VALUES
("C'est moi qui est écrit ce superbe article !", "2023-12-16", 1, 1);

INSERT INTO `SUBSCRIPTIONS` (`user_id`, `topic_id`) VALUES
(1, 1);