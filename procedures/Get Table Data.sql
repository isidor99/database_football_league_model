CREATE DEFINER=`root`@`localhost` PROCEDURE `get_table_data`(in idLiga int, in idSezona int)
select 
	pozicija_na_tabeli.idLiga AS idLiga,
	pozicija_na_tabeli.idSezona AS idSezona,
	pozicija_na_tabeli.idKlub AS idKlub,
    klub.ime AS klub,
	(select count(*) from gol
        join utakmica on utakmica.idUtakmica = gol.idUtakmica
		where gol.idKlub = pozicija_na_tabeli.idKlub and 
				utakmica.idLiga = idLiga and 
                utakmica.idSezona = idSezona) as brojPostignutihGolova,
	(select count(*) from gol
		join utakmica on utakmica.idDomaci = pozicija_na_tabeli.idKlub
				or utakmica.idGosti = pozicija_na_tabeli.idKlub
		where gol.idUtakmica = utakmica.idUtakmica and 
				gol.idKlub <> pozicija_na_tabeli.idKlub and 
                utakmica.idLiga = idLiga and 
                utakmica.idSezona = idSezona) as brojPrimljenihGolova,
	(select count(*) from get_matches
		where get_matches.datumVrijeme < NOW() and get_matches.idLiga = idLiga and get_matches.idSezona = idSezona and
				((get_matches.idDomaci = pozicija_na_tabeli.idKlub
                    and get_matches.golova_domaci > get_matches.golova_gosti)
				or (get_matches.idGosti = pozicija_na_tabeli.idKlub
                    and get_matches.golova_gosti > get_matches.golova_domaci))) as brojPobjeda,
	(select count(*) from get_matches
		where get_matches.datumVrijeme < NOW() and get_matches.idLiga = idLiga and get_matches.idSezona = idSezona and 
				((get_matches.idDomaci = pozicija_na_tabeli.idKlub
                    and get_matches.golova_gosti > get_matches.golova_domaci)
				or (get_matches.idGosti = pozicija_na_tabeli.idKlub
                    and get_matches.golova_domaci > get_matches.golova_gosti))) as brojPoraza,
	(select count(*) from get_matches
		where get_matches.datumVrijeme < NOW() and get_matches.idLiga = idLiga and get_matches.idSezona = idSezona and
				((get_matches.idDomaci = pozicija_na_tabeli.idKlub
                    and get_matches.golova_gosti = get_matches.golova_domaci)
				or (get_matches.idGosti = pozicija_na_tabeli.idKlub
                    and get_matches.golova_domaci = get_matches.golova_gosti))) AS brojRemija,
	(select (brojPobjeda * 3) + brojRemija) AS brojBodova
from pozicija_na_tabeli
join klub on klub.idKlub = pozicija_na_tabeli.idKlub
where pozicija_na_tabeli.idLiga = idLiga and
		pozicija_na_tabeli.idSezona = idSezona
order by brojBodova desc