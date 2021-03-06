CREATE TABLE users
(
  name text NOT NULL,
  token text,
  trackerstate text,
  updatetime int,
  CONSTRAINT users_pkey PRIMARY KEY (name),
  CONSTRAINT users_token_key UNIQUE (token)
);

CREATE INDEX users_time_index ON users (updatetime);
