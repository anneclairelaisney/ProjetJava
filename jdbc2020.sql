-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: May 19, 2020 at 11:25 AM
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
(2, 'Analyse de Fourier');

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
(1, 1),
(6, 2);

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
(2, 1, 6),
(3, 2, 6),
(4, 3, 6),
(5, 4, 7);

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
(4, 'TD4', 1),
(5, 'TD5', 1),
(6, 'TD6', 1),
(7, 'TD7', 1),
(8, 'TD8', 1),
(9, 'TD9', 1),
(10, 'TD10', 1),
(11, 'TD11', 1);

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
(1, '2022'),
(2, '2021');

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
(316, 'P316', 40, 2);

-- --------------------------------------------------------

--
-- Table structure for table `SEANCE`
--

CREATE TABLE `SEANCE` (
  `ID` int(11) NOT NULL,
  `SEMAINE` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `HEURE_DEBUT` time NOT NULL,
  `HEURE_FIN` time NOT NULL,
  `ETAT` int(11) NOT NULL,
  `ID_COURS` int(11) NOT NULL,
  `ID_TYPE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `SEANCE`
--

INSERT INTO `SEANCE` (`ID`, `SEMAINE`, `DATE`, `HEURE_DEBUT`, `HEURE_FIN`, `ETAT`, `ID_COURS`, `ID_TYPE`) VALUES
(1, 21, '2020-05-20', '10:15:00', '11:45:00', 1, 1, 1);

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
(316, 1);

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
(1, 6);

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
(1, 316);

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
(2, 'E2');

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
(1, 'Cours magistral');

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
(1, 'jean-pierre.segado@ece.fr', 'ece', 'SEGADO', 'JEAN-PIERRE', 3),
(2, 'remy.ghidaglia@edu.ece.fr', 'ece', 'GHIDAGLIA', 'REMY', 4),
(3, 'anneclaire.laisney@edu.ece.fr', 'ece', 'LAISNEY', 'ANNE-CLAIRE', 4),
(4, 'rayan.perrin@edu.ece.fr', 'ece', 'PERRIN', 'RAYAN', 4),
(5, 'elia.levy@edu.ece.fr', 'ece', 'LEVY', 'Elia', 4),
(6, 'fabienne.coudray@ece.fr', 'ece', 'COUDRAY', 'FABIENNE', 3);

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
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `PROMOTION`
--
ALTER TABLE `PROMOTION`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `SALLE`
--
ALTER TABLE `SALLE`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=317;

--
-- AUTO_INCREMENT for table `SEANCE`
--
ALTER TABLE `SEANCE`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `SITE`
--
ALTER TABLE `SITE`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `TYPE_COURS`
--
ALTER TABLE `TYPE_COURS`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `UTILISATEUR`
--
ALTER TABLE `UTILISATEUR`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
  ADD CONSTRAINT `seance_enseignants_ibfk_2` FOREIGN KEY (`ID_SEANCE`) REFERENCES `SALLE` (`ID`);

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
  ADD CONSTRAINT `seance_salles_ibfk_1` FOREIGN KEY (`ID_SALLE`) REFERENCES `SALLE` (`ID`),
  ADD CONSTRAINT `seance_salles_ibfk_2` FOREIGN KEY (`ID_SEANCE`) REFERENCES `SEANCE` (`ID`);
