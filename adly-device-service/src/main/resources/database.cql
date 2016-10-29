CREATE KEYSPACE adly WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

CREATE TABLE profile (uuid uuid, devices list<uuid>, PRIMARY KEY((uuid)));