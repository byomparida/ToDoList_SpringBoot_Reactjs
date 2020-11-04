/*
SQLyog Enterprise - MySQL GUI v8.18 
MySQL - 8.0.20 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;
CREATE DATABASE deloitte_todo_list;

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
	`user_id` DOUBLE ,
	`user_name` VARCHAR (60),
	`password` VARCHAR (768),
	`created_time` DATETIME ,
	`updated_time` DATETIME 
); 
INSERT INTO `tbl_user` (`user_id`, `user_name`, `password`, `created_time`, `updated_time`) VALUES('6','test','$2a$10$dP8EM2gxnr.A55P6PH91WO7Ywns8tKoTL9cSZ9NHqsw69CCPQqS9q','2020-11-04 21:53:06','2020-11-04 21:53:06');
INSERT INTO `tbl_user` (`user_id`, `user_name`, `password`, `created_time`, `updated_time`) VALUES('7','byom','$2a$10$TsEAeCApAxqULunQDG1vqu4b3cBaROMuYFjIN4qQgM6aMpZfwWn62','2020-11-04 21:53:20','2020-11-04 21:53:20');


DROP TABLE IF EXISTS `tbl_todolist`;
CREATE TABLE `tbl_todolist` (
	`todolist_id` DOUBLE ,
	`user_id` DOUBLE ,
	`todolist_name` VARCHAR (150),
	`todolist_type` VARCHAR (90),
	`created_time` DATETIME ,
	`updated_time` DATETIME 
); 
INSERT INTO `tbl_todolist` (`todolist_id`, `user_id`, `todolist_name`, `todolist_type`, `created_time`, `updated_time`) VALUES('49','6','School','To Collect Kids','2020-11-04 21:53:35','2020-11-04 21:53:35');


