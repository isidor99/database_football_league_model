CREATE DEFINER=`root`@`localhost` PROCEDURE `get_num_of_played_matches`(in idKlub int, in idLiga int, in idSezona int)
select count(*) from get_matches 
where (idDomaci = idKlub or idGosti = idKlub) and 
		get_matches.idLiga = idLiga and 
        get_matches.idSezona = idSezona and 
        datumVrijeme < now()