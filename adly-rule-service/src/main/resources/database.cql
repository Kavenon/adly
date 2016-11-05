CREATE TABLE survey_sent (profile_id uuid, survey_id int, date timestamp, PRIMARY KEY((profile_id), survey_id, date)) WITH CLUSTERING ORDER BY (survey_id DESC);

