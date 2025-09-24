CREATE TABLE user_cinematic_reference
(
    user_id                BIGINT       NOT NULL,
    cinematic_reference_id BIGINT       NOT NULL,
    discriminator          VARCHAR(255) NOT NULL,
    CONSTRAINT uc_user_cinematic_reference UNIQUE (user_id, cinematic_reference_id, discriminator)
);