CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `get_position_on_table` AS
    SELECT 
        `pozicija_na_tabeli`.`idLiga` AS `idLiga`,
        `pozicija_na_tabeli`.`idSezona` AS `idSezona`,
        `pozicija_na_tabeli`.`idKlub` AS `idKlub`,
        `klub`.`ime` AS `klub`,
        (SELECT 
                COUNT(0)
            FROM
                `gol`
            WHERE
                (`gol`.`idKlub` = `pozicija_na_tabeli`.`idKlub`)) AS `brojPostignutihGolova`,
        (SELECT 
                COUNT(0)
            FROM
                (`gol`
                JOIN `utakmica` ON (((`utakmica`.`idDomaci` = `pozicija_na_tabeli`.`idKlub`)
                    OR (`utakmica`.`idGosti` = `pozicija_na_tabeli`.`idKlub`))))
            WHERE
                ((`gol`.`idUtakmica` = `utakmica`.`idUtakmica`)
                    AND (`gol`.`idKlub` <> `pozicija_na_tabeli`.`idKlub`))) AS `brojPrimljenihGolova`,
        (SELECT 
                COUNT(0)
            FROM
                `get_matches`
            WHERE
                ((`get_matches`.`datumVrijeme` < NOW())
                    AND (((`get_matches`.`idDomaci` = `pozicija_na_tabeli`.`idKlub`)
                    AND (`get_matches`.`golova_domaci` > `get_matches`.`golova_gosti`))
                    OR ((`get_matches`.`idGosti` = `pozicija_na_tabeli`.`idKlub`)
                    AND (`get_matches`.`golova_gosti` > `get_matches`.`golova_domaci`))))) AS `brojPobjeda`,
        (SELECT 
                COUNT(0)
            FROM
                `get_matches`
            WHERE
                ((`get_matches`.`datumVrijeme` < NOW())
                    AND (((`get_matches`.`idDomaci` = `pozicija_na_tabeli`.`idKlub`)
                    AND (`get_matches`.`golova_gosti` > `get_matches`.`golova_domaci`))
                    OR ((`get_matches`.`idGosti` = `pozicija_na_tabeli`.`idKlub`)
                    AND (`get_matches`.`golova_domaci` > `get_matches`.`golova_gosti`))))) AS `brojPoraza`,
        (SELECT 
                COUNT(0)
            FROM
                `get_matches`
            WHERE
                ((`get_matches`.`datumVrijeme` < NOW())
                    AND (((`get_matches`.`idDomaci` = `pozicija_na_tabeli`.`idKlub`)
                    AND (`get_matches`.`golova_gosti` = `get_matches`.`golova_domaci`))
                    OR ((`get_matches`.`idGosti` = `pozicija_na_tabeli`.`idKlub`)
                    AND (`get_matches`.`golova_domaci` = `get_matches`.`golova_gosti`))))) AS `brojRemija`,
        (SELECT ((`brojPobjeda` * 3) + `brojRemija`)) AS `brojBodova`
    FROM
        (`pozicija_na_tabeli`
        JOIN `klub` ON ((`klub`.`idKlub` = `pozicija_na_tabeli`.`idKlub`)))