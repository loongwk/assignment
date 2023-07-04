CREATE TABLE TESTDB.users (
  user_profile_id BIGINT NOT NULL identity(1,1),
  name VARCHAR(255),
  email VARCHAR(255)
);

ALTER TABLE TESTDB.users
  ADD CONSTRAINT user_profile_pkey
    PRIMARY KEY (user_profile_id);