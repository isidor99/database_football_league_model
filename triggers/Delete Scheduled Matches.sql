DROP TRIGGER IF EXISTS delete_scheduled_matches;
CREATE TRIGGER delete_scheduled_matches AFTER DELETE ON pozicija_na_tabeli
FOR EACH ROW
DELETE FROM utakmica 
WHERE (utakmica.idDomaci = old.idKlub or utakmica.idGosti = old.idKlub) and
		utakmica.idLiga = old.idLiga and 
        utakmica.idSezona = old.idSezona;
