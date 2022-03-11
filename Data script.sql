-- Drzava --
insert into drzava (naziv) values ('England'), ('France'), ('Spain'), ('Germany'), ('Italy');
insert into drzava (naziv) values ('Senegal'), ('Uruguay'), ('Portugal'), ('Egypt'), ('South Korea');
insert into drzava (naziv) values ('Serbia'), ('Russia'), ('Brazil'), ('Nigeria');

-- Grad --
insert into grad (idDrzava, naziv) values (1, 'London'), (1, 'Manchester'), (1, 'Liverpool'), (1, 'Portsmouth'), (1, 'Stockport'), (1, 'Walthamstow');
insert into grad (idDrzava, naziv) values (2, 'Paris'), (2, 'Lyon'), (2, 'Nice');
insert into grad (idDrzava, naziv) values (3, 'Madrid'), (3, 'Barcelona'), (3, 'Valencia'), (3, 'Foios');
insert into grad (idDrzava, naziv) values (4, 'Berlin'), (4, 'Frankfurt'), (4, 'Munich'), (4, 'Aachen');
insert into grad (idDrzava, naziv) values (5, 'Rome'), (5, 'Naples'), (5, 'Milan');
insert into grad (idDrzava, naziv) values (6, 'Sedhiou');
insert into grad (idDrzava, naziv) values (7, 'Salto');
insert into grad (idDrzava, naziv) values (8, 'Maia');
insert into grad (idDrzava, naziv) values (9, 'Nagrig');
insert into grad (idDrzava, naziv) values (10, 'Chuncheon-si');

-- Admin --
insert into administrator (korisnickoIme, lozinka) values ('admin', 'admin123');

-- Stadion i Klub --
insert into stadion (naziv, kapacitet) values ('Old Trafford', 74140);
insert into klub (ime, grb, idStadion, idGrad) values ('Manchester United F.C.', null, 1, 2);

insert into stadion (naziv, kapacitet) values ('Stamford Bridge', 41837);
insert into klub (ime, grb, idStadion, idGrad) values ('Chelsea F.C.', null, 2, 1);

insert into stadion (naziv, kapacitet) values ('Anfield', 61905);
insert into klub (ime, grb, idStadion, idGrad) values ('Liverpool F.C.', null, 3, 3);

insert into stadion (naziv, kapacitet) values ('White Hart Lane', 36284);
insert into klub (ime, grb, idStadion, idGrad) values ('Tottenham Hotspur F.C.', null, 4, 1);

insert into stadion (naziv, kapacitet) values ('Etihad', 55017);
insert into klub (ime, grb, idStadion, idGrad) values ('Manchester City F.C.', null, 5, 2);

insert into stadion (naziv, kapacitet) values ('Emirates', 60704);
insert into klub (ime, grb, idStadion, idGrad) values ('Arsenal F.C.', null, 6, 1);

insert into stadion (naziv, kapacitet) values ('Santiago Bernabeu', 81044);
insert into klub (ime, grb, idStadion, idGrad) values ('Real Madrid CF', null, 7, 10);

insert into stadion (naziv, kapacitet) values ('Camp Nou', 99354);
insert into klub (ime, grb, idStadion, idGrad) values ('FC Barcelona', null, 8, 11);

insert into stadion (naziv, kapacitet) values ('Wanda Metropolitano', 68456);
insert into klub (ime, grb, idStadion, idGrad) values ('Atletico Madrid', null, 9, 10);

insert into stadion (naziv, kapacitet) values ('Mestalla', 48600);
insert into klub (ime, grb, idStadion, idGrad) values ('Valencia CF', null, 10, 12);

-- Sezone -- 
insert into sezona (oznakaSezone) values ('20/21'), ('19/20'), ('18/19');

-- Lige --
insert into liga (naziv, idDrzava) values ('Premier League', 1), ('LaLiga', 3);

-- Tabela --
insert into tabela (idLiga, idSezona) values (1, 1);
insert into tabela (idLiga, idSezona) values (1, 2);
insert into tabela (idLiga, idSezona) values (2, 2);

-- Pozicija na tabeli -
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (1, 1, 1);
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (1, 1, 2);
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (1, 1, 3);
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (1, 1, 4);
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (1, 1, 5);
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (1, 1, 6);

insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (1, 2, 1);
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (1, 2, 2);
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (1, 2, 3);
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (1, 2, 4);
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (1, 2, 5);
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (1, 2, 6);

insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (2, 2, 7);
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (2, 2, 8);
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (2, 2, 9);
insert into pozicija_na_tabeli (idLiga, idSezona, idKlub) values (2, 2, 10);

-- Utakmice --
-- liga 1 sezona 1
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-01 17:00:00', 1, 1, 1, 6, 4);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-01 16:00:00', 1, 1, 1, 3, 5);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-01 17:30:00', 1, 1, 1, 1, 2);

insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-04 10:45:00', 2, 1, 1, 6, 5);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-05 14:00:00', 2, 1, 1, 4, 2);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-05 21:30:00', 2, 1, 1, 3, 1);

insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-08 12:00:00', 3, 1, 1, 6, 1);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-10 17:45:00', 3, 1, 1, 2, 3);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-09 19:30:00', 3, 1, 1, 5, 4);

insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-12 12:30:00', 4, 1, 1, 6, 2);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-12 16:45:00', 4, 1, 1, 5, 1);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-02 21:30:00', 4, 1, 1, 4, 3);

insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-18 12:00:00', 5, 1, 1, 6, 3);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-18 12:45:00', 5, 1, 1, 1, 4);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2021-08-08 14:30:00', 5, 1, 1, 2, 5);

-- liga 2 sezona 2
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2020-10-01 17:00:00', 1, 2, 2, 8, 10);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2020-10-01 22:00:00', 1, 2, 2, 7, 9);

insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2020-10-04 10:45:00', 2, 2, 2, 8, 7);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2020-10-05 14:00:00', 2, 2, 2, 9, 10);

insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2020-10-08 12:00:00', 3, 2, 2, 8, 9);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2020-10-10 17:45:00', 3, 2, 2, 10, 7);

insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2020-10-12 12:30:00', 4, 2, 2, 7, 8);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2020-10-12 16:45:00', 4, 2, 2, 10, 9);

insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2020-10-18 12:00:00', 5, 2, 2, 10, 8);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2020-10-18 12:45:00', 5, 2, 2, 9, 7);

insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2020-10-26 12:00:00', 6, 2, 2, 9, 8);
insert into utakmica (datumVrijeme, kolo, idSezona, idLiga, idDomaci, idGosti) values ('2020-10-27 12:45:00', 6, 2, 2, 7, 10);

-- Osobe --
insert into osoba (ime, prezime, datumRodjenja, idGradRodjenja) values ('Mason', 'Mount', '1999-01-10', 4);
insert into osoba (ime, prezime, datumRodjenja, idGradRodjenja) values ('Kai', 'Havertz', '1999-06-11', 17);
insert into osoba (ime, prezime, datumRodjenja, idGradRodjenja) values ('Sadio', 'Mane', '1992-03-10', 21);
insert into osoba (ime, prezime, datumRodjenja, idGradRodjenja) values ('Edinson', 'Cavani', '1987-02-14', 22);
insert into osoba (ime, prezime, datumRodjenja, idGradRodjenja) values ('Bruno', 'Fernandes', '1994-09-08', 23);
insert into osoba (ime, prezime, datumRodjenja, idGradRodjenja) values ('Phil', 'Foden', '2000-05-28', 5);
insert into osoba (ime, prezime, datumRodjenja, idGradRodjenja) values ('Ferran', 'Torres', '2000-02-29', 13);
insert into osoba (ime, prezime, datumRodjenja, idGradRodjenja) values ('Harry', 'Kane', '1993-07-28', 6);
insert into osoba (ime, prezime, datumRodjenja, idGradRodjenja) values ('Mohamed', 'Salah', '1992-06-15', 24);
insert into osoba (ime, prezime, datumRodjenja, idGradRodjenja) values ('Son', 'Heung-min', '1992-07-08', 25);
insert into osoba (ime, prezime, datumRodjenja, idGradRodjenja) values ('Alexandre', 'Lacazette', '1991-05-26', 8);
insert into osoba (ime, prezime, datumRodjenja, idGradRodjenja) values ('Bukayo', 'Saka', '2001-09-05', 1);

-- Fudbaleri --
insert into fudbaler (idOsoba, visina, tezina, noga, specijalnost) values (1, 178, 70, 'right', 'Free kicks');
insert into fudbaler (idOsoba, visina, tezina, noga, specijalnost) values (2, 189, 82, 'left', 'Shoot');
insert into fudbaler (idOsoba, visina, tezina, noga, specijalnost) values (3, 175, 76, 'right', 'One-to-one');
insert into fudbaler (idOsoba, visina, tezina, noga, specijalnost) values (4, 184, 71, 'right', 'Finish');
insert into fudbaler (idOsoba, visina, tezina, noga, specijalnost) values (5, 179, 69, 'right', 'Assists');
insert into fudbaler (idOsoba, visina, tezina, noga, specijalnost) values (6, 171, 70, 'left', null);
insert into fudbaler (idOsoba, visina, tezina, noga, specijalnost) values (7, 184, 77, 'right', 'Crossing');
insert into fudbaler (idOsoba, visina, tezina, noga, specijalnost) values (8, 188, 86, 'right', 'Finishing');
insert into fudbaler (idOsoba, visina, tezina, noga, specijalnost) values (9, 175, 71, 'left', 'Dribbling');
insert into fudbaler (idOsoba, visina, tezina, noga, specijalnost) values (10, 180, 77, 'both', 'One-to-one');
insert into fudbaler (idOsoba, visina, tezina, noga, specijalnost) values (11, 176, 75, 'right', 'Dribbling');
insert into fudbaler (idOsoba, visina, tezina, noga, specijalnost) values (12, 178, 65, 'left', 'Assists');

-- Gol --
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (1, 3, 2, false, 15);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (1, 3, 2, false, 38);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (1, 5, 2, false, 35);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (1, 8, 2, false, 8);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (1, 10, 2, false, 90);

insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (2, 3, 2, false, 1);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (2, 5, 2, false, 42);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (2, 8, 2, false, 55);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (2, 8, 2, false, 68);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (2, 15, 2, false, 72);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (2, 15, 2, false, 77);

insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (3, 2, 3, false, 4);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (3, 2, 3, false, 40);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (3, 2, 3, false, 58);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (3, 6, 3, false, 7);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (3, 8, 3, false, 15);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (3, 8, 3, false, 32);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (3, 12, 3, false, 3);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (3, 13, 3, false, 49);

insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (4, 3, 1, false, 10);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (4, 6, 1, false, 12);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (4, 6, 1, false, 65);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (4, 6, 1, false, 78);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (4, 7, 1, false, 43);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (4, 11, 1, false, 40);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (4, 11, 1, false, 70);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (4, 14, 1, false, 88);

insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (5, 7, 1, false, 13);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (5, 11, 1, false, 86);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (5, 14, 1, false, 18);

insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (6, 2, 5, false, 3);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (6, 4, 5, false, 33);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (6, 9, 5, false, 75);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (6, 11, 5, false, 52);

insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (7, 2, 5, false, 16);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (7, 4, 5, false, 20);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (7, 4, 5, false, 75);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (7, 9, 5, false, 44);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (7, 11, 5, false, 81);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (7, 15, 5, false, 17);

insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (8, 1, 4, false, 4);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (8, 1, 4, false, 45);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (8, 5, 4, false, 19);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (8, 9, 4, false, 41);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (8, 12, 4, false, 47);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (8, 12, 4, false, 78);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (8, 14, 4, false, 79);

insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (9, 2, 3, false, 42);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (9, 2, 3, false, 45);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (9, 6, 3, false, 20);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (9, 8, 2, true, 40);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (9, 12, 3, false, 23);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (9, 12, 3, false, 59);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (9, 13, 3, false, 64);

insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (10, 1, 4, false, 6);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (10, 1, 4, false, 27);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (10, 5, 4, false, 32);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (10, 5, 2, true, 84);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (10, 9, 4, false, 79);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (10, 12, 4, false, 56);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (10, 14, 4, false, 58);

insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (11, 1, 6, false, 18);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (11, 1, 6, false, 73);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (11, 4, 6, false, 52);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (11, 7, 6, false, 14);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (11, 10, 6, false, 18);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (11, 10, 6, false, 88);

insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (12, 4, 6, true, 80);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (12, 7, 6, false, 14);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (12, 13, 6, false, 76);
insert into gol (idOsoba, idUtakmica, idKlub, autogol, minuta) values (12, 13, 6, false, 90);

