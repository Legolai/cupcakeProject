CREATE SCHEMA cupcakeProjectTestDB;
USE cupcakeProjectTestDB;
CREATE TABLE CupcakeTopping LIKE cupcakeProjectDB.CupcakeTopping;
CREATE TABLE CupcakeBottom LIKE cupcakeProjectDB.CupcakeBottom;
CREATE TABLE `User` LIKE cupcakeProjectDB.User;
CREATE TABLE `Order` LIKE cupcakeProjectDB.Order;
CREATE TABLE OrderDetail LIKE cupcakeProjectDB.OrderDetail;
