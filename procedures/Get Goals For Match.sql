CREATE DEFINER=`root`@`localhost` PROCEDURE `get_goals_for_match`(in idUtakmica int)
select * from get_goals_info
where get_goals_info.idUtakmica = idUtakmica
order by minuta asc