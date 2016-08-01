-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 06, 2015 at 08:12 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `crm`
--

-- --------------------------------------------------------

--
-- Table structure for table `komercijalista`
--

CREATE TABLE IF NOT EXISTS `komercijalista` (
  `kid` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `prezime` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `pass` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`kid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `komercijalista`
--

INSERT INTO `komercijalista` (`kid`, `ime`, `prezime`, `username`, `pass`) VALUES
(1, 'Petar', 'Petrović', 'peca', 'peca123');

-- --------------------------------------------------------

--
-- Table structure for table `kontaktosoba`
--

CREATE TABLE IF NOT EXISTS `kontaktosoba` (
  `kupid` int(11) NOT NULL,
  `kontid` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `prezime` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `telefon` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `napomene` text CHARACTER SET utf8 COLLATE utf8_bin,
  PRIMARY KEY (`kontid`,`kupid`),
  KEY `fk` (`kupid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `kontaktosoba`
--

INSERT INTO `kontaktosoba` (`kupid`, `kontid`, `ime`, `prezime`, `telefon`, `email`, `napomene`) VALUES
(1, 1, 'Marko', 'Maksic', '011 123 223', 'mare@max.rs', 'Ovo je napomena za Marka'),
(6, 4, 'Petar', 'Petrović', '011 222 2333', 'petar@uniqa.rs', 'Zvati između 9 i 11h.');

-- --------------------------------------------------------

--
-- Table structure for table `kupac`
--

CREATE TABLE IF NOT EXISTS `kupac` (
  `kupid` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `pib` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `mib` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ulicaibroj` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `kid` int(11) NOT NULL,
  `mid` int(11) NOT NULL,
  PRIMARY KEY (`kupid`),
  UNIQUE KEY `pib` (`pib`),
  UNIQUE KEY `mib` (`mib`),
  KEY `fk1` (`kid`),
  KEY `fk2` (`mid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin2 COLLATE=latin2_croatian_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `kupac`
--

INSERT INTO `kupac` (`kupid`, `naziv`, `pib`, `mib`, `ulicaibroj`, `kid`, `mid`) VALUES
(1, 'FON', '1234', '35566', 'Jove Ilića 154', 1, 3),
(6, 'UNIQA Osiguranje', '9232983', '66666666666', 'Bulevar Milutina Milankovića', 1, 2),
(7, 'Florida Bel', '888898', '38728738', 'Milentija Popovića 9c', 1, 2),
(8, 'Fakultet organizacionih nauka', '123445', '329399', 'Jove Ilića 154', 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `mesto`
--

CREATE TABLE IF NOT EXISTS `mesto` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ptt` int(11) NOT NULL,
  PRIMARY KEY (`mid`),
  UNIQUE KEY `ptt` (`ptt`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin2 COLLATE=latin2_bin AUTO_INCREMENT=4 ;

--
-- Dumping data for table `mesto`
--

INSERT INTO `mesto` (`mid`, `naziv`, `ptt`) VALUES
(2, 'Novi Beograd', 11070),
(3, 'Voždovac', 11010);

-- --------------------------------------------------------

--
-- Table structure for table `prodaja`
--

CREATE TABLE IF NOT EXISTS `prodaja` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `datum` date NOT NULL,
  `vrednost` double NOT NULL,
  `kupid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `fk3` (`kupid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci AUTO_INCREMENT=22 ;

--
-- Dumping data for table `prodaja`
--

INSERT INTO `prodaja` (`pid`, `datum`, `vrednost`, `kupid`) VALUES
(9, '2015-06-17', 150, 1),
(15, '2015-01-15', 1525, 1),
(17, '2015-01-03', 250, 6),
(18, '2014-01-03', 440, 6),
(20, '2015-01-01', 2400, 6);

-- --------------------------------------------------------

--
-- Table structure for table `proizvod`
--

CREATE TABLE IF NOT EXISTS `proizvod` (
  `prid` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `cena` double NOT NULL,
  PRIMARY KEY (`prid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `proizvod`
--

INSERT INTO `proizvod` (`prid`, `naziv`, `cena`) VALUES
(4, 'ASUS MemoPad 7', 150),
(5, 'LC Power Rack 3.5"', 25),
(6, 'Lenovo U410 Ultrabook i7', 800),
(7, 'Digitalna kamera Canon 10 MPix', 80),
(8, 'Sennheiser CX 150', 25);

-- --------------------------------------------------------

--
-- Table structure for table `stavkaprodaje`
--

CREATE TABLE IF NOT EXISTS `stavkaprodaje` (
  `pid` int(11) NOT NULL DEFAULT '0',
  `spid` int(11) NOT NULL AUTO_INCREMENT,
  `kolicina` int(11) NOT NULL,
  `vrednost` double NOT NULL,
  `prid` int(11) DEFAULT NULL,
  PRIMARY KEY (`spid`,`pid`),
  KEY `fk4` (`pid`),
  KEY `fk5` (`prid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci AUTO_INCREMENT=27 ;

--
-- Dumping data for table `stavkaprodaje`
--

INSERT INTO `stavkaprodaje` (`pid`, `spid`, `kolicina`, `vrednost`, `prid`) VALUES
(15, 13, 2, 700, NULL),
(15, 14, 1, 800, 6),
(15, 15, 1, 25, 5),
(17, 18, 10, 250, 5),
(18, 19, 3, 240, 7),
(18, 20, 2, 50, 8),
(18, 21, 1, 150, 4),
(20, 24, 3, 2400, 6);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kontaktosoba`
--
ALTER TABLE `kontaktosoba`
  ADD CONSTRAINT `kontaktosoba_ibfk_1` FOREIGN KEY (`kupid`) REFERENCES `kupac` (`kupid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `kupac`
--
ALTER TABLE `kupac`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`kid`) REFERENCES `komercijalista` (`kid`),
  ADD CONSTRAINT `fk2` FOREIGN KEY (`mid`) REFERENCES `mesto` (`mid`) ON UPDATE CASCADE;

--
-- Constraints for table `prodaja`
--
ALTER TABLE `prodaja`
  ADD CONSTRAINT `fk3` FOREIGN KEY (`kupid`) REFERENCES `kupac` (`kupid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `stavkaprodaje`
--
ALTER TABLE `stavkaprodaje`
  ADD CONSTRAINT `fk4` FOREIGN KEY (`pid`) REFERENCES `prodaja` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk5` FOREIGN KEY (`prid`) REFERENCES `proizvod` (`prid`) ON DELETE SET NULL ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
