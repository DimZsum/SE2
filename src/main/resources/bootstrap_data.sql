INSERT INTO Profile (id, username, hashAlgorithm, passwordHash, salt) VALUES (2, "admin", "SHA512", "JeuPx1++vnOSp/rdDwjnPwNIldN49UhtJEwUcKr3ksTvu/3s6nmnWbh+mkjfsw41w1LE8VJJAukn+LpfnUybzg==", "wwUVuetAfN1dtaXk0zWOMmb1nIw=");
INSERT INTO User (id, firstname, lastname, profile_id) VALUES (1, "Chuck", "Norris", 2);
UPDATE hibernate_sequence SET next_val = 3;