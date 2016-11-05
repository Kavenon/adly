CREATE TABLE beacon_discover_history (uuid uuid, beacon_id int, date timestamp, trace_id uuid, PRIMARY KEY((uuid), beacon_id, date)) WITH CLUSTERING ORDER BY (beacon_id DESC);
