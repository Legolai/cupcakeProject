CREATE SCHEMA IF NOT EXISTS cupcakeProjectTestDB;
USE cupcakeProjectTestDB;

DROP TABLE IF EXISTS CupcakeTopping;
CREATE TABLE CupcakeTopping LIKE cupcakeProjectDB.CupcakeTopping;
DROP TABLE IF EXISTS CupcakeBottom;
CREATE TABLE CupcakeBottom LIKE cupcakeProjectDB.CupcakeBottom;
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` LIKE cupcakeProjectDB.User;
DROP TABLE IF EXISTS `Order`;
CREATE TABLE `Order` LIKE cupcakeProjectDB.Order;
DROP TABLE IF EXISTS OrderDetail;
CREATE TABLE OrderDetail LIKE cupcakeProjectDB.OrderDetail;
