INSERT INTO roles (id_role, role) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id_role, role) VALUES (2, 'ROLE_ADMIN');

INSERT INTO body_type (id_bt) VALUES (1);
INSERT INTO body_type (id_bt) VALUES (2);
INSERT INTO body_type (id_bt) VALUES (3);
INSERT INTO body_type (id_bt) VALUES (4);

INSERT INTO body_type_translations (id_bt_translations,language,description,id_bt) VALUES (1,'en-GB','none',1);
INSERT INTO body_type_translations (id_bt_translations,language,description,id_bt) VALUES (2,'en-GB','ectomorph',2);
INSERT INTO body_type_translations (id_bt_translations,language,description,id_bt) VALUES (3,'en-GB','mesomorph',3);
INSERT INTO body_type_translations (id_bt_translations,language,description,id_bt) VALUES (4,'en-GB','endomorph',4);
INSERT INTO body_type_translations (id_bt_translations,language,description,id_bt) VALUES (5,'pl-PL','nieokreslono',1);
INSERT INTO body_type_translations (id_bt_translations,language,description,id_bt) VALUES (6,'pl-PL','ektomorficzny',2);
INSERT INTO body_type_translations (id_bt_translations,language,description,id_bt) VALUES (7,'pl-PL','mezomorficzny',3);
INSERT INTO body_type_translations (id_bt_translations,language,description,id_bt) VALUES (8,'pl-PL','endomorficzny',4);

INSERT INTO frequency_of_activity (id_foa) VALUES (1);
INSERT INTO frequency_of_activity (id_foa) VALUES (2);
INSERT INTO frequency_of_activity (id_foa) VALUES (3);
INSERT INTO frequency_of_activity (id_foa) VALUES (4);
INSERT INTO frequency_of_activity (id_foa) VALUES (5);
INSERT INTO frequency_of_activity (id_foa) VALUES (6);

INSERT INTO frequency_of_activity_translations (id_foa_translations,language,description,id_foa) VALUES (1,'en-GB','none',1);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,description,id_foa) VALUES (2,'en-GB','very low',2);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,description,id_foa) VALUES (3,'en-GB','low',3);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,description,id_foa) VALUES (4,'en-GB','medium',4);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,description,id_foa) VALUES (5,'en-GB','high',5);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,description,id_foa) VALUES (6,'en-GB','very high',6);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,description,id_foa) VALUES (7,'pl-PL','nieokreślono',1);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,description,id_foa) VALUES (8,'pl-PL','bardzo mała',2);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,description,id_foa) VALUES (9,'pl-PL','mała',3);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,description,id_foa) VALUES (10,'pl-PL','średnia',4);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,description,id_foa) VALUES (11,'pl-PL','duża',5);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,description,id_foa) VALUES (12,'pl-PL','bardzo duża',6);

INSERT INTO sex (id_sex) VALUES (1);
INSERT INTO sex (id_sex) VALUES (2);

INSERT INTO sex_translations (id_sex_translations, language, description, id_sex) VALUES (1, 'en-GB', 'male', 1);
INSERT INTO sex_translations (id_sex_translations, language, description, id_sex) VALUES (2, 'en-GB', 'female', 2);
INSERT INTO sex_translations (id_sex_translations, language, description, id_sex) VALUES (3, 'pl-PL', 'mężczyzna', 1);
INSERT INTO sex_translations (id_sex_translations, language, description, id_sex) VALUES (4, 'pl-PL', 'kobieta', 2);


--User Adam
--INSERT INTO users (id_user, username, password, email, enabled) VALUES (1, 'adam', 'test', 'adam@gmail.com', true);
--INSERT INTO users_details (id_user, height, weight, age, BMI, BMR, id_foa, id_bt, id_sex) VALUES (1, 193, 97, 27, 666, 666, 5, 2, 1);
--INSERT INTO users_has_roles (id_user, id_role) VALUES (1, 1);
--INSERT INTO users_has_roles (id_user, id_role) VALUES (1, 2);

--User Justyna
--INSERT INTO users (id_user, username, password, email, enabled) VALUES (2, 'justyna', 'test', 'isia89@gmail.com', true);
--INSERT INTO users_details (id_user, height, weight, age, BMI, BMR, id_foa, id_bt, id_sex) VALUES (2, 160, 50, 27, 333, 333, 3, 1, 2);
--INSERT INTO users_has_roles (id_user, id_role) VALUES (2, 1);
