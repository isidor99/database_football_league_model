CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `get_tables` AS
    SELECT 
        `liga`.`idLiga` AS `idLiga`,
        `liga`.`naziv` AS `liga`,
        `sezona`.`idSezona` AS `idSezona`,
        `sezona`.`oznakaSezone` AS `sezona`,
        `drzava`.`idDrzava` AS `idDrzava`,
        `drzava`.`naziv` AS `drzava`
    FROM
        (((`tabela`
        JOIN `liga` ON ((`tabela`.`idLiga` = `liga`.`idLiga`)))
        JOIN `sezona` ON ((`sezona`.`idSezona` = `tabela`.`idSezona`)))
        JOIN `drzava` ON ((`drzava`.`idDrzava` = `liga`.`idDrzava`)))
    ORDER BY `drzava`.`idDrzava` , `sezona`.`oznakaSezone` DESC