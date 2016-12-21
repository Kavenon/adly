CREATE KEYSPACE IF NOT EXISTS adly WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE adly;

CREATE TABLE IF NOT EXISTS profile (uuid uuid, devices list<uuid>, PRIMARY KEY((uuid)));

CREATE TABLE IF NOT EXISTS device (uuid uuid, profile uuid, created timestamp, "token" text, system text, PRIMARY KEY((uuid)));

CREATE TABLE IF NOT EXISTS vendor_profiles (vendor_id int, profile_id uuid, PRIMARY KEY((vendor_id), profile_id))  WITH CLUSTERING ORDER BY (profile_id DESC);

CREATE TABLE IF NOT EXISTS notification_sent (device_id uuid, date timestamp, notification text, PRIMARY KEY((device_id), date)) WITH CLUSTERING ORDER BY (date DESC);

CREATE TABLE IF NOT EXISTS survey_response (profile_id uuid, survey_id int, date timestamp, response text, device_id uuid, PRIMARY KEY((profile_id), survey_id, date))  WITH CLUSTERING ORDER BY (survey_id DESC);

CREATE TABLE IF NOT EXISTS profile_card (profile_id uuid, property_id int, property_value text, PRIMARY KEY((profile_id),property_id)) WITH CLUSTERING ORDER BY (property_id DESC);

CREATE TABLE IF NOT EXISTS survey_sent (profile_id uuid, survey_id int, date timestamp, PRIMARY KEY((profile_id), survey_id, date)) WITH CLUSTERING ORDER BY (survey_id DESC);

CREATE TABLE IF NOT EXISTS beacon_discover_history (uuid uuid, beacon_id int, date timestamp, trace_id uuid, PRIMARY KEY((uuid), beacon_id, date)) WITH CLUSTERING ORDER BY (beacon_id DESC);

