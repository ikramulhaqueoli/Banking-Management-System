-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 29, 2019 at 07:06 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `b02`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `userid` varchar(10) NOT NULL,
  `account_number` varchar(8) NOT NULL,
  `balance` double DEFAULT '0',
  `account_type` varchar(14) NOT NULL,
  `pin` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`userid`, `account_number`, `balance`, `account_type`, `pin`) VALUES
('C-D000003', '00000003', 200, 'Overdraft', '12345'),
('C-D000004', '00000004', 2000, 'Overdraft', '12345'),
('C-D000008', '00000008', 0, 'Overdraft', '12345'),
('C-S000005', '00000005', 0, 'Savings', NULL),
('C-S000006', '00000006', 0, 'Savings', '12345'),
('C-S000007', '00000007', 0, 'Savings', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `userid` varchar(10) NOT NULL,
  `designation` varchar(25) NOT NULL,
  `joindate` date NOT NULL,
  `salary` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`userid`, `designation`, `joindate`, `salary`) VALUES
('E-A000001', 'Banker', '2019-04-29', 79000),
('E-B000008', 'Banker', '2019-04-29', 79000),
('E-B000009', 'Banker', '2019-04-29', 79000),
('E-B000010', 'Banker', '2019-04-29', 79000),
('E-B000011', 'Banker', '2019-04-29', 79000),
('E-B000012', 'Banker', '2019-04-29', 79000),
('E-B000013', 'Banker', '2019-04-29', 79000);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `userid` varchar(10) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`userid`, `password`) VALUES
('C-D000003', 'Amioli!25'),
('C-D000004', 'Amioli!25'),
('C-D000008', 'Amioli!25'),
('C-S000005', 'Amioli!25'),
('C-S000006', 'Amioli!25'),
('C-S000007', '@Rahat12'),
('E-A000001', 'Admin@01'),
('E-B000008', 'Amioli!25'),
('E-B000009', '@Afif123'),
('E-B000010', '@Ferdous1'),
('E-B000011', '@Sultan1'),
('E-B000012', '@Zohir12'),
('E-B000013', '@Efaz100');

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `source_id` varchar(10) DEFAULT NULL,
  `target_id` varchar(10) NOT NULL,
  `Message` longtext NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `notification_type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`source_id`, `target_id`, `Message`, `time`, `notification_type`) VALUES
(NULL, '', '', '0000-00-00 00:00:00', 'Personal_message'),
(NULL, '', '', '2019-04-28 00:03:44', 'Message_personal');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(15) UNSIGNED NOT NULL,
  `target_account` varchar(8) NOT NULL COMMENT 'Applicable for Deposit & P2P Transactions',
  `transfer_type` varchar(20) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `amount` double NOT NULL,
  `executing_officer_id` varchar(10) DEFAULT NULL COMMENT 'Applicable for deposit or withdrawal',
  `source_account` varchar(8) DEFAULT NULL COMMENT 'Applicable for Withdrawal & P2P transactions'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `target_account`, `transfer_type`, `time`, `amount`, `executing_officer_id`, `source_account`) VALUES
(26, '00000003', 'P2P_Transfer', '2019-04-28 05:52:18', 5, 'N/A', '00000001'),
(27, '00000003', 'Deposit', '2019-04-28 14:53:49', 1000, 'E-B000005', 'N/A'),
(28, '00000003', 'Deposit', '2019-04-28 14:57:07', 100, 'E-B000005', 'N/A'),
(29, '00000003', 'Deposit', '2019-04-28 14:58:57', -100, 'E-B000005', 'N/A'),
(30, 'N/A', 'Withdraw', '2019-04-28 15:14:07', 500, 'E-B000005', '00000003'),
(31, 'N/A', 'Withdraw', '2019-04-28 15:14:22', 100, 'E-B000005', '00000003'),
(32, 'N/A', 'Withdraw', '2019-04-28 15:18:01', 100, 'E-B000005', '00000003'),
(33, 'N/A', 'Withdraw', '2019-04-28 18:02:36', 100, 'E-B000005', '00000003'),
(34, '00000004', 'Deposit', '2019-04-28 18:03:04', 2000, 'E-B000005', 'N/A');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userid` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `nid` varchar(17) NOT NULL,
  `birth_date` date NOT NULL,
  `Gender` varchar(8) NOT NULL,
  `email` varchar(35) NOT NULL,
  `phone` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `name`, `nid`, `birth_date`, `Gender`, `email`, `phone`) VALUES
('C-D000003', 'Ikramul Oli', '7227402106299', '1996-04-25', 'Male', 'ikramuloligo@gmail.com', '01714347754'),
('C-D000004', 'Efaz Mahmud', '1998722740011', '2008-12-10', 'Male', 'efaz1995@gmail.com', '01521414786'),
('C-D000008', 'Mahmudul Hasan Arif', '7227401236541', '1998-10-21', 'Male', 'mahmudularif@gmail.com', '01711223355'),
('C-S000005', 'Nusrat Moon', '7447445425544', '2000-12-01', 'Female', 'nusratmoonplay@gmail.com', '01797029218'),
('C-S000006', 'Md Ariful Islam', '7227406522', '1995-09-23', 'Male', 'arif@gmail.com', '01685254565'),
('C-S000007', 'Rahat Talukder', '1002003001', '1997-01-01', 'Male', 'rahat@gmail.com', '01646346356'),
('E-A000001', 'Amader Sir', '7227102123456', '1990-01-01', 'Male', 'amadersir@amaderbank.com', '01711223344'),
('E-B000008', 'Samawat Ullah', '1236541236', '1996-04-21', 'Male', 'samawat@aiub.edu', '01714526341'),
('E-B000009', 'Afif Shan', '1002003005', '1990-01-01', 'Male', 'afifshan@gmail.com', '01732345678'),
('E-B000010', 'Ferdous Jahan', '1002003006', '1997-09-18', 'Female', 'ferdousjahan@gmail.com', '01683566767'),
('E-B000011', 'Sultanul Alam', '1002003014', '1984-05-14', 'Male', 'sultanulalam@gmail.com', '01683555776'),
('E-B000012', 'Zohir Abbas', '1002003011', '1998-02-09', 'Male', 'zohirabbas@gmail.com', '01791308693'),
('E-B000013', 'Efaz Ahmed', '1002003009', '1994-11-25', 'Male', 'efazahmed809@gmail.com', '01684540188');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD UNIQUE KEY `User Id Must be Unique` (`userid`),
  ADD UNIQUE KEY `Account_number must be unique` (`account_number`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD UNIQUE KEY `Error: User Id Ambiguous` (`userid`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`),
  ADD UNIQUE KEY `Error: NID Already Registered` (`nid`) USING BTREE,
  ADD UNIQUE KEY `Error: Phone Already Registered` (`phone`) USING BTREE,
  ADD UNIQUE KEY `Error: User Id Ambiguous` (`userid`) USING BTREE,
  ADD UNIQUE KEY `Error: Email Already Registered` (`email`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(15) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
