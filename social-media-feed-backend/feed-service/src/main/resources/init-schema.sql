
DROP SCHEMA IF EXISTS feed CASCADE;

CREATE SCHEMA feed;

DROP TABLE IF EXISTS feed.follows CASCADE;

CREATE TABLE feed.follows(
    follow_id UUID PRIMARY KEY ,
    follower_id UUID NOT NULL,
    followee_id UUID NOT NULL
);