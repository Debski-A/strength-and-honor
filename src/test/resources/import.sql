INSERT INTO roles (id_role, role) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id_role, role) VALUES (2, 'ROLE_ADMIN');

INSERT INTO body_type (id_bt) VALUES (1);
INSERT INTO body_type (id_bt) VALUES (2);
INSERT INTO body_type (id_bt) VALUES (3);
INSERT INTO body_type (id_bt) VALUES (4);

INSERT INTO body_type_translations (id_bt_translations,language,is_default,description,id_bt) VALUES (1,'en-UK',1,'none',1);
INSERT INTO body_type_translations (id_bt_translations,language,is_default,description,id_bt) VALUES (2,'en-UK',1,'ectomorph',2);
INSERT INTO body_type_translations (id_bt_translations,language,is_default,description,id_bt) VALUES (3,'en-UK',1,'mesomorph',3);
INSERT INTO body_type_translations (id_bt_translations,language,is_default,description,id_bt) VALUES (4,'en-UK',1,'endomorph',4);
INSERT INTO body_type_translations (id_bt_translations,language,is_default,description,id_bt) VALUES (5,'pl-PL',0,'nieokreslono',1);
INSERT INTO body_type_translations (id_bt_translations,language,is_default,description,id_bt) VALUES (6,'pl-PL',0,'ektomorficzny',2);
INSERT INTO body_type_translations (id_bt_translations,language,is_default,description,id_bt) VALUES (7,'pl-PL',0,'mezomorficzny',3);
INSERT INTO body_type_translations (id_bt_translations,language,is_default,description,id_bt) VALUES (8,'pl-PL',0,'endomorficzny',4);

INSERT INTO frequency_of_activity (id_foa) VALUES (1);
INSERT INTO frequency_of_activity (id_foa) VALUES (2);
INSERT INTO frequency_of_activity (id_foa) VALUES (3);
INSERT INTO frequency_of_activity (id_foa) VALUES (4);
INSERT INTO frequency_of_activity (id_foa) VALUES (5);
INSERT INTO frequency_of_activity (id_foa) VALUES (6);

INSERT INTO frequency_of_activity_translations (id_foa_translations,language,is_default,description,id_foa) VALUES (1,'en-UK',1,'none',1);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,is_default,description,id_foa) VALUES (2,'en-UK',1,'very low',2);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,is_default,description,id_foa) VALUES (3,'en-UK',1,'low',3);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,is_default,description,id_foa) VALUES (4,'en-UK',1,'medium',4);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,is_default,description,id_foa) VALUES (5,'en-UK',1,'high',5);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,is_default,description,id_foa) VALUES (6,'en-UK',1,'very high',6);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,is_default,description,id_foa) VALUES (7,'pl-PL',0,'nieokreślono',1);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,is_default,description,id_foa) VALUES (8,'pl-PL',0,'bardzo mała',2);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,is_default,description,id_foa) VALUES (9,'pl-PL',0,'mała',3);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,is_default,description,id_foa) VALUES (10,'pl-PL',0,'średnia',4);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,is_default,description,id_foa) VALUES (11,'pl-PL',0,'duża',5);
INSERT INTO frequency_of_activity_translations (id_foa_translations,language,is_default,description,id_foa) VALUES (12,'pl-PL',0,'bardzo duża',6);

INSERT INTO sex (id_sex) VALUES (1);
INSERT INTO sex (id_sex) VALUES (2);

INSERT INTO sex_translations (id_sex_translations, language, is_default, description, id_sex) VALUES (1, 'en-UK', 1, 'male', 1);
INSERT INTO sex_translations (id_sex_translations, language, is_default, description, id_sex) VALUES (2, 'en-UK', 1, 'female', 2);
INSERT INTO sex_translations (id_sex_translations, language, is_default, description, id_sex) VALUES (3, 'pl-PL', 0, 'mężczyzna', 1);
INSERT INTO sex_translations (id_sex_translations, language, is_default, description, id_sex) VALUES (4, 'pl-PL', 0, 'kobieta', 2);

ALTER TABLE posts ALTER COLUMN id_post RESTART WITH 1

INSERT INTO posts (id_post) VALUES (1);

INSERT INTO posts_translations (id_post_translations, language, is_default, content, id_post) VALUES (1, 'en-UK', 1, 'This is first post in english', 1);
INSERT INTO posts_translations (id_post_translations, language, is_default, content, id_post) VALUES (2, 'pl-PL', 0, 'To jest pierwszy post po polsku', 1);

--User Adam
--INSERT INTO users (id_user, username, password, email, enabled) VALUES (1, 'adam', 'test', 'adam@gmail.com', true);
--INSERT INTO users_details (id_user, height, weight, age, BMI, BMR, id_foa, id_bt, id_sex) VALUES (1, 193, 97, 27, 666, 666, 5, 2, 1);
--INSERT INTO users_has_roles (id_user, id_role) VALUES (1, 1);
--INSERT INTO users_has_roles (id_user, id_role) VALUES (1, 2);

--User Justyna
--INSERT INTO users (id_user, username, password, email, enabled) VALUES (2, 'justyna', 'test', 'isia89@gmail.com', true);
--INSERT INTO users_details (id_user, height, weight, age, BMI, BMR, id_foa, id_bt, id_sex) VALUES (2, 160, 50, 27, 333, 333, 3, 1, 2);
--INSERT INTO users_has_roles (id_user, id_role) VALUES (2, 1);
