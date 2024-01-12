-- Example values
INSERT INTO `USERS` (`name`, `email`, `password`) VALUES
('JohnDoe', 'johndoe@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
('AliceSmith', 'alicesmith@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
('BobJohnson', 'bobjohnson@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
('EmmaBrown', 'emmabrown@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
('AlexWilson', 'alexwilson@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.');

INSERT INTO `TOPICS` (`title`, `description`) VALUES
('Cybersecurity', 'Explorez les concepts et les stratégies de sécurité informatique pour protéger les systèmes et les données contre les menaces en ligne.'),
('JavaScript', 'Apprenez le langage de programmation JavaScript pour ajouter des fonctionnalités dynamiques et interactives à vos sites web.'),
('Mobile App Development', 'Explorez le développement d''applications mobiles pour iOS et Android en utilisant des frameworks tels que React Native ou Flutter.'),
('Cloud Computing', 'Découvrez les services de cloud computing tels que AWS, Azure et Google Cloud pour déployer et gérer vos applications à l''échelle mondiale.'),
('Blockchain Technology', 'Plongez dans la technologie de la blockchain et comprenez son rôle dans les applications décentralisées et les crypto-monnaies.'),
('DevOps', 'Adoptez les pratiques DevOps pour améliorer la collaboration entre les équipes de développement et d''exploitation et accélérer le déploiement logiciel.'),
('IoT (Internet of Things)', 'Explorez l''Internet des objets et apprenez à connecter des appareils physiques pour collecter et échanger des données.'),
('Machine Learning', 'Découvrez les algorithmes et les modèles de machine learning pour créer des systèmes capables d''apprendre à partir de données.'),
('Front-end Frameworks', 'Explorez des frameworks front-end populaires tels que React, Angular et Vue.js pour construire des interfaces utilisateur interactives.'),
('Game Development', 'Plongez dans le développement de jeux vidéo et apprenez à créer des expériences interactives et immersives.'),
('Big Data Analytics', 'Explorez les techniques d''analyse de données pour extraire des informations significatives à partir de grandes quantités de données structurées et non structurées.'),
('Python', 'Python est un langage de programmation largement utilisé dans les applications Web, le développement de logiciels, la science des données et le machine learning (ML).'),
('IA', 'L''intelligence artificielle (IA) est un processus d''imitation de l''intelligence humaine qui repose sur la création et l''application d''algorithmes exécutés dans un environnement informatique dynamique.');

INSERT INTO `POSTS` (`title`, `content`, `created_at`, `user_id`, `topic_id`) VALUES
('Les bases de la programmation en Python', 'Découvrez les concepts fondamentaux de la programmation en Python, du contrôle de flux aux structures de données de base.', '2024-01-02', 1, 12),
('Construire des applications web avec Django', 'Apprenez à créer des applications web puissantes en utilisant le framework Django en Python pour la gestion des bases de données, le routage et les vues.', '2023-12-28', 2, 1),
('Introduction à l''apprentissage automatique', 'Explorez les bases de l''apprentissage automatique, des algorithmes classiques aux modèles de deep learning, pour résoudre une variété de problèmes.', '2023-12-25', 1, 7),
('Sécuriser votre application avec OAuth 2.0', 'Comprenez le protocole OAuth 2.0 et apprenez à sécuriser vos applications en gérant l''authentification et l''autorisation des utilisateurs.', '2024-01-05', 3, 4),
('Création d''une application mobile avec React Native', 'Explorez le développement d''applications mobiles multiplateformes en utilisant React Native pour construire des interfaces utilisateur réactives.', '2024-01-08', 4, 2),
('Les bases de la cybersécurité pour les développeurs', 'Apprenez les principes fondamentaux de la cybersécurité, des attaques courantes aux meilleures pratiques de protection des applications.', '2024-01-12', 5, 5),
('Analyse de données avec pandas et matplotlib', 'Découvrez comment utiliser les bibliothèques Python pandas et matplotlib pour l''analyse de données et la visualisation de manière efficace.', '2024-01-15', 3, 6),
('Création d''un site web moderne avec Vue.js', 'Apprenez à construire des applications web interactives en utilisant le framework Vue.js pour la création de composants réutilisables.', '2024-01-18', 2, 8),
('Introduction à la réalité virtuelle', 'Explorez les bases de la réalité virtuelle et apprenez à créer des expériences immersives avec des casques VR et des moteurs de jeu.', '2024-01-22', 1, 9),
('Développement de jeux 2D avec Unity', 'Découvrez les concepts de base du développement de jeux 2D en utilisant le moteur de jeu Unity pour créer des jeux ludiques et divertissants.', '2024-01-25', 5, 10),
('L''API Revit avec Python !', 'Le logiciel de BIM, Revit, ouvre son API afin de créer des automatisations grâce à Python et Dynamo.', '2023-12-15', 2, 1),
('Optimisation des performances SEO pour les sites web', 'Découvrez des stratégies avancées pour améliorer le référencement de votre site web, y compris l''optimisation des balises méta et la vitesse de chargement.', '2024-01-28', 1, 2),
('Introduction à la programmation quantique avec Qiskit', 'Explorez les bases de la programmation quantique en utilisant le framework Qiskit avec Python pour créer et manipuler des algorithmes quantiques.', '2024-02-01', 3, 7);

INSERT INTO `COMMENTS` (`content`, `created_at`, `user_id`, `post_id`) VALUES
('Excellent article, vraiment instructif !', '2024-01-03 12:30:00', 2, 1),
('Je suis curieux d''en apprendre davantage sur Django.', '2024-01-05 15:45:00', 3, 2),
('L''apprentissage automatique est fascinant, merci pour l''introduction.', '2024-01-03 18:20:00', 4, 3),
('Bonne explication sur OAuth 2.0, c''est crucial pour la sécurité.', '2024-01-07 10:10:00', 5, 4),
('React Native est vraiment puissant pour le développement mobile.', '2024-01-10 14:00:00', 1, 5),
('Sécurité d''abord ! Merci pour cet article sur la cybersécurité.', '2024-01-14 09:45:00', 2, 6),
('L''analyse de données est une compétence essentielle, merci pour les conseils.', '2024-01-16 17:30:00', 3, 7),
('Vue.js simplifie vraiment le développement web.', '2024-01-20 11:55:00', 4, 8),
('La réalité virtuelle ouvre de nouvelles perspectives passionnantes !', '2024-01-24 13:40:00', 5, 9),
('Unity est génial pour le développement de jeux, j''adore !', '2024-01-27 16:15:00', 1, 10),
('J''ai également apprécié la partie sur les bases de Python !', '2024-01-04 08:15:00', 3, 1),
('Merci pour la clarté de vos explications.', '2024-01-06 14:20:00', 4, 1),
('Django est vraiment puissant, j''aimerais en savoir plus sur les projets réels que vous avez développés avec !', '2024-01-08 10:30:00', 5, 2),
('Les modèles de deep learning sont passionnants, avez-vous des recommandations pour approfondir ce sujet ?', '2024-01-04 16:45:00', 1, 3),
('L''authentification OAuth 2.0 est cruciale, merci pour la mise en lumière.', '2024-01-06 12:50:00', 2, 4),
('Quels sont les avantages pratiques de l''utilisation d''OAuth 2.0 dans une application ?', '2024-01-07 18:30:00', 3, 4),
('React Native est-il plus performant que d''autres frameworks pour le développement mobile ?', '2024-01-10 16:00:00', 2, 5),
('J''ai utilisé React Native dans un projet récent et c''était incroyablement efficace !', '2024-01-11 09:15:00', 3, 5),
('La cybersécurité est une préoccupation croissante, merci pour ces informations utiles.', '2024-01-13 11:30:00', 4, 6),
('Pandas et Matplotlib sont des outils essentiels dans l''analyse de données. Bien expliqué !', '2024-01-15 20:00:00', 5, 7),
('Vue.js facilite vraiment la création d''interfaces utilisateur réactives, merci pour le tutoriel !', '2024-01-18 15:45:00', 1, 8),
('Avez-vous des recommandations pour des projets Vue.js avancés ?', '2024-01-19 09:30:00', 2, 8),
('La réalité virtuelle est un domaine passionnant ! Des suggestions pour les débutants ?', '2024-01-23 14:20:00', 3, 9),
('Unity est génial, mais je trouve le développement 2D plus accessible pour les débutants.', '2024-01-26 12:10:00', 4, 10);

INSERT INTO `SUBSCRIPTIONS` (`user_id`, `topic_id`) VALUES
(1, 1),
(1, 3),
(1, 4),
(1, 7),
(2, 3),
(3, 5),
(4, 7),
(5, 9);