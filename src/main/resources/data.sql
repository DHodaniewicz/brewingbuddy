use Brewingbuddy_DB;

insert into hop (id, alfa_acid, name) VALUES (1, 12.5, 'Zula'),
                                             (2, 3.0, 'Lubelski'),
                                             (3, 6.0, 'Sybilla'),
                                             (4, 7.8,'Oktawia');

insert into malt (id, ebc, extraction_rate, name, type) VALUES (1, 4.0, 81.0, 'Pilzneński', 'standard'),
                                                         (2, 6.0, 80.0, 'Pale Ale', 'standard'),
                                                         (3, 4.0, 85.0, 'Pszeniczny', 'standard');

insert into yeast(id, form, laboratory, name) VALUES (1, 'dry', 'Safale', 'US-05'),
                                                     (2, 'liquid', 'Fermentis', 'Białe walonki');

insert into additional_ingredient(id, name) VALUES (1, 'aromat cytrynowy'),
                                                   (2, 'ziarna kawy');


insert into beer_style (id, beer_style) VALUES (1, 'Barley wine'), (2, 'Bock'), (3, 'Gose'),
                                               (4, 'Pils'), (5, 'Pale Ale'), (6, 'Porter'),
                                               (7, 'Stout');

INSERT INTO `role` (`id`, `name`) VALUES (NULL, 'ROLE_USER');

INSERT INTO users (email, enabled, username)  VALUES ('d.hodaniewicz@gmail.com', 1, 'Danny');

INSERT INTO users (email, enabled, username)  VALUES ('d.hodaniewicz@wp.pl', 1, 'Danny_2');


