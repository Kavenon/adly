CREATE KEYSPACE adly WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

CREATE TABLE profile (uuid uuid, devices list<uuid>, PRIMARY KEY((uuid)));

CREATE TABLE device (uuid uuid, profile uuid, created timestamp, "token" text, system text, PRIMARY KEY((uuid)));

CREATE TABLE vendor_profiles (vendor_id int, profile_id uuid, PRIMARY KEY((vendor_id), profile_id))  WITH CLUSTERING ORDER BY (profile_id DESC);
