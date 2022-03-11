CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `get_leagues` AS
    SELECT 
        `liga`.`idLiga` AS `id`,
        `liga`.`naziv` AS `naziv_lige`,
        `drzava`.`naziv` AS `drzava`
    FROM
        (`liga`
        JOIN `drzava` ON ((`drzava`.`idDrzava` = `liga`.`idDrzava`)))