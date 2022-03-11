CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `get_matches` AS
    SELECT 
        `utakmica`.`idUtakmica` AS `id`,
        `liga`.`idLiga` AS `idLiga`,
        `sezona`.`idSezona` AS `idSezona`,
        `klubd`.`idKlub` AS `idDomaci`,
        `klubg`.`idKlub` AS `idGosti`,
        `utakmica`.`datumVrijeme` AS `datumVrijeme`,
        `utakmica`.`kolo` AS `kolo`,
        `klubd`.`ime` AS `domaci`,
        (SELECT 
                COUNT(0)
            FROM
                `gol`
            WHERE
                ((`gol`.`idUtakmica` = `utakmica`.`idUtakmica`)
                    AND (`gol`.`idKlub` = `klubd`.`idKlub`))) AS `golova_domaci`,
        `klubg`.`ime` AS `gosti`,
        (SELECT 
                COUNT(0)
            FROM
                `gol`
            WHERE
                ((`gol`.`idUtakmica` = `utakmica`.`idUtakmica`)
                    AND (`gol`.`idKlub` = `klubg`.`idKlub`))) AS `golova_gosti`,
        `liga`.`naziv` AS `liga`,
        `sezona`.`oznakaSezone` AS `sezona`
    FROM
        ((((`utakmica`
        JOIN `klub` `klubd` ON ((`klubd`.`idKlub` = `utakmica`.`idDomaci`)))
        JOIN `klub` `klubg` ON ((`klubg`.`idKlub` = `utakmica`.`idGosti`)))
        JOIN `liga` ON ((`liga`.`idLiga` = `utakmica`.`idLiga`)))
        JOIN `sezona` ON ((`sezona`.`idSezona` = `utakmica`.`idSezona`)))