INSERT into profile_property (id, deleted, name, type, user_id) values (30, false, 'Name', '{"type":".SimplePropertyType","simpleType":"TEXT"}', null);
INSERT into profile_property (id, deleted, name, type, user_id) values (31, false, 'Age', '{"type":".SimplePropertyType","simpleType":"INTEGER"}', null);
INSERT INTO survey (id, deleted, name, user_id) VALUES (32, false, 'Welcome to Adly.io', null);
INSERT INTO survey_field (id, name, profile_property_id) VALUES (33, 'Name', 30);
INSERT INTO survey_field (id, name, profile_property_id) VALUES (34, 'Age', 31);
INSERT INTO survey_field_list (survey_id, field_list_id) VALUES (32, 33);
INSERT INTO survey_field_list (survey_id, field_list_id) VALUES (32, 34);
