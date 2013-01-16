-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Mer 16 Janvier 2013 à 12:59
-- Version du serveur: 5.1.36
-- Version de PHP: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `geonotes`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE IF NOT EXISTS `administrateur` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF1F3E384E3AB03` (`id`),
  KEY `fk_idadmin01_idx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `administrateur`
--


-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE IF NOT EXISTS `categorie` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) DEFAULT NULL,
  `Intitule` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Contenu de la table `categorie`
--


-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE IF NOT EXISTS `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commentaire` varchar(255) DEFAULT NULL,
  `dateCreation` date DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `id_admin` int(11) DEFAULT NULL,
  `id_categorie` int(11) DEFAULT NULL,
  `ville` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK33AFF2FC0C3163` (`id_admin`),
  KEY `FK33AFF22A8F842A` (`id_categorie`),
  KEY `fk_noteParc_idx` (`id_categorie`),
  KEY `fk_noteadmin_idx` (`id_admin`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=47 ;

--
-- Contenu de la table `note`
--


-- --------------------------------------------------------

--
-- Structure de la table `parcours`
--

CREATE TABLE IF NOT EXISTS `parcours` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commentaire` varchar(255) DEFAULT NULL,
  `dateCreation` date DEFAULT NULL,
  `denivele` double NOT NULL,
  `distance` double NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `id_admin` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK45EFDD89FC0C3163` (`id_admin`),
  KEY `fk_idadminparc_idx` (`id_admin`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Contenu de la table `parcours`
--


-- --------------------------------------------------------

--
-- Structure de la table `parcoursnotes`
--

CREATE TABLE IF NOT EXISTS `parcoursnotes` (
  `id_parcours` int(11) NOT NULL,
  `id_note` int(11) NOT NULL,
  PRIMARY KEY (`id_parcours`,`id_note`),
  KEY `FK267C7ED8FDC05037` (`id_parcours`),
  KEY `FK267C7ED862A678A8` (`id_note`),
  KEY `fk_parnotid_idx` (`id_parcours`),
  KEY `fk_parnotesid_idx` (`id_note`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `parcoursnotes`
--


-- --------------------------------------------------------

--
-- Structure de la table `parcours_utilisateurs`
--

CREATE TABLE IF NOT EXISTS `parcours_utilisateurs` (
  `id_utilisateur` int(11) NOT NULL,
  `id_parcours` int(11) NOT NULL,
  PRIMARY KEY (`id_utilisateur`,`id_parcours`),
  KEY `FKB57FD046C7A8A9C2` (`id_utilisateur`),
  KEY `FKB57FD046FDC05037` (`id_parcours`),
  KEY `fk_puserid_idx` (`id_utilisateur`),
  KEY `fk_pparid_idx` (`id_parcours`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `parcours_utilisateurs`
--


-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `DTYPE` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=64 ;

--
-- Contenu de la table `users`
--


-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDD1633834E3AB03` (`id`),
  KEY `fk_iduser_idx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `utilisateur`
--


--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD CONSTRAINT `fk_idadmin01` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `fk_noteadmin` FOREIGN KEY (`id_admin`) REFERENCES `administrateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_notecat` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `parcours`
--
ALTER TABLE `parcours`
  ADD CONSTRAINT `fk_idadminparc` FOREIGN KEY (`id_admin`) REFERENCES `administrateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `parcoursnotes`
--
ALTER TABLE `parcoursnotes`
  ADD CONSTRAINT `fk_parnotesid` FOREIGN KEY (`id_note`) REFERENCES `note` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_parnotid` FOREIGN KEY (`id_parcours`) REFERENCES `parcours` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `parcours_utilisateurs`
--
ALTER TABLE `parcours_utilisateurs`
  ADD CONSTRAINT `fk_pparid` FOREIGN KEY (`id_parcours`) REFERENCES `parcours` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_puserid` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `fk_iduser` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
