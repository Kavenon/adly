CREATE TABLE survey_response (profile_id uuid, date timestamp, survey_id int, response text, device_id uuid, PRIMARY KEY((profile_id), date, survey_id))  WITH CLUSTERING ORDER BY (date DESC);
