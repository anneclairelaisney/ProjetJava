-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jun 02, 2020 at 06:48 AM
-- Server version: 5.7.26
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `jdbc2020`
--

-- --------------------------------------------------------

--
-- Table structure for table `COURS`
--

CREATE TABLE `COURS` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `COURS`
--

INSERT INTO `COURS` (`ID`, `NOM`) VALUES
(1, 'POO JAVA'),
(2, 'Analyse de Fourier'),
(3, 'Probabilités et statistiques');

-- --------------------------------------------------------

--
-- Table structure for table `ENSEIGNANT`
--

CREATE TABLE `ENSEIGNANT` (
  `ID_UTILISATEUR` int(11) NOT NULL,
  `ID_COURS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ENSEIGNANT`
--

INSERT INTO `ENSEIGNANT` (`ID_UTILISATEUR`, `ID_COURS`) VALUES
(21, 1),
(23, 1),
(22, 2),
(24, 3);

-- --------------------------------------------------------

--
-- Table structure for table `ETUDIANT`
--

CREATE TABLE `ETUDIANT` (
  `ID_UTILISATEUR` int(11) NOT NULL,
  `NUMERO` int(11) NOT NULL,
  `ID_GROUPE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ETUDIANT`
--

INSERT INTO `ETUDIANT` (`ID_UTILISATEUR`, `NUMERO`, `ID_GROUPE`) VALUES
(1, 2021001, 2),
(2, 2021002, 1),
(3, 2021003, 3),
(4, 2021004, 1),
(5, 2021005, 3),
(6, 2021006, 2),
(7, 2022001, 6),
(8, 2022002, 4),
(9, 2022003, 6),
(10, 2022004, 5),
(11, 2022005, 5),
(12, 2022006, 4),
(13, 2023001, 7),
(14, 2023002, 9),
(15, 2023003, 8),
(16, 2023004, 7),
(17, 2023005, 9),
(18, 2023006, 8);

-- --------------------------------------------------------

--
-- Table structure for table `GROUPE`
--

CREATE TABLE `GROUPE` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL,
  `ID_PROMOTION` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `GROUPE`
--

INSERT INTO `GROUPE` (`ID`, `NOM`, `ID_PROMOTION`) VALUES
(1, 'TD1', 1),
(2, 'TD2', 1),
(3, 'TD3', 1),
(4, 'TD1', 2),
(5, 'TD2', 2),
(6, 'TD3', 2),
(7, 'TD1', 3),
(8, 'TD2', 3),
(9, 'TD3', 3);

-- --------------------------------------------------------

--
-- Table structure for table `PROMOTION`
--

CREATE TABLE `PROMOTION` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `PROMOTION`
--

INSERT INTO `PROMOTION` (`ID`, `NOM`) VALUES
(1, '2021'),
(2, '2022'),
(3, '2023');

-- --------------------------------------------------------

--
-- Table structure for table `SALLE`
--

CREATE TABLE `SALLE` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL,
  `CAPACITE` int(11) NOT NULL,
  `ID_SITE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `SALLE`
--

INSERT INTO `SALLE` (`ID`, `NOM`, `CAPACITE`, `ID_SITE`) VALUES
(1, 'EM009', 200, 1),
(2, 'EM010', 200, 1),
(3, 'SC001', 40, 1),
(4, 'P316', 40, 2),
(5, 'P315', 30, 2),
(6, 'P445', 70, 2),
(7, 'C314', 30, 3),
(8, 'C316', 40, 3),
(9, 'C322', 40, 3);

-- --------------------------------------------------------

--
-- Table structure for table `SEANCE`
--

CREATE TABLE `SEANCE` (
  `ID` int(11) NOT NULL,
  `SEMAINE` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `HEURE_DEBUT` int(11) NOT NULL,
  `HEURE_FIN` int(11) NOT NULL,
  `ETAT` int(11) NOT NULL,
  `ID_COURS` int(11) NOT NULL,
  `ID_TYPE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `SEANCE`
--

INSERT INTO `SEANCE` (`ID`, `SEMAINE`, `DATE`, `HEURE_DEBUT`, `HEURE_FIN`, `ETAT`, `ID_COURS`, `ID_TYPE`) VALUES
(1, 22, '2020-05-25', 10, 11, 1, 1, 1),
(2, 22, '2020-05-27', 9, 10, 1, 1, 3),
(3, 22, '2020-05-28', 14, 15, 1, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `SEANCE_ENSEIGNANTS`
--

CREATE TABLE `SEANCE_ENSEIGNANTS` (
  `ID_SEANCE` int(11) NOT NULL,
  `ID_ENSEIGNANT` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `SEANCE_ENSEIGNANTS`
--

INSERT INTO `SEANCE_ENSEIGNANTS` (`ID_SEANCE`, `ID_ENSEIGNANT`) VALUES
(1, 21),
(1, 23),
(1, 21),
(3, 24),
(2, 24);

-- --------------------------------------------------------

--
-- Table structure for table `SEANCE_GROUPES`
--

CREATE TABLE `SEANCE_GROUPES` (
  `ID_SEANCE` int(11) NOT NULL,
  `ID_GROUPE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `SEANCE_GROUPES`
--

INSERT INTO `SEANCE_GROUPES` (`ID_SEANCE`, `ID_GROUPE`) VALUES
(1, 4),
(1, 5),
(1, 6),
(1, 1),
(1, 2),
(3, 7),
(3, 8),
(3, 9),
(2, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `SEANCE_SALLES`
--

CREATE TABLE `SEANCE_SALLES` (
  `ID_SEANCE` int(11) NOT NULL,
  `ID_SALLE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `SEANCE_SALLES`
--

INSERT INTO `SEANCE_SALLES` (`ID_SEANCE`, `ID_SALLE`) VALUES
(1, 1),
(1, 2),
(2, 6),
(3, 3),
(3, 7);

-- --------------------------------------------------------

--
-- Table structure for table `SITE`
--

CREATE TABLE `SITE` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `SITE`
--

INSERT INTO `SITE` (`ID`, `NOM`) VALUES
(1, 'E1'),
(2, 'E2'),
(3, 'E3');

-- --------------------------------------------------------

--
-- Table structure for table `TYPE_COURS`
--

CREATE TABLE `TYPE_COURS` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `TYPE_COURS`
--

INSERT INTO `TYPE_COURS` (`ID`, `NOM`) VALUES
(1, 'Cours magistral'),
(2, 'TP'),
(3, 'TD'),
(4, 'Projet'),
(5, 'Soutien');

-- --------------------------------------------------------

--
-- Table structure for table `UTILISATEUR`
--

CREATE TABLE `UTILISATEUR` (
  `ID` int(11) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PASSWD` varchar(255) NOT NULL,
  `NOM` varchar(255) NOT NULL,
  `PRENOM` varchar(255) NOT NULL,
  `DROIT` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `UTILISATEUR`
--

INSERT INTO `UTILISATEUR` (`ID`, `EMAIL`, `PASSWD`, `NOM`, `PRENOM`, `DROIT`) VALUES
(1, 'eva.dreyfus@edu.ece.fr', 'ece', 'DREYFUS', 'Eva', 4),
(2, 'annelise.herve@edu.ece.fr', 'ece', 'HERVE', 'Anne-Lise', 4),
(3, 'jade.laville@edu.ece.fr', 'ece', 'LAVILLE', 'Jade', 4),
(4, 'clement.lurati@edu.ece.fr', 'ece', 'LURATI', 'Clément', 4),
(5, 'axel.sani@edu.ece.fr', 'ece', 'SANI', 'Axel', 4),
(6, 'mario.yammine@edu.ece.fr', 'ece', 'YAMMINE', 'Mario', 4),
(7, 'arthur.damele@edu.ece.fr', 'ece', 'DAMELE', 'Arthur', 4),
(8, 'rems.ghidaglia@edu.ece.fr', 'ece', 'GHIDAGLIA', 'Remy', 4),
(9, 'anneclaire.laisney@edu.ece.fr', 'ece', 'LAISNEY', 'Anne-Claire', 4),
(10, 'elia.levy@edu.ece.fr', 'ece', 'LEVY', 'Elia', 4),
(11, 'rayan.perrin@edu.ece.fr', 'ece', 'PERRIN', 'Rayan', 4),
(12, 'jeanne.roques@edu.ece.fr', 'ece', 'ROQUES', 'Jeanne', 4),
(13, 'pierre.badault@edu.ece.fr', 'ece', 'BADAULT', 'Pierre', 4),
(14, 'olivia.delachapelle@edu.ece.fr', 'ece', 'DE LA CHAPELLE', 'Olivia', 4),
(15, 'lea.guignochau@edu.ece.fr', 'ece', 'GUIGNOCHAU', 'Léa', 4),
(16, 'agathe.leseche@edu.ece.fr', 'ece', 'LESECHE', 'Agathe', 4),
(17, 'emily.richardson@edu.ece.fr', 'ece', 'RICHARDSON', 'Emily', 4),
(18, 'tom.sella@edu.ece.fr', 'ece', 'SELLA', 'Tom', 4),
(19, 'admin@ece.fr', 'ece', 'ADMIN', 'ECE', 1),
(20, 'marie.saad@ece.fr', 'ece', 'SAAD', 'Marie', 2),
(21, 'jeanpierre.segado@ece.fr', 'ece', 'SEGADO', 'Jean-Pierre', 3),
(22, 'fabienne.coudray@ece.fr', 'ece', 'COUDRAY', 'Fabienne', 3),
(23, 'manolo.hina@ece.fr', 'ece', 'HINA', 'Manolo', 3),
(24, 'luc.lecor@ece.fr', 'ece', 'LE COR', 'Luc', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `COURS`
--
ALTER TABLE `COURS`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `ENSEIGNANT`
--
ALTER TABLE `ENSEIGNANT`
  ADD PRIMARY KEY (`ID_UTILISATEUR`),
  ADD KEY `ID_COURS` (`ID_COURS`),
  ADD KEY `ID_UTILISATEUR` (`ID_UTILISATEUR`) USING BTREE;

--
-- Indexes for table `ETUDIANT`
--
ALTER TABLE `ETUDIANT`
  ADD PRIMARY KEY (`ID_UTILISATEUR`),
  ADD KEY `ID_GROUPE` (`ID_GROUPE`),
  ADD KEY `ID_UTILISATEUR` (`ID_UTILISATEUR`);

--
-- Indexes for table `GROUPE`
--
ALTER TABLE `GROUPE`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_PROMOTION` (`ID_PROMOTION`);

--
-- Indexes for table `PROMOTION`
--
ALTER TABLE `PROMOTION`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `SALLE`
--
ALTER TABLE `SALLE`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_SITE` (`ID_SITE`);

--
-- Indexes for table `SEANCE`
--
ALTER TABLE `SEANCE`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_COURS` (`ID_COURS`),
  ADD KEY `ID_TYPE` (`ID_TYPE`);

--
-- Indexes for table `SEANCE_ENSEIGNANTS`
--
ALTER TABLE `SEANCE_ENSEIGNANTS`
  ADD KEY `ID_SEANCE` (`ID_SEANCE`),
  ADD KEY `ID_ENSEIGNANT` (`ID_ENSEIGNANT`);

--
-- Indexes for table `SEANCE_GROUPES`
--
ALTER TABLE `SEANCE_GROUPES`
  ADD KEY `ID_SEANCE` (`ID_SEANCE`),
  ADD KEY `ID_SEANCE_2` (`ID_SEANCE`),
  ADD KEY `ID_GROUPE` (`ID_GROUPE`);

--
-- Indexes for table `SEANCE_SALLES`
--
ALTER TABLE `SEANCE_SALLES`
  ADD KEY `ID_SEANCE` (`ID_SEANCE`),
  ADD KEY `ID_SALLE` (`ID_SALLE`);

--
-- Indexes for table `SITE`
--
ALTER TABLE `SITE`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `TYPE_COURS`
--
ALTER TABLE `TYPE_COURS`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `UTILISATEUR`
--
ALTER TABLE `UTILISATEUR`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `GROUPE`
--
ALTER TABLE `GROUPE`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `PROMOTION`
--
ALTER TABLE `PROMOTION`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `SALLE`
--
ALTER TABLE `SALLE`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `SEANCE`
--
ALTER TABLE `SEANCE`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `SITE`
--
ALTER TABLE `SITE`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `TYPE_COURS`
--
ALTER TABLE `TYPE_COURS`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `UTILISATEUR`
--
ALTER TABLE `UTILISATEUR`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ENSEIGNANT`
--
ALTER TABLE `ENSEIGNANT`
  ADD CONSTRAINT `enseignant_ibfk_1` FOREIGN KEY (`ID_UTILISATEUR`) REFERENCES `UTILISATEUR` (`ID`),
  ADD CONSTRAINT `enseignant_ibfk_2` FOREIGN KEY (`ID_COURS`) REFERENCES `COURS` (`ID`);

--
-- Constraints for table `ETUDIANT`
--
ALTER TABLE `ETUDIANT`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`ID_GROUPE`) REFERENCES `GROUPE` (`ID`),
  ADD CONSTRAINT `etudiant_ibfk_2` FOREIGN KEY (`ID_UTILISATEUR`) REFERENCES `UTILISATEUR` (`ID`);

--
-- Constraints for table `GROUPE`
--
ALTER TABLE `GROUPE`
  ADD CONSTRAINT `groupe_ibfk_1` FOREIGN KEY (`ID_PROMOTION`) REFERENCES `PROMOTION` (`ID`);

--
-- Constraints for table `SALLE`
--
ALTER TABLE `SALLE`
  ADD CONSTRAINT `salle_ibfk_1` FOREIGN KEY (`ID_SITE`) REFERENCES `SITE` (`ID`);

--
-- Constraints for table `SEANCE`
--
ALTER TABLE `SEANCE`
  ADD CONSTRAINT `seance_ibfk_1` FOREIGN KEY (`ID_COURS`) REFERENCES `COURS` (`ID`),
  ADD CONSTRAINT `seance_ibfk_2` FOREIGN KEY (`ID_TYPE`) REFERENCES `TYPE_COURS` (`ID`);

--
-- Constraints for table `SEANCE_ENSEIGNANTS`
--
ALTER TABLE `SEANCE_ENSEIGNANTS`
  ADD CONSTRAINT `seance_enseignants_ibfk_1` FOREIGN KEY (`ID_ENSEIGNANT`) REFERENCES `ENSEIGNANT` (`ID_UTILISATEUR`),
  ADD CONSTRAINT `seance_enseignants_ibfk_2` FOREIGN KEY (`ID_SEANCE`) REFERENCES `SEANCE` (`ID`);

--
-- Constraints for table `SEANCE_GROUPES`
--
ALTER TABLE `SEANCE_GROUPES`
  ADD CONSTRAINT `seance_groupes_ibfk_1` FOREIGN KEY (`ID_GROUPE`) REFERENCES `GROUPE` (`ID`),
  ADD CONSTRAINT `seance_groupes_ibfk_2` FOREIGN KEY (`ID_SEANCE`) REFERENCES `SEANCE` (`ID`);

--
-- Constraints for table `SEANCE_SALLES`
--
ALTER TABLE `SEANCE_SALLES`
  ADD CONSTRAINT `seance_salles_ibfk_1` FOREIGN KEY (`ID_SEANCE`) REFERENCES `SEANCE` (`ID`),
  ADD CONSTRAINT `seance_salles_ibfk_2` FOREIGN KEY (`ID_SALLE`) REFERENCES `SALLE` (`ID`);
