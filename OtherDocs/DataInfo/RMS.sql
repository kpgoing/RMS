-- MySQL Script generated by MySQL Workbench
-- Tue Nov 15 15:33:26 2016
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema RMS
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema RMS
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `RMS` DEFAULT CHARACTER SET utf8 ;
USE `RMS` ;

-- -----------------------------------------------------
-- Table `RMS`.`Teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RMS`.`Teacher` (
  `id_teacher` INT NOT NULL AUTO_INCREMENT COMMENT '	',
  `account` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `birthday` DATE NULL DEFAULT '1970-07-01',
  `education_background` VARCHAR(45) NULL,
  `college` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `ID` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `gender` TINYINT(1) NOT NULL,
  `work_place` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NULL,
  `avatar_url` VARCHAR(45) NULL,
  `param1` VARCHAR(45) NULL,
  `param2` VARCHAR(45) NULL,
  PRIMARY KEY (`id_teacher`),
  UNIQUE INDEX `userName_UNIQUE` (`account` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RMS`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RMS`.`Admin` (
  `id_admin` INT NOT NULL AUTO_INCREMENT,
  `account` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `param1` VARCHAR(45) NULL,
  `param2` VARCHAR(45) NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE INDEX `adminName_UNIQUE` (`account` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RMS`.`Paper`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RMS`.`Paper` (
  `id_paper` INT NOT NULL AUTO_INCREMENT,
  `id_teacher` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `release_date` DATE NOT NULL,
  `writer` VARCHAR(45) NOT NULL,
  `publish_date` DATE NOT NULL,
  `publish_place` VARCHAR(45) NOT NULL,
  `key_word` VARCHAR(45) NOT NULL,
  `abstract_content` LONGTEXT NOT NULL,
  `content` VARCHAR(100) NOT NULL,
  `param1` VARCHAR(45) NULL,
  `param2` VARCHAR(45) NULL,
  PRIMARY KEY (`id_paper`),
  INDEX `fk_Paper_Teacher_idx` (`id_teacher` ASC),
  CONSTRAINT `fk_Paper_Teacher`
    FOREIGN KEY (`id_teacher`)
    REFERENCES `RMS`.`Teacher` (`id_teacher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RMS`.`Project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RMS`.`Project` (
  `id_project` INT NOT NULL AUTO_INCREMENT,
  `id_teacher` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `source` VARCHAR(45) NOT NULL,
  `project_time` DATE NOT NULL,
  `master` VARCHAR(45) NOT NULL,
  `funds` DECIMAL(20,2) NOT NULL,
  `publish_time` DATE NOT NULL,
  `introduction` LONGTEXT NOT NULL,
  `param1` VARCHAR(45) NULL,
  `param2` VARCHAR(45) NULL,
  PRIMARY KEY (`id_project`),
  UNIQUE INDEX `idProject_UNIQUE` (`id_project` ASC),
  INDEX `fk_Project_Teacher1_idx` (`id_teacher` ASC),
  CONSTRAINT `fk_Project_Teacher1`
    FOREIGN KEY (`id_teacher`)
    REFERENCES `RMS`.`Teacher` (`id_teacher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RMS`.`check_status_of_teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RMS`.`check_status_of_teacher` (
  `id_check_status_of_teacher` INT NOT NULL AUTO_INCREMENT,
  `id_teacher` INT NOT NULL,
  `check_status` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_check_status_of_teacher`),
  INDEX `fk_check_status_of_teacher_Teacher1_idx` (`id_teacher` ASC),
  CONSTRAINT `fk_check_status_of_teacher_Teacher1`
    FOREIGN KEY (`id_teacher`)
    REFERENCES `RMS`.`Teacher` (`id_teacher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RMS`.`dynamic_state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RMS`.`dynamic_state` (
  `id_dynamic_state` INT NOT NULL AUTO_INCREMENT,
  `id_teacher` INT NOT NULL,
  `id_content` INT NOT NULL,
  `kind` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `teacher_name` VARCHAR(45) NOT NULL,
  `publish_date` DATETIME NOT NULL,
  PRIMARY KEY (`id_dynamic_state`),
  INDEX `fk_DynamicState_Teacher1_idx` (`id_teacher` ASC),
  CONSTRAINT `fk_DynamicState_Teacher1`
    FOREIGN KEY (`id_teacher`)
    REFERENCES `RMS`.`Teacher` (`id_teacher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
