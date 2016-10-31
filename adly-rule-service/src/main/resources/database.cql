CREATE TABLE survey_sent (profile_id uuid, survey_id int, date timestamp, PRIMARY KEY((profile_id), survey_id, date)) WITH CLUSTERING ORDER BY (survey_id DESC);

CREATE TABLE push_sent (device_id uuid, date timestamp, push_id int, PRIMARY KEY((device_id), date, push_id)) WITH CLUSTERING ORDER BY (date DESC);
