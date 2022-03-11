-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fudbal
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fudbal
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fudbal` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `fudbal` ;

-- -----------------------------------------------------
-- Table `fudbal`.`Drzava`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Drzava` (
  `idDrzava` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idDrzava`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Grad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Grad` (
  `idGrad` INT NOT NULL AUTO_INCREMENT,
  `idDrzava` INT NOT NULL,
  `naziv` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idGrad`),
  INDEX `fk_Grad_Drzava_idx` (`idDrzava` ASC) VISIBLE,
  CONSTRAINT `fk_Grad_Drzava`
    FOREIGN KEY (`idDrzava`)
    REFERENCES `fudbal`.`Drzava` (`idDrzava`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Osoba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Osoba` (
  `idOsoba` INT NOT NULL AUTO_INCREMENT,
  `ime` VARCHAR(20) NOT NULL,
  `prezime` VARCHAR(20) NOT NULL,
  `datumRodjenja` DATE NOT NULL,
  `idGradRodjenja` INT NOT NULL,
  PRIMARY KEY (`idOsoba`),
  INDEX `fk_Osoba_Grad1_idx` (`idGradRodjenja` ASC) VISIBLE,
  CONSTRAINT `fk_Osoba_Grad1`
    FOREIGN KEY (`idGradRodjenja`)
    REFERENCES `fudbal`.`Grad` (`idGrad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Fudbaler`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Fudbaler` (
  `idOsoba` INT NOT NULL,
  `visina` SMALLINT NOT NULL,
  `tezina` FLOAT NOT NULL,
  `noga` VARCHAR(10) NOT NULL,
  `specijalnost` VARCHAR(40) NULL,
  PRIMARY KEY (`idOsoba`),
  CONSTRAINT `fk_Fudbaler_Osoba1`
    FOREIGN KEY (`idOsoba`)
    REFERENCES `fudbal`.`Osoba` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Trener`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Trener` (
  `idOsoba` INT NOT NULL,
  `stilIgre` VARCHAR(20) NULL,
  `datumPocetkaKarijere` DATE NULL,
  PRIMARY KEY (`idOsoba`),
  CONSTRAINT `fk_Trener_Osoba1`
    FOREIGN KEY (`idOsoba`)
    REFERENCES `fudbal`.`Osoba` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Sudija`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Sudija` (
  `idOsoba` INT NOT NULL,
  `datumIzbora` DATE NULL,
  `karakter` VARCHAR(45) NULL,
  PRIMARY KEY (`idOsoba`),
  CONSTRAINT `fk_Sudija_Osoba1`
    FOREIGN KEY (`idOsoba`)
    REFERENCES `fudbal`.`Osoba` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Pozicija`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Pozicija` (
  `idPozicija` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idPozicija`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Stadion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Stadion` (
  `idStadion` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  `kapacitet` INT NOT NULL,
  PRIMARY KEY (`idStadion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Klub`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Klub` (
  `idKlub` INT NOT NULL AUTO_INCREMENT,
  `ime` VARCHAR(45) NOT NULL,
  `grb` BLOB NULL,
  `idStadion` INT NOT NULL,
  `idGrad` INT NOT NULL,
  PRIMARY KEY (`idKlub`),
  INDEX `fk_Klub_Stadion1_idx` (`idStadion` ASC) VISIBLE,
  INDEX `fk_Klub_Grad1_idx` (`idGrad` ASC) VISIBLE,
  CONSTRAINT `fk_Klub_Stadion1`
    FOREIGN KEY (`idStadion`)
    REFERENCES `fudbal`.`Stadion` (`idStadion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Klub_Grad1`
    FOREIGN KEY (`idGrad`)
    REFERENCES `fudbal`.`Grad` (`idGrad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Postava`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Postava` (
  `idPostava` INT NOT NULL,
  `formacija` VARCHAR(10) NOT NULL,
  `idKlub` INT NOT NULL,
  PRIMARY KEY (`idPostava`),
  INDEX `fk_Postava_Klub1_idx` (`idKlub` ASC) VISIBLE,
  CONSTRAINT `fk_Postava_Klub1`
    FOREIGN KEY (`idKlub`)
    REFERENCES `fudbal`.`Klub` (`idKlub`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Igra_na_poziciji`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Igra_na_poziciji` (
  `idOsoba` INT NOT NULL,
  `idPozicija` INT NOT NULL,
  `idPostava` INT NOT NULL,
  INDEX `fk_Fudbaler_has_Pozicija_Fudbaler1_idx` (`idOsoba` ASC) VISIBLE,
  PRIMARY KEY (`idOsoba`, `idPozicija`, `idPostava`),
  INDEX `fk_Igra_na_poziciji_Pozicija1_idx` (`idPozicija` ASC) VISIBLE,
  INDEX `fk_Igra_na_poziciji_Postava1_idx` (`idPostava` ASC) VISIBLE,
  CONSTRAINT `fk_Fudbaler_has_Pozicija_Fudbaler1`
    FOREIGN KEY (`idOsoba`)
    REFERENCES `fudbal`.`Fudbaler` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Igra_na_poziciji_Pozicija1`
    FOREIGN KEY (`idPozicija`)
    REFERENCES `fudbal`.`Pozicija` (`idPozicija`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Igra_na_poziciji_Postava1`
    FOREIGN KEY (`idPostava`)
    REFERENCES `fudbal`.`Postava` (`idPostava`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Sezona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Sezona` (
  `idSezona` INT NOT NULL AUTO_INCREMENT,
  `oznakaSezone` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`idSezona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Liga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Liga` (
  `idLiga` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  `idDrzava` INT NOT NULL,
  PRIMARY KEY (`idLiga`),
  INDEX `fk_Liga_Drzava1_idx` (`idDrzava` ASC) VISIBLE,
  CONSTRAINT `fk_Liga_Drzava1`
    FOREIGN KEY (`idDrzava`)
    REFERENCES `fudbal`.`Drzava` (`idDrzava`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Utakmica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Utakmica` (
  `idUtakmica` INT NOT NULL AUTO_INCREMENT,
  `datumVrijeme` DATETIME NOT NULL,
  `kolo` SMALLINT NOT NULL,
  `idSezona` INT NOT NULL,
  `idLiga` INT NOT NULL,
  `idDomaci` INT NOT NULL,
  `idGosti` INT NOT NULL,
  `idPostavaDomaci` INT NULL,
  `idPostavaGosti` INT NULL,
  PRIMARY KEY (`idUtakmica`),
  INDEX `fk_Utakmica_Sezona1_idx` (`idSezona` ASC) VISIBLE,
  INDEX `fk_Utakmica_Liga1_idx` (`idLiga` ASC) VISIBLE,
  INDEX `fk_Utakmica_Klub1_idx` (`idDomaci` ASC) VISIBLE,
  INDEX `fk_Utakmica_Klub2_idx` (`idGosti` ASC) VISIBLE,
  INDEX `fk_Utakmica_Postava1_idx` (`idPostavaDomaci` ASC) VISIBLE,
  INDEX `fk_Utakmica_Postava2_idx` (`idPostavaGosti` ASC) VISIBLE,
  CONSTRAINT `fk_Utakmica_Sezona1`
    FOREIGN KEY (`idSezona`)
    REFERENCES `fudbal`.`Sezona` (`idSezona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utakmica_Liga1`
    FOREIGN KEY (`idLiga`)
    REFERENCES `fudbal`.`Liga` (`idLiga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utakmica_Klub1`
    FOREIGN KEY (`idDomaci`)
    REFERENCES `fudbal`.`Klub` (`idKlub`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utakmica_Klub2`
    FOREIGN KEY (`idGosti`)
    REFERENCES `fudbal`.`Klub` (`idKlub`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utakmica_Postava1`
    FOREIGN KEY (`idPostavaDomaci`)
    REFERENCES `fudbal`.`Postava` (`idPostava`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utakmica_Postava2`
    FOREIGN KEY (`idPostavaGosti`)
    REFERENCES `fudbal`.`Postava` (`idPostava`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Sudi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Sudi` (
  `idUtakmica` INT NOT NULL,
  `idOsoba` INT NOT NULL,
  `uloga` VARCHAR(20) NOT NULL,
  `plata` DOUBLE NOT NULL,
  PRIMARY KEY (`idUtakmica`, `idOsoba`),
  INDEX `fk_Utakmica_has_Sudija_Sudija1_idx` (`idOsoba` ASC) VISIBLE,
  INDEX `fk_Utakmica_has_Sudija_Utakmica1_idx` (`idUtakmica` ASC) VISIBLE,
  CONSTRAINT `fk_Utakmica_has_Sudija_Utakmica1`
    FOREIGN KEY (`idUtakmica`)
    REFERENCES `fudbal`.`Utakmica` (`idUtakmica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utakmica_has_Sudija_Sudija1`
    FOREIGN KEY (`idOsoba`)
    REFERENCES `fudbal`.`Sudija` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Tabela`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Tabela` (
  `idLiga` INT NOT NULL,
  `idSezona` INT NOT NULL,
  PRIMARY KEY (`idLiga`, `idSezona`),
  INDEX `fk_Liga_has_Sezona_Liga1_idx` (`idLiga` ASC) VISIBLE,
  INDEX `fk_Tabela_Sezona1_idx` (`idSezona` ASC) VISIBLE,
  CONSTRAINT `fk_Liga_has_Sezona_Liga1`
    FOREIGN KEY (`idLiga`)
    REFERENCES `fudbal`.`Liga` (`idLiga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tabela_Sezona1`
    FOREIGN KEY (`idSezona`)
    REFERENCES `fudbal`.`Sezona` (`idSezona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Pozicija_na_tabeli`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Pozicija_na_tabeli` (
  `idLiga` INT NOT NULL,
  `idSezona` INT NOT NULL,
  `idKlub` INT NOT NULL,
  PRIMARY KEY (`idLiga`, `idSezona`, `idKlub`),
  INDEX `fk_Tabela_has_Klub_Klub1_idx` (`idKlub` ASC) VISIBLE,
  INDEX `fk_Tabela_has_Klub_Tabela1_idx` (`idLiga` ASC, `idSezona` ASC) VISIBLE,
  CONSTRAINT `fk_Tabela_has_Klub_Tabela1`
    FOREIGN KEY (`idLiga`)
    REFERENCES `fudbal`.`Tabela` (`idLiga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tabela_has_Klub_Klub1`
    FOREIGN KEY (`idKlub`)
    REFERENCES `fudbal`.`Klub` (`idKlub`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Transfer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Transfer` (
  `idOsoba` INT NOT NULL,
  `idKlubTrenutni` INT NOT NULL,
  `idKlubNovi` INT NOT NULL,
  `datum` DATE NOT NULL,
  `iznos` DECIMAL NOT NULL,
  `tip` TINYINT NOT NULL,
  INDEX `fk_Transfer_Fudbaler1_idx` (`idOsoba` ASC) VISIBLE,
  PRIMARY KEY (`idOsoba`, `idKlubTrenutni`, `idKlubNovi`, `datum`),
  INDEX `fk_Transfer_Klub1_idx` (`idKlubTrenutni` ASC) VISIBLE,
  INDEX `fk_Transfer_Klub2_idx` (`idKlubNovi` ASC) VISIBLE,
  CONSTRAINT `fk_Transfer_Fudbaler1`
    FOREIGN KEY (`idOsoba`)
    REFERENCES `fudbal`.`Fudbaler` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transfer_Klub1`
    FOREIGN KEY (`idKlubTrenutni`)
    REFERENCES `fudbal`.`Klub` (`idKlub`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transfer_Klub2`
    FOREIGN KEY (`idKlubNovi`)
    REFERENCES `fudbal`.`Klub` (`idKlub`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Trenira_u_klubu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Trenira_u_klubu` (
  `idKlub` INT NOT NULL,
  `idOsoba` INT NOT NULL,
  `datumPotpisaUgovora` DATE NOT NULL,
  `datumIstekaUgovora` VARCHAR(45) NOT NULL,
  `datumRaskidaUgovora` DATE NULL,
  `tip` VARCHAR(20) NOT NULL,
  `plata` DECIMAL NOT NULL,
  PRIMARY KEY (`idKlub`, `idOsoba`, `datumPotpisaUgovora`),
  INDEX `fk_Klub_has_Trener_Trener1_idx` (`idOsoba` ASC) VISIBLE,
  INDEX `fk_Klub_has_Trener_Klub1_idx` (`idKlub` ASC) VISIBLE,
  CONSTRAINT `fk_Klub_has_Trener_Klub1`
    FOREIGN KEY (`idKlub`)
    REFERENCES `fudbal`.`Klub` (`idKlub`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Klub_has_Trener_Trener1`
    FOREIGN KEY (`idOsoba`)
    REFERENCES `fudbal`.`Trener` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Gol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Gol` (
  `idGol` INT NOT NULL AUTO_INCREMENT,
  `idOsoba` INT NOT NULL,
  `idUtakmica` INT NOT NULL,
  `idKlub` INT NOT NULL,
  `autogol` TINYINT NOT NULL,
  `minuta` TINYINT NOT NULL,
  PRIMARY KEY (`idGol`),
  INDEX `fk_Fudbaler_has_Utakmica_Utakmica1_idx` (`idUtakmica` ASC) VISIBLE,
  INDEX `fk_Fudbaler_has_Utakmica_Fudbaler1_idx` (`idOsoba` ASC) VISIBLE,
  INDEX `fk_Gol_Klub1_idx` (`idKlub` ASC) VISIBLE,
  CONSTRAINT `fk_Fudbaler_has_Utakmica_Fudbaler1`
    FOREIGN KEY (`idOsoba`)
    REFERENCES `fudbal`.`Fudbaler` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Fudbaler_has_Utakmica_Utakmica1`
    FOREIGN KEY (`idUtakmica`)
    REFERENCES `fudbal`.`Utakmica` (`idUtakmica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Gol_Klub1`
    FOREIGN KEY (`idKlub`)
    REFERENCES `fudbal`.`Klub` (`idKlub`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Karton`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Karton` (
  `idOsoba` INT NOT NULL,
  `idUtakmica` INT NOT NULL,
  `idSudija` INT NOT NULL,
  `karton` BINARY NOT NULL,
  `iskljucenje` TINYINT NOT NULL,
  `minuta` INT NOT NULL,
  PRIMARY KEY (`idOsoba`, `idUtakmica`, `idSudija`, `karton`, `iskljucenje`),
  INDEX `fk_Fudbaler_has_Utakmica_Utakmica2_idx` (`idUtakmica` ASC) VISIBLE,
  INDEX `fk_Fudbaler_has_Utakmica_Fudbaler2_idx` (`idOsoba` ASC) VISIBLE,
  INDEX `fk_Karton_Sudija1_idx` (`idSudija` ASC) VISIBLE,
  CONSTRAINT `fk_Fudbaler_has_Utakmica_Fudbaler2`
    FOREIGN KEY (`idOsoba`)
    REFERENCES `fudbal`.`Fudbaler` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Fudbaler_has_Utakmica_Utakmica2`
    FOREIGN KEY (`idUtakmica`)
    REFERENCES `fudbal`.`Utakmica` (`idUtakmica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Karton_Sudija1`
    FOREIGN KEY (`idSudija`)
    REFERENCES `fudbal`.`Sudija` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Izmjena`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Izmjena` (
  `idIzlaziOsoba` INT NOT NULL,
  `idUlaziOsoba` INT NOT NULL,
  `idUtakmica` INT NOT NULL,
  `minuta` INT NOT NULL,
  PRIMARY KEY (`idIzlaziOsoba`, `idUlaziOsoba`, `idUtakmica`),
  INDEX `fk_Izmjena_Fudbaler2_idx` (`idUlaziOsoba` ASC) VISIBLE,
  INDEX `fk_Izmjena_Utakmica1_idx` (`idUtakmica` ASC) VISIBLE,
  CONSTRAINT `fk_Izmjena_Fudbaler1`
    FOREIGN KEY (`idIzlaziOsoba`)
    REFERENCES `fudbal`.`Fudbaler` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Izmjena_Fudbaler2`
    FOREIGN KEY (`idUlaziOsoba`)
    REFERENCES `fudbal`.`Fudbaler` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Izmjena_Utakmica1`
    FOREIGN KEY (`idUtakmica`)
    REFERENCES `fudbal`.`Utakmica` (`idUtakmica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Administrator` (
  `korisnickoIme` VARCHAR(20) NOT NULL,
  `lozinka` VARCHAR(45) NULL,
  PRIMARY KEY (`korisnickoIme`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fudbal`.`Fudbaler_igra_za_klub`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fudbal`.`Fudbaler_igra_za_klub` (
  `idOsoba` INT NOT NULL,
  `idKlub` INT NOT NULL,
  `datumPotpisaUgovora` DATE NOT NULL,
  `datumIstekaUgovora` DATE NOT NULL,
  `datumRaskidaUgovora` DATE NULL,
  `plata` DECIMAL NOT NULL,
  `brojNaDresu` SMALLINT NOT NULL,
  PRIMARY KEY (`idOsoba`, `idKlub`, `datumPotpisaUgovora`),
  INDEX `fk_Fudbaler_has_Klub_Klub1_idx` (`idKlub` ASC) VISIBLE,
  INDEX `fk_Fudbaler_has_Klub_Fudbaler1_idx` (`idOsoba` ASC) VISIBLE,
  CONSTRAINT `fk_Fudbaler_has_Klub_Fudbaler1`
    FOREIGN KEY (`idOsoba`)
    REFERENCES `fudbal`.`Fudbaler` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Fudbaler_has_Klub_Klub1`
    FOREIGN KEY (`idKlub`)
    REFERENCES `fudbal`.`Klub` (`idKlub`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
