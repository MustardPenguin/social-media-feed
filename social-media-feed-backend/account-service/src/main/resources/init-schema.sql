
DROP SCHEMA IF EXISTS account CASCADE;

CREATE SCHEMA account;

DROP TABLE IF EXISTS account.accounts;

CREATE TABLE account.accounts (
    account_id UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);