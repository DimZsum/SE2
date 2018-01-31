INSERT INTO Role (id, name) VALUES(1, "Adminrolle");
INSERT INTO Role_rights (Role_id, rights) VALUES(1, "ADMIN");
INSERT INTO Profile (id, role_id, username, hashAlgorithm, passwordHash, salt) VALUES (2, 1, "admin", "SHA512", "JeuPx1++vnOSp/rdDwjnPwNIldN49UhtJEwUcKr3ksTvu/3s6nmnWbh+mkjfsw41w1LE8VJJAukn+LpfnUybzg==", "wwUVuetAfN1dtaXk0zWOMmb1nIw=");
INSERT INTO User (id, profile_id, firstname, lastname) VALUES (3, 2, "Chuck", "Norris");
INSERT INTO Film (id,name, verfuegbar,fsk) VALUES (1, "test", TRUE, 18);
UPDATE hibernate_sequence SET next_val = 4;
