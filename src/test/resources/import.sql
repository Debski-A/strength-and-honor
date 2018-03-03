INSERT INTO roles (id_role, role) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id_role, role) VALUES (2, 'ROLE_ADMIN');

INSERT INTO body_type (id_bt, type) VALUES (2, 'ectomorphic');
INSERT INTO body_type (id_bt, type) VALUES (3, 'mesomorphic');
INSERT INTO body_type (id_bt, type) VALUES (4, 'endomorphic');
INSERT INTO body_type (id_bt, type) VALUES (1, 'none');

INSERT INTO frequency_of_activity (id_foa, frequency) VALUES (2, 'very low');
INSERT INTO frequency_of_activity (id_foa, frequency) VALUES (3, 'low');
INSERT INTO frequency_of_activity (id_foa, frequency) VALUES (4, 'medium');
INSERT INTO frequency_of_activity (id_foa, frequency) VALUES (5, 'high');
INSERT INTO frequency_of_activity (id_foa, frequency) VALUES (6, 'very high');
INSERT INTO frequency_of_activity (id_foa, frequency) VALUES (1, 'none');

INSERT INTO sex (id_sex, type) VALUES (1, 'male');
INSERT INTO sex (id_sex, type) VALUES (2, 'female');


--User Adam
--INSERT INTO users (id_user, username, password, email, enabled) VALUES (1, 'adam', 'test', 'adam@gmail.com', true);
--INSERT INTO users_details (id_user, height, weight, age, BMI, BMR, id_foa, id_bt, id_sex) VALUES (1, 193, 97, 27, 666, 666, 5, 2, 1);
--INSERT INTO users_has_roles (id_user, id_role) VALUES (1, 1);
--INSERT INTO users_has_roles (id_user, id_role) VALUES (1, 2);

--User Justyna
--INSERT INTO users (id_user, username, password, email, enabled) VALUES (2, 'justyna', 'test', 'isia89@gmail.com', true);
--INSERT INTO users_details (id_user, height, weight, age, BMI, BMR, id_foa, id_bt, id_sex) VALUES (2, 160, 50, 27, 333, 333, 3, 1, 2);
--INSERT INTO users_has_roles (id_user, id_role) VALUES (2, 1);
