
DROP SCHEMA IF EXISTS account CASCADE;

CREATE SCHEMA account;

DROP TABLE IF EXISTS account.accounts;

CREATE TABLE account.accounts (
    account_id UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS account.follows;

CREATE TABLE account.follows (
    follow_id UUID PRIMARY KEY,
    follower_id UUID NOT NULL,
    followee_id UUID NOT NULL
);

DROP TABLE IF EXISTS account.follow_created_event;

CREATE TABLE account.follow_created_events (
    event_id UUID PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    payload jsonb NOT NULL
);