CREATE TABLE beacon_discover_history (uuid uuid, date timestamp, beacon_id int, trace_id uuid, PRIMARY KEY((uuid), date)) WITH CLUSTERING ORDER BY (date DESC);
