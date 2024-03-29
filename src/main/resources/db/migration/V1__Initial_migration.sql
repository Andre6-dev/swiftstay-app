CREATE TABLE booked_rooms
(
    booking_id        UUID         NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE,
    last_modified_at  TIMESTAMP WITHOUT TIME ZONE,
    check_in          date,
    check_out         date,
    guest_full_name   VARCHAR(100) NOT NULL,
    guest_email       VARCHAR(100) NOT NULL,
    num_of_adults     INTEGER,
    num_of_children   INTEGER,
    total_guest       INTEGER,
    confirmation_code VARCHAR(255),
    room_id           UUID,
    CONSTRAINT pk_booked_rooms PRIMARY KEY (booking_id)
);

CREATE TABLE roles
(
    role_id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_at TIMESTAMP WITHOUT TIME ZONE,
    role_name        VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (role_id)
);

CREATE TABLE rooms
(
    room_id          UUID         NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_at TIMESTAMP WITHOUT TIME ZONE,
    room_name        VARCHAR(100) NOT NULL,
    room_type        VARCHAR(100) NOT NULL,
    room_price       DECIMAL      NOT NULL,
    room_slug        VARCHAR(255),
    room_description VARCHAR(255),
    number_of_beds   INTEGER,
    room_dimension   VARCHAR(255),
    is_booked        BOOLEAN,
    is_featured      BOOLEAN,
    photo_url        VARCHAR(255) NOT NULL,
    CONSTRAINT pk_rooms PRIMARY KEY (room_id)
);

CREATE TABLE users
(
    user_id               UUID         NOT NULL,
    created_at            TIMESTAMP WITHOUT TIME ZONE,
    last_modified_at      TIMESTAMP WITHOUT TIME ZONE,
    first_name            VARCHAR(50)  NOT NULL,
    last_name             VARCHAR(50)  NOT NULL,
    email                 VARCHAR(100) NOT NULL,
    password              VARCHAR(255),
    phone_number          VARCHAR(20)  NOT NULL,
    account_status        VARCHAR(255),
    last_login_date       TIMESTAMP WITHOUT TIME ZONE,
    failed_login_attempts INTEGER,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

CREATE TABLE users_roles
(
    role_id BIGINT NOT NULL,
    user_id UUID   NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (role_id, user_id)
);

ALTER TABLE booked_rooms
    ADD CONSTRAINT FK_BOOKED_ROOMS_ON_ROOM FOREIGN KEY (room_id) REFERENCES rooms (room_id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (role_id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (user_id);