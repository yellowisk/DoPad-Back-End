CREATE SCHEMA dopad_platform;

ALTER SCHEMA dopad_platform OWNER TO "dopad";

DROP TABLE IF EXISTS dopad_platform.user CASCADE;

CREATE TABLE dopad_platform.user (
    id uuid NOT NULL,
    username varchar NOT NULL,
    password varchar NOT NULL
);

ALTER TABLE dopad_platform.user OWNER TO "dopad";

ALTER TABLE dopad_platform.user
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);

DROP TYPE IF EXISTS dopad_platform.page_status CASCADE;

CREATE TYPE dopad_platform.page_status AS ENUM (
    'FAILED_TO_SEND',
    'IN_PROGRESS',
    'SENT'
);

ALTER TYPE dopad_platform.page_status OWNER TO "dopad";

DROP TABLE IF EXISTS dopad_platform.page CASCADE;

CREATE TABLE dopad_platform.page (
    id uuid NOT NULL,
    owner_id uuid NOT NULL,
    title varchar NOT NULL,
    text jsonb,
    status dopad_platform.page_status NOT NULL,
    change_code varchar NOT NULL,
    upload_date timestamp NOT NULL,
    is_private boolean NOT NULL
);

ALTER TABLE dopad_platform.page OWNER TO "dopad";

ALTER TABLE dopad_platform.page
    ADD CONSTRAINT page_pkey PRIMARY KEY (id);

ALTER TABLE dopad_platform.page
    ADD CONSTRAINT page_owner_id_fkey FOREIGN KEY (owner_id)
        REFERENCES dopad_platform.user(id);

DROP TYPE IF EXISTS dopad_platform.page_membership_status CASCADE;

CREATE TYPE dopad_platform.page_membership_status AS ENUM (
    'PENDING',
    'ACCEPTED',
    'REJECTED'
);

ALTER TYPE dopad_platform.page_membership_status OWNER TO "dopad";

DROP TABLE IF EXISTS dopad_platform.page_membership CASCADE;

CREATE TABLE dopad_platform.page_membership (
    id uuid NOT NULL,
    page_id uuid NOT NULL,
    user_id uuid NOT NULL,
    status dopad_platform.page_membership_status NOT NULL
);

ALTER TABLE dopad_platform.page_membership OWNER TO "dopad";

ALTER TABLE dopad_platform.page_membership
    ADD CONSTRAINT page_membership_pkey PRIMARY KEY (id);

ALTER TABLE dopad_platform.page_membership
    ADD CONSTRAINT page_membership_page_id_fkey FOREIGN KEY (page_id)
        REFERENCES dopad_platform.page(id);

ALTER TABLE dopad_platform.page_membership
    ADD CONSTRAINT page_membership_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES dopad_platform.user(id);