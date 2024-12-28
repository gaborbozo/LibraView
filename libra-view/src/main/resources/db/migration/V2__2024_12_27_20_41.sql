CREATE SEQUENCE IF NOT EXISTS cinematic_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS genre_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS null_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE cinematic
(
    id            BIGINT NOT NULL,
    discriminator VARCHAR(31),
    tmdb_id       BIGINT,
    title         VARCHAR(255),
    CONSTRAINT pk_cinematic PRIMARY KEY (id)
);

CREATE TABLE cinematic_genre
(
    cinematic_id BIGINT NOT NULL,
    genre_id     BIGINT NOT NULL,
    CONSTRAINT pk_cinematic_genre PRIMARY KEY (cinematic_id, genre_id)
);

CREATE TABLE genre
(
    id      BIGINT NOT NULL,
    tmdb_id BIGINT,
    name    VARCHAR(255),
    CONSTRAINT pk_genre PRIMARY KEY (id)
);

ALTER TABLE cinematic
    ADD CONSTRAINT uc_cinematic_tmdb UNIQUE (tmdb_id);

ALTER TABLE genre
    ADD CONSTRAINT uc_genre_tmdb UNIQUE (tmdb_id);

ALTER TABLE cinematic_genre
    ADD CONSTRAINT fk_cingen_on_cinematic FOREIGN KEY (cinematic_id) REFERENCES cinematic (id);

ALTER TABLE cinematic_genre
    ADD CONSTRAINT fk_cingen_on_genre FOREIGN KEY (genre_id) REFERENCES genre (id);