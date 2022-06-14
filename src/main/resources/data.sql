-- PROJECT G4 --
-- H2 2.1.212;


INSERT INTO "PUBLIC"."PROFILES" VALUES
                                    ('PF-5b98064b-34c3-4ca6-b51d-dadf78a0eabf', 'Administrator'),
                                    ('PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', 'Visitor'),
                                    ('PF-75c9f064-d241-4458-af54-418d193783a6', 'Director'),
                                    ('PF-a6c9b600-8241-4db8-9339-4569d8b1b546', 'Project Manager'),
                                    ('PF-a2d374b2-4fac-47bf-8ba9-4e59e2bfee55', 'Product Owner'),
                                    ('PF-0060dd9d-971b-4de5-bcc1-f330bf044438', 'Scrum Master'),
                                    ('PF-4beb5567-4169-4ea7-97f0-7114fea6cd8b', 'User');


INSERT INTO "PUBLIC"."PROJECTS" VALUES
                                    ('A0001', 150000.0, 'It doesn''t matter', 'XPTO, SA', 'Dummy 01', 6, 'Just a dummy project', 2, 'CLOSED', DATE '2026-03-22', DATE '2022-02-25', 'Fixed Cost'),
                                    ('A0002', 550000.0, 'It doesn''t matter', 'XPTO, SA', 'Dummy 02', 6, 'Just a dummy project', 2, 'INCEPTION', DATE '2026-03-22', DATE '2022-02-25', 'Insert typology.'),
                                    ('A0003', 35000.0, 'It doesn''t matter', 'XYZ, Lda', 'Dummy 03', 12, 'Just another dummy project', 4, 'PLANNED', DATE '2026-03-22', DATE '2022-02-25', 'Insert typology.'),
                                    ('A0004', 50000.0, 'It doesn''t matter', 'XYZ, Lda', 'Dummy 04', 12, 'Just another dummy project', 4, 'PLANNED', DATE '2026-03-22', DATE '2022-02-25', 'Insert typology.'),
                                    ('A0005', 350000.0, 'It doesn''t matter', 'XYZ, Lda', 'Dummy 05', 12, 'Just another dummy project', 4, 'PLANNED', DATE '2026-03-22', DATE '2022-02-25', 'Insert typology.'),
                                    ('A0666', 500000.0, 'Hospitality industry', 'Hell, LLC', 'Inevitable nightmare', 15, 'Doomed from the start', 3, 'PLANNED', DATE '2026-03-22', DATE '2022-02-25', 'Insert typology.');


INSERT INTO "PUBLIC"."RESOURCES" VALUES
                                     ('a8b66225-f0f5-4090-a7a7-7c47d238a95f', 35.0, 'tc@mymail.com', DATE '2022-03-25', 20, 'A0001', 'ProjectManager', DATE '2022-02-25'),
                                     ('15e77ea2-2098-4205-95ac-98d794b96d4d', 25.0, 'js@mymail.com', DATE '2022-03-25', 20, 'A0001', 'ProductOwner', DATE '2022-02-23');


INSERT INTO "PUBLIC"."TYPOLOGIES" VALUES
                                      ('TYP-812c1386-42d3-422b-bb6b-5368082c2d69', 'Fixed Cost'),
                                      ('TYP-69222d5f-998c-40d9-82b8-6cb2072797a0', 'Time And Materials');


INSERT INTO "PUBLIC"."USERS" VALUES
                                 (1, 'js@mymail.com', 'Anything', '$2a$12$AbkOQMO3OL3Vo6PVkicR.eqvV9Jj.cp2PlT.ie3u0jeAhPI6Jhlfy', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', U&'Jo\00e3o Silva', FALSE),
                                 (2, 'ms@mymail.com', 'Anything', '$2a$12$9YmHU17a/rE83oFgtG8v9ux3jzJvKtPoA12RgkncHDum5qEGUiavq', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', 'Manel Costa', FALSE),
                                 (3, 'xf@mymail.com', 'Anything', '$2a$12$g1.fDDoe82SPHkDGNpUv7OyeGLioBlxmUI5g3OHdnjvojYnAGKtVK', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', 'Xico Ferreira', FALSE),
                                 (4, 'tc@mymail.com', 'Anything', '$2a$12$I3RzBuEvWOdEnja3nhtz/./zXgA5EN6H/JKtRqppO14fDKE6/0GEW', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', 'Tiago Cancado', FALSE),
                                 (5, 'udu@mymail.com', 'Anything', '$2a$12$fB6vFvnSw6W/N3T8FfqaIuuilIi6mGhdG15tUobjpPQUmoOb7CaWS', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', 'Urbino das Urzes', FALSE),
                                 (6, 'ze@mymail.com', 'Anything', '$2a$12$Gm4z5N9LCN51yamylgksDemT9TUV7W8.tiWWz02b8Tjy1vG/3Ts4e', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', 'Ze da Esquina', FALSE),
                                 (7, 'nel.m@mymail.com', 'Flexible', '$2a$12$wocmfj2OjwnwRrcLD1fBSORbDcjdft29BELPwa5B28zEW2tSUWk4e', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', 'Nel Moleiro', FALSE),
                                 (8, 'zb@mymail.com', 'Flexible', '$2a$12$LqQnMU6PbchIH31EeXyuZO7.kmYRWUotzniiafYJfebtkZ0ECFtGO', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', U&'Z\00e9 do Bento', FALSE),
                                 (9, 'to.f@mymail.com', 'Flexible', '$2a$12$M2Un2W5qswtcQmPueu54R.fhacNiTWfW2hwU2u8Rbrcp.BgEsJZrq', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', U&'T\00f3 Farrulo', FALSE),
                                 (10, 'tdc@mymail.com', 'Not so flexible', '$2a$12$vvfHI3iFIlu3HWrX.Ip44OoE3BE.NCBsDO3c5hPaefXQdWn24Yl5q', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', 'Tino das Cruzes', FALSE),
                                 (11, 'qb@mymail.com', 'Anything', '$2a$12$n1T8m.clUqlCPo.O2WscOe.lCTGSSsmoeIJL1JOexnQz11IOcIwpS', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', 'Quim Barreiros', FALSE),
                                 (12, 'tg@mymail.com', 'Anything', '$2a$12$.CKKgJH.hLbWRlamB/bp2e6ysVVtSX.9MG0gPDjLSZHQHJfnOIYq.', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', 'Tiago Geringonca', FALSE),
                                 (13, 'zm@mymail.com', 'Anything', '$2a$12$FO2gw/dhBhg/jIvf2qlyeuHELBTjGEmp5P43de20hmIaLtCLIhJ5C', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', U&'Z\00e9 Manel', FALSE),
                                 (14, 'as@mymail.com', 'Anything', '$2a$12$lTv.5EBidjwZ6R3DDSrZ2ezEdwfk1MONq7jl/TkKRGOo.gj2LC/8W', 'https://user.jpg', 'PF-e16d6d9b-3fec-4d69-bdf0-6d17d9ecc52d', 'Antonio Silva', FALSE);

INSERT INTO "PUBLIC"."USER_STORIES" VALUES
                                        ('US-67ffdb47-6c7c-46fe-b265-6c6c44345635', 'Dummy 01', 0, '-1', 1, 'A0001', '-1', 'PLANNED'),
                                        ('US-eeefa7b5-4017-424c-a764-35013b17f084', 'Dummy 02', 0, '-1', 2, 'A0001', '-1', 'PLANNED'),
                                        ('US-d335dc39-4d28-427e-914e-6f969feca54f', 'Dummy 03', 0, '-1', 3, 'A0001', '-1', 'PLANNED'),
                                        ('US-719ee8c7-2c8e-4dc8-95ed-daf1a1f7a0e6', 'Dummy 04', 0, '-1', 4, 'A0001', '-1', 'PLANNED'),
                                        ('US-f4c04073-0920-4fae-aa7f-c22235ad8ed7', 'Dummy 05', 0, '-1', 5, 'A0001', '-1', 'PLANNED'),
                                        ('US-9c6c0161-27d6-4eca-b243-882a6bcd723e', 'Dummy 06', 0, '-1', 6, 'A0001', '-1', 'PLANNED'),
                                        ('US-c0945bdc-f759-4914-b89c-3eff2a29ca87', 'Dummy 07', 0, '-1', 7, 'A0001', '-1', 'PLANNED'),
                                        ('US-536d528d-83ee-4538-b898-2c8098e9532b', 'Dummy 08', 0, '-1', 8, 'A0001', '-1', 'PLANNED'),
                                        ('US-f09f9e0c-841e-431c-abbb-1e7a6fb9967b', 'Dummy 09', 0, '-1', 9, 'A0001', '-1', 'PLANNED'),
                                        ('US-2d383b84-ff73-474b-bdf8-eb454efff523', 'Dummy 10', 0, '-1', 10, 'A0001', '-1', 'PLANNED'),
                                        ('US-f24f6819-4123-47eb-8785-47da92b265fb', 'Dummy 11', 0, '-1', 11, 'A0001', '-1', 'PLANNED'),
                                        ('US-a181792f-705e-4768-ab38-e97343c79727', 'Dummy 12', 0, '-1', 12, 'A0001', '-1', 'PLANNED'),
                                        ('US-6b2cd82c-affb-4f6b-9570-b9ba767d326d', 'Dummy 13', 0, '-1', 13, 'A0001', '-1', 'PLANNED'),
                                        ('US-888b99a4-18d2-479c-a381-331627a6ba4a', 'Dummy 14', 0, '-1', 14, 'A0001', '-1', 'PLANNED'),
                                        ('US-8a95364e-5a2c-49e9-9d89-8fc452519069', 'Dummy 15', 0, '-1', 15, 'A0001', '-1', 'PLANNED');
