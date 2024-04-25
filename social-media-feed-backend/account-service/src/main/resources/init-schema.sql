
DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
    account_id UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);