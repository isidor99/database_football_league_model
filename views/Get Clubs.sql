CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `get_clubs` AS
    SELECT 
        `klub`.`idKlub` AS `id`,
        `klub`.`ime` AS `ime`,
        `klub`.`grb` AS `grb`,
        `stadion`.`naziv` AS `naziv_stadiona`,
        `stadion`.`kapacitet` AS `kapacitet_stadiona`,
        `grad`.`naziv` AS `grad`,
        `drzava`.`naziv` AS `drzava`
    FROM
        (((`klub`
        JOIN `stadion` ON ((`stadion`.`idStadion` = `klub`.`idStadion`)))
        JOIN `grad` ON ((`grad`.`idGrad` = `klub`.`idGrad`)))
        JOIN `drzava` ON ((`grad`.`idDrzava` = `drzava`.`idDrzava`)))