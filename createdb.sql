-- here is the db shema you need to run this service.

CREATE TABLE users
(
  name text NOT NULL,
  token text,
  trackerstate text,
  stateversion int,
  CONSTRAINT users_pkey PRIMARY KEY (name),
  CONSTRAINT users_token_key UNIQUE (token)
);
