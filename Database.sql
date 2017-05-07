CREATE SCHEMA IF NOT EXISTS fogdb;
USE fogdb;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS adminuser;
DROP TABLE IF EXISTS userdata;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS material (
    mno INT NOT NULL,
    type VARCHAR(35) NOT NULL,
    price DOUBLE NOT NULL,
    name VARCHAR(55) NOT NULL,
    qoh INT,
    size INT NOT NULL,
    PRIMARY KEY (mno)
);

CREATE TABLE adminuser (
    empno VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    empname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    salt VARCHAR(45) NOT NULL,
    userstring VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    uid INT NOT NULL UNIQUE AUTO_INCREMENT,
    uname VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(55) NOT NULL,
    salt VARCHAR(45) NOT NULL,
    userstring VARCHAR(45) NOT NULL,
    carport TEXT,
    PRIMARY KEY (uid)
);

CREATE TABLE IF NOT EXISTS userdata (
    uid INT,
    datatype VARCHAR(255),
    Value VARCHAR(255),
    FOREIGN KEY (uid)
        REFERENCES users (uid)
);

CREATE TABLE IF NOT EXISTS orders (
    ono INT NOT NULL UNIQUE,
    uid INT NOT NULL,
    ostatus INT NOT NULL,
    carport TEXT NOT NULL,
    price DOUBLE NOT NULL,
    PRIMARY KEY (ono),
    FOREIGN KEY (uid)
        REFERENCES users (uid)
);