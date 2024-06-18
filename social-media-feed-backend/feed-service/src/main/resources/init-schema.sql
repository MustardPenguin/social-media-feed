
DROP SCHEMA IF EXISTS feed CASCADE;

CREATE SCHEMA feed;

DROP TABLE IF EXISTS feed.follows CASCADE;

CREATE TABLE feed.follows(
    follow_id UUID PRIMARY KEY ,
    follower_id UUID NOT NULL,
    followee_id UUID NOT NULL
);

DROP TABLE IF EXISTS feed.posts CASCADE;

CREATE TABLE feed.posts(
    post_id UUID PRIMARY KEY,
    account_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL
);