CREATE TABLE survey_response (profile_id uuid, survey_id int, date timestamp, response text, device_id uuid, PRIMARY KEY((profile_id), survey_id, date))  WITH CLUSTERING ORDER BY (survey_id DESC);

CREATE TABLE profile_card (profile_id uuid, property_id int, property_value text, PRIMARY KEY((profile_id),property_id)) WITH CLUSTERING ORDER BY (property_id DESC);