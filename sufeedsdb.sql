-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2024 at 07:50 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sufeedsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_classes`
--

CREATE TABLE `tbl_classes` (
  `class_id` int(11) NOT NULL,
  `student_id` varchar(50) DEFAULT NULL,
  `class_name` varchar(100) NOT NULL,
  `semester` varchar(20) NOT NULL,
  `year` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_classes`
--

INSERT INTO `tbl_classes` (`class_id`, `student_id`, `class_name`, `semester`, `year`) VALUES
(1, '190004', 'Linear Algebra', '2', 2),
(2, '190004', 'Discrete', '1', 1),
(3, '190004', 'Linear Algebra', '2', 1),
(4, '190004', 'Linear Algebra', '2', 3),
(5, '190527', 'Statistics 1', '1', 4),
(6, '190527', 'Art', '1', 3),
(7, '190004', 'German', '1', 2),
(8, '190004', 'hHistroy', '1', 1),
(9, '190004', 'OOP', '1', 1),
(10, '190004', 'Geography', '2', 1),
(11, '190004', 'Communication Skills II', '2', 2),
(12, '190006', 'Communication Skills II', '2', 2),
(13, '190004', 'Computer Networks', '2', 4),
(14, '190004', 'Calculus', '1', 2),
(15, '190004', 'Ethics', '2', 3),
(16, '190004', 'Linear Algebra', '1', 3),
(17, '190527', 'Linear Algebra', '2', 3),
(18, '179001', 'OOP II', '2', 2),
(19, '190004', 'Advanced Networking', '2', 4),
(20, '190527', 'Maths', '1', 5),
(21, '180010', 'Machine Learning', '2', 2),
(22, '151009', 'Computer Fundamentals', '1', 1),
(23, '180000', 'POM', '1', 1),
(24, '100011', 'Business Mathematics', '1', 1),
(25, '190004', 'Linear Algebra', '2', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_feedback`
--

CREATE TABLE `tbl_feedback` (
  `feedback_id` int(11) NOT NULL,
  `student_id` varchar(50) DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  `feedback_content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_feedback`
--

INSERT INTO `tbl_feedback` (`feedback_id`, `student_id`, `topic_id`, `feedback_content`) VALUES
(1, '190004', 1, 'The topic was very informative'),
(2, '190004', 2, 'New territory'),
(3, '190004', 3, 'so hard'),
(5, '190527', 5, 'I felt very overwhelmed'),
(8, '190006', 7, 'I really like Dr. Elizabeth Gitonga, she was very interactive.'),
(9, '190004', 8, 'P.Neri was very nice!!! '),
(10, '190004', 9, 'I loved Mrs Mary and Mr Orwa in both these classes'),
(12, '179001', 12, 'Got a lot of knowledge from this'),
(13, '190004', 13, 'VERRYYY AMAZINGGGG'),
(14, '190527', 14, 'It was ni'),
(15, '180010', 15, 'This was cool'),
(16, '151009', 16, 'The Lecturers are very interesting and l really like the labs they are very hands on.'),
(17, '180000', 17, 'BUZZZZ'),
(18, '100011', 18, 'Maths is my favourite subject really'),
(19, '190004', 19, 'This was cool');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_students`
--

CREATE TABLE `tbl_students` (
  `student_id` varchar(50) NOT NULL,
  `student_name` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `school` varchar(100) NOT NULL,
  `course` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_students`
--

INSERT INTO `tbl_students` (`student_id`, `student_name`, `password`, `school`, `course`) VALUES
('100011', 'Harry Potter', 'Stone', 'Strathmore Business School (SBS)', 'Bachelor of Commerce (BCOM)'),
('151009', 'Leon Gantt', '2222', 'Strathmore Institute of Mathematical Sciences (SIMS)', 'Bachelor of Business Science in Financial Engineering (BBSFENG)'),
('179001', 'Jack Black', '7722', 'Strathmore Institute of Management and Technology (SI)', 'Diploma in Business Information Technology (DBIT)'),
('180000', 'NULL', 'HAPPY', 'Strathmore Institute of Management and Technology (SI)', 'Bachelors in Informatics and Computer Science (BISC)'),
('180010', 'Kevin Feige', 'Marvel', 'School of Tourism and Hospitality (STH)', 'Bachelor of Science in Tourism Management (BTM)'),
('190004', 'Mukundi', '4563', 'Strathmore Institute of Management and Technology (SI)', 'Bachelors in Informatics and Computer Science (BISC)'),
('190006', 'Jerry West', 'Jerry@', 'Strathmore Business School (SBS)', 'Bachelor of Commerce (BCOM)'),
('190527', 'Happy', '1234', 'Strathmore Institute of Mathematical Sciences (SIMS)', 'Bachelor of Business Science in Financial Engineering (BBSFENG)'),
('2222', 'mee', '1111', 'Strathmore Institute of Management and Technology (SI)', 'Bachelors in Informatics and Computer Science (BISC)'),
('6969', 'Jason', '69696', 'School of Computing and Engineering Sciences (SCES)', 'Bachelors in Informatics and Computer Science (BISC)');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_topics`
--

CREATE TABLE `tbl_topics` (
  `topic_id` int(11) NOT NULL,
  `class_id` int(11) DEFAULT NULL,
  `week` int(11) NOT NULL,
  `description` text NOT NULL,
  `topic_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_topics`
--

INSERT INTO `tbl_topics` (`topic_id`, `class_id`, `week`, `description`, `topic_name`) VALUES
(1, 2, 1, 'Union, intersection', 'Set Theory'),
(2, 3, 2, 'Matrix row operation', 'Row Echelon'),
(3, 4, 4, 'Methods', 'Gaussian Elimination'),
(4, 5, 3, 'Sets', 'Probability'),
(5, 6, 5, 'Picaso\'s history', 'Picasso'),
(6, 11, 1, 'This went well!', 'Citations'),
(7, 12, 2, 'Survey, Question, Read, Recite, Review', 'SQ3R methods'),
(8, 13, 2, 'Application Layer,Session Layer,Presentation Layer , Transport Layer, Network Layer, Physical Layer ', 'OSI Model'),
(9, 14, 5, 'Continous and Non-continous Limits', 'Limits'),
(10, 15, 5, 'PAAAAAAA', 'Philosophy'),
(11, 16, 1, 'Row operation', 'Gaussian Elimination'),
(12, 18, 1, 'Differiantiate between an object and a class', 'Object and Classes'),
(13, 19, 1, 'l LOVED IT', 'Network Routing'),
(14, 20, 7, 'Angles', 'Geometry'),
(15, 21, 4, '...', 'Learning in machines'),
(16, 22, 6, 'Device Lock, ManTraps', 'Security'),
(17, 23, 11, 'I don\'t even know what she is talking about', 'Tranformational Leadership'),
(18, 24, 2, 'Limits, Functions', 'Calculus'),
(19, 25, 2, 'Gaussian Jordan', 'Matrices');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_classes`
--
ALTER TABLE `tbl_classes`
  ADD PRIMARY KEY (`class_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `tbl_feedback`
--
ALTER TABLE `tbl_feedback`
  ADD PRIMARY KEY (`feedback_id`),
  ADD KEY `student_id` (`student_id`),
  ADD KEY `topic_id` (`topic_id`);

--
-- Indexes for table `tbl_students`
--
ALTER TABLE `tbl_students`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `tbl_topics`
--
ALTER TABLE `tbl_topics`
  ADD PRIMARY KEY (`topic_id`),
  ADD KEY `class_id` (`class_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_classes`
--
ALTER TABLE `tbl_classes`
  MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `tbl_feedback`
--
ALTER TABLE `tbl_feedback`
  MODIFY `feedback_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `tbl_topics`
--
ALTER TABLE `tbl_topics`
  MODIFY `topic_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_classes`
--
ALTER TABLE `tbl_classes`
  ADD CONSTRAINT `tbl_classes_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `tbl_students` (`student_id`);

--
-- Constraints for table `tbl_feedback`
--
ALTER TABLE `tbl_feedback`
  ADD CONSTRAINT `tbl_feedback_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `tbl_students` (`student_id`),
  ADD CONSTRAINT `tbl_feedback_ibfk_2` FOREIGN KEY (`topic_id`) REFERENCES `tbl_topics` (`topic_id`);

--
-- Constraints for table `tbl_topics`
--
ALTER TABLE `tbl_topics`
  ADD CONSTRAINT `tbl_topics_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `tbl_classes` (`class_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
