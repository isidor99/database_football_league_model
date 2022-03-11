CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `get_goals_info` AS
    SELECT 
        `gol`.`idGol` AS `idGol`,
        `gol`.`idOsoba` AS `idOsoba`,
        `gol`.`idUtakmica` AS `idUtakmica`,
        `gol`.`idKlub` AS `idKlub`,
        `osoba`.`ime` AS `ime_fudbalera`,
        `osoba`.`prezime` AS `prezime_fudbalera`,
        `klub`.`ime` AS `klub`,
        `gol`.`autogol` AS `autogol`,
        `gol`.`minuta` AS `minuta`
    FROM
        ((`gol`
        JOIN `osoba` ON ((`osoba`.`idOsoba` = `gol`.`idOsoba`)))
        JOIN `klub` ON ((`klub`.`idKlub` = `gol`.`idKlub`)))