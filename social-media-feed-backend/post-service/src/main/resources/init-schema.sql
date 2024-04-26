
DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
    post_id UUID PRIMARY KEY,
    account_id UUID NOT NULL,
    title VARCHAR(128) NOT NULL,
    description VARCHAR(1024) NOT NULL
);