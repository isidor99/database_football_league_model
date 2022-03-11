CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `get_footballers` AS
    SELECT 
        `osoba`.`idOsoba` AS `id`,
        `osoba`.`ime` AS `ime`,
        `osoba`.`prezime` AS `prezima`,
        `osoba`.`datumRodjenja` AS `datumRodjenja`,
        `grad`.`naziv` AS `mjestoRodjenja`,
        `drzava`.`naziv` AS `drzava`,
        `fudbaler`.`visina` AS `visina`,
        `fudbaler`.`tezina` AS `tezina`,
        `fudbaler`.`noga` AS `noga`,
        `fudbaler`.`specijalnost` AS `specijalnost`
    FROM
        (((`fudbaler`
        JOIN `osoba` ON ((`osoba`.`idOsoba` = `fudbaler`.`idOsoba`)))
        JOIN `grad` ON ((`grad`.`idGrad` = `osoba`.`idGradRodjenja`)))
        JOIN `drzava` ON ((`drzava`.`idDrzava` = `grad`.`idDrzava`)))