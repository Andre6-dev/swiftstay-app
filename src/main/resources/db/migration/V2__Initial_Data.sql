-- Sample data for roles table
INSERT INTO roles (created_at, last_modified_at, role_name)
VALUES (CURRENT_TIMESTAMP, NULL, 'ROLE_USER'),
       (CURRENT_TIMESTAMP, NULL, 'ROLE_MODERATOR'),
       (CURRENT_TIMESTAMP, NULL, 'ROLE_ADMIN');

-- Sample data for users table
INSERT INTO users (user_id, created_at, last_modified_at, first_name, last_name, email, password, phone_number,
                   account_status, last_login_date, failed_login_attempts)
VALUES ('f2018be9-7ba7-4a0f-84b6-5de1e5e28b6c', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alice', 'Johnson',
        'alice@example.com', 'hashed_password_123', '+1234567890', 'active', CURRENT_TIMESTAMP, 0),
       ('7fc9dce4-15e4-4be2-b62a-90a62e9d1e0d', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Bob', 'Smith', 'bob@example.com',
        'hashed_password_456', '+1987654321', 'active', CURRENT_TIMESTAMP, 0);

-- Sample data for users_roles table (linking roles to users)
INSERT INTO users_roles (role_id, user_id)
VALUES (1, 'f2018be9-7ba7-4a0f-84b6-5de1e5e28b6c'),
       (2, '7fc9dce4-15e4-4be2-b62a-90a62e9d1e0d');

-- Sample data for rooms table
INSERT INTO rooms (room_id, created_at, last_modified_at, room_name, room_type, room_price, room_slug, room_description, number_of_beds, room_dimension, is_booked, is_featured, photo_url)
VALUES
    ('0fc6ba1c-6fd3-4f67-b69c-1242fe2cb5f9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Luxury Suite', 'Suite', 300.00, 'luxury-suite', 'Spacious luxury suite with a view', 2, '500 sq ft', false, true, 'https://example.com/luxury-suite.jpg'),
    ('124d2e1f-7bfc-4821-a4a5-6c9c202f0f22', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Standard Room', 'Standard', 100.00, 'standard-room', 'Comfortable standard room for two', 1, '250 sq ft', false, false, 'https://example.com/standard-room.jpg');

-- Sample data for booked_rooms table
INSERT INTO booked_rooms (booking_id, created_at, last_modified_at, check_in, check_out, guest_full_name, guest_email, num_of_adults, num_of_children, total_guest, confirmation_code, room_id)
VALUES
    ('1f8a6b9c-15c5-4c5d-bf79-1d8d48b3c0f1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '2023-05-15', '2023-05-20', 'John Doe', 'johndoe@example.com', 2, 0, 2, 'ABC123', '0fc6ba1c-6fd3-4f67-b69c-1242fe2cb5f9'),
    ('2e5a3bd1-d24e-4c01-9b30-ecb9137c7d8a', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '2023-06-10', '2023-06-15', 'Jane Smith', 'janesmith@example.com', 1, 1, 2, 'DEF456', '124d2e1f-7bfc-4821-a4a5-6c9c202f0f22');