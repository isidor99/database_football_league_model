CREATE DEFINER=`root`@`localhost` PROCEDURE `get_matches_for_league_in_season`(in idLiga int, in idSezona int)
select * from get_matches
where get_matches.idLiga = idLiga and
		get_matches.idSezona = idSezona
order by datumVrijeme asc, kolo asc