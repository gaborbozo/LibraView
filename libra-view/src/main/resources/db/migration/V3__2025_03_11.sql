CREATE SEQUENCE IF NOT EXISTS libra_user_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE libra_user
(
    id       BIGINT NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT pk_libra_user PRIMARY KEY (id)
);

CREATE TABLE user_cinematic
(
    cinematic_id BIGINT NOT NULL,
    user_id      BIGINT NOT NULL,
    CONSTRAINT pk_user_cinematic PRIMARY KEY (cinematic_id, user_id)
);

ALTER TABLE cinematic
    ADD backdrop_path VARCHAR(255);

ALTER TABLE cinematic
    ADD overview VARCHAR(5000);

ALTER TABLE cinematic
    ADD popularity DOUBLE PRECISION;

ALTER TABLE cinematic
    ADD poster_path VARCHAR(255);

ALTER TABLE cinematic
    ADD release_date TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE cinematic
    ADD vote_average DOUBLE PRECISION;

ALTER TABLE cinematic
    ADD vote_count INTEGER;

ALTER TABLE user_cinematic
    ADD CONSTRAINT fk_usecin_on_cinematic FOREIGN KEY (cinematic_id) REFERENCES cinematic (id);

ALTER TABLE user_cinematic
    ADD CONSTRAINT fk_usecin_on_libra_user FOREIGN KEY (user_id) REFERENCES libra_user (id);