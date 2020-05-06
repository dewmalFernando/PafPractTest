-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 06, 2020 at 02:31 PM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paf_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `Admin_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Admin_Name` varchar(50) NOT NULL,
  `Admin_Contact` int(11) NOT NULL,
  `Admin_Mail` varchar(50) NOT NULL,
  `Admin_Address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Admin_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ambulance_service`
--

DROP TABLE IF EXISTS `ambulance_service`;
CREATE TABLE IF NOT EXISTS `ambulance_service` (
  `Ambulance_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Amb_Contact_Number` int(11) NOT NULL,
  PRIMARY KEY (`Ambulance_Id`),
  UNIQUE KEY `Amb_Contact_Number` (`Amb_Contact_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
CREATE TABLE IF NOT EXISTS `appointments` (
  `Appointment_Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_Id` int(11) NOT NULL,
  `Doctor_Id` int(11) NOT NULL,
  `Doctor_spec` varchar(50) NOT NULL,
  `Hosp_Id` int(11) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`Appointment_Id`),
  KEY `Hosp_Id` (`Hosp_Id`),
  KEY `User_Id` (`User_Id`),
  KEY `Doctor_Id` (`Doctor_Id`),
  KEY `Doctor_spec` (`Doctor_spec`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`Appointment_Id`, `User_Id`, `Doctor_Id`, `Doctor_spec`, `Hosp_Id`, `Date`) VALUES
(13, 1, 1, 'Dentist', 2, '2020-04-22');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
CREATE TABLE IF NOT EXISTS `doctor` (
  `Doctor_ID` int(11) NOT NULL AUTO_INCREMENT,
  `D_Name` varchar(100) NOT NULL,
  `D_Type` varchar(100) NOT NULL,
  `D_Contact_Number` int(11) NOT NULL,
  `D_Address` varchar(100) NOT NULL,
  `D_Email` varchar(50) NOT NULL,
  `Hospital_ID` int(10) NOT NULL,
  `Availability` char(1) DEFAULT '1',
  PRIMARY KEY (`Doctor_ID`),
  KEY `Hospital_ID` (`Hospital_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`Doctor_ID`, `D_Name`, `D_Type`, `D_Contact_Number`, `D_Address`, `D_Email`, `Hospital_ID`, `Availability`) VALUES
(1, 'Mr.Perera', 'Dentist', 315654565, 'colombo', 'p@gmail.com', 1, '1');

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
CREATE TABLE IF NOT EXISTS `hospital` (
  `H_ID` int(10) NOT NULL AUTO_INCREMENT,
  `H_name` varchar(50) NOT NULL,
  `H_contactNumber` int(10) NOT NULL,
  `H_address` varchar(50) NOT NULL,
  `H_email` varchar(50) NOT NULL,
  PRIMARY KEY (`H_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`H_ID`, `H_name`, `H_contactNumber`, `H_address`, `H_email`) VALUES
(1, 'Nawaloka', 212563214, 'colombo', 'n@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
CREATE TABLE IF NOT EXISTS `payments` (
  `Payment_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Ammount` double NOT NULL,
  `User_Id` int(11) NOT NULL,
  `Doctor_Id` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  PRIMARY KEY (`Payment_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `User_Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_First_Name` varchar(30) NOT NULL,
  `User_Last_Name` varchar(30) NOT NULL,
  `User_Address` varchar(50) NOT NULL,
  `User_Contact_Number` int(10) NOT NULL,
  `User_DOB` date NOT NULL,
  `User_Age` int(11) NOT NULL,
  `User_Email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`User_Id`),
  UNIQUE KEY `Contact_Number` (`User_Contact_Number`),
  UNIQUE KEY `User_Email` (`User_Email`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`User_Id`, `User_First_Name`, `User_Last_Name`, `User_Address`, `User_Contact_Number`, `User_DOB`, `User_Age`, `User_Email`) VALUES
(1, 'kamal', 'perera', 'colombo', 231251256, '1997-05-02', 22, 'k@gmail.com');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
