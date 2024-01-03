CREATE SCHEMA dopad_platform;

ALTER SCHEMA dopad_platform OWNER TO "dopad";

CREATE TABLE dopad_platform.user (
    id uuid NOT NULL,
    username varchar NOT NULL,
    password varchar NOT NULL
);

ALTER TABLE dopad_platform.user OWNER TO "dopad";

ALTER TABLE dopad_platform.user
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);

CREATE TYPE dopad_platform.page_status AS ENUM (
    'FAILED_TO_SEND',
    'IN_PROGRESS',
    'SENT'
);

ALTER TYPE dopad_platform.page_status OWNER TO "dopad";

CREATE TABLE dopad_platform.page (
    id uuid NOT NULL,
    title varchar NOT NULL,
    text jsonb,
    status dopad_platform.page_status NOT NULL,
    change_code varchar NOT NULL,
    upload_date timestamp NOT NULL,
    is_private boolean NOT NULL,
    owner_id uuid NOT NULL
);

ALTER TABLE dopad_platform.page OWNER TO "dopad";

ALTER TABLE dopad_platform.page
    ADD CONSTRAINT page_pkey PRIMARY KEY (id);

ALTER TABLE dopad_platform.page
    ADD CONSTRAINT page_owner_id_fkey FOREIGN KEY (owner_id) REFERENCES dopad_platform.user(id);