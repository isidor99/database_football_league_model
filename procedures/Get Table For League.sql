CREATE DEFINER=`root`@`localhost` PROCEDURE `get_table_for_league`(in idLiga int)
select * from get_tables
where get_tables.idLiga = idLiga