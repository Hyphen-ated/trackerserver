-- here is the db shema you need to run this service.

CREATE TABLE users
(
  name text NOT NULL,
  token text,
  trackerstate text,
  stateversion int,
  updatetime timestamp with time zone,
  CONSTRAINT users_pkey PRIMARY KEY (name),
  CONSTRAINT users_token_key UNIQUE (token)
);

CREATE INDEX users_time_index ON users (updatetime DESC NULLS LAST);