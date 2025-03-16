CREATE SEQUENCE IF NOT EXISTS user_cinematic_group_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE user_cinematic_group
(
    id      BIGINT           NOT NULL,
    index   DOUBLE PRECISION NOT NULL,
    name    VARCHAR(255)     NOT NULL,
    user_id BIGINT,
    CONSTRAINT pk_user_cinematic_group PRIMARY KEY (id)
);

ALTER TABLE user_cinematic
    ADD added_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE user_cinematic
    ADD cinematic_group_id BIGINT;

ALTER TABLE user_cinematic
    ADD comment VARCHAR(255);

ALTER TABLE user_cinematic
    ADD index DOUBLE PRECISION;

ALTER TABLE user_cinematic
    ADD watched_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE user_cinematic
    ALTER COLUMN added_at SET NOT NULL;

CREATE UNIQUE INDEX IX_pk_user_cinematic ON user_cinematic (user_id, cinematic_id);

ALTER TABLE user_cinematic_group
    ADD CONSTRAINT FK_USER_CINEMATIC_GROUP_ON_USER FOREIGN KEY (user_id) REFERENCES libra_user (id);

ALTER TABLE user_cinematic
    ADD CONSTRAINT FK_USER_CINEMATIC_ON_CINEMATIC_GROUP FOREIGN KEY (cinematic_group_id) REFERENCES user_cinematic_group (id);