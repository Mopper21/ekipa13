-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bolniska
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bolniska
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bolniska` DEFAULT CHARACTER SET utf8 ;
USE `bolniska` ;

-- -----------------------------------------------------
-- Table `bolniska`.`Pacient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bolniska`.`Pacient` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(45) NULL,
  `Priimek` VARCHAR(45) NULL,
  `Datum_rojstva` DATE NULL,
  `Naslov` VARCHAR(45) NULL,
  `Tel_st` INT NULL,
  `Email` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bolniska`.`Zdravnik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bolniska`.`Zdravnik` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(45) NULL,
  `Priimek` VARCHAR(45) NULL,
  `Specializacija` VARCHAR(45) NULL,
  `Telefon` INT NULL,
  `Email` VARCHAR(45) NULL,
  `Zdravnikcol` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `Zdravnikcol_UNIQUE` (`Zdravnikcol` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bolniska`.`Napotnice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bolniska`.`Napotnice` (
  `ID` INT NOT NULL,
  `Datum_izdaje` DATE NULL,
  `Datum_pregleda` DATE NULL,
  `Pacient_ID` INT NOT NULL,
  `Zdravnik_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Napotnice_Pacient_idx` (`Pacient_ID` ASC) VISIBLE,
  INDEX `fk_Napotnice_Zdravnik1_idx` (`Zdravnik_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Napotnice_Pacient`
    FOREIGN KEY (`Pacient_ID`)
    REFERENCES `bolniska`.`Pacient` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Napotnice_Zdravnik1`
    FOREIGN KEY (`Zdravnik_ID`)
    REFERENCES `bolniska`.`Zdravnik` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
