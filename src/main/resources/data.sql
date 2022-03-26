use Brewingbuddy_DB;

insert into hop (id, alfa_acid, name) VALUES (1, 12.5, 'Zula'),
                                             (2, 3.0, 'Lubelski'),
                                             (3, 6.0, 'Sybilla'),
                                             (4, 7.8,'Oktawia');

insert into malt (id, ebc, extraction_rate, name) VALUES (1, 4.0, 81.0, 'Pilzneński'),
                                                         (2, 6.0, 80.0, 'Pale Ale'),
                                                         (3, 4.0, 85.0, 'Pszeniczny');

insert into yeast(id, form, laboratory, name) VALUES (1, 'dry', 'Safale', 'US-05'),
                                                     (2, 'liquid', 'Fermentis', 'Białe walonki');

insert into additional_ingredient(id, name) VALUES (1, 'aromat cytrynowy'),
                                                   (2, 'ziarna kawy');
