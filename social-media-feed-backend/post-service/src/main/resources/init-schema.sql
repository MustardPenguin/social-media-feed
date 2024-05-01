
DROP SCHEMA IF EXISTS post CASCADE;

CREATE SCHEMA post;

DROP TABLE IF EXISTS post.posts;

CREATE TABLE post.posts (
    post_id UUID PRIMARY KEY,
    account_id UUID NOT NULL,
    title VARCHAR(128) NOT NULL,
    description VARCHAR(1024) NOT NULL
);

DROP TABLE IF EXISTS post.post_created_events;

CREATE TABLE post.post_created_events (
    event_id UUID PRIMARY KEY,
    account_id UUID NOT NULL,
    post_id UUID NOT NULL
);