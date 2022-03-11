CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `get_cities` AS
    SELECT 
        `grad`.`idGrad` AS `idGrad`,
        `grad`.`naziv` AS `grad`,
        `drzava`.`idDrzava` AS `idDrzava`,
        `drzava`.`naziv` AS `drzava`
    FROM
        (`grad`
        JOIN `drzava` ON ((`drzava`.`idDrzava` = `grad`.`idDrzava`)))