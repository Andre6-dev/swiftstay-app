ALTER TABLE rooms
    ALTER COLUMN numeber_of_beds SET NOT NULL;

ALTER TABLE rooms
    ALTER COLUMN room_description SET NOT NULL;

ALTER TABLE rooms
    ALTER COLUMN room_dimension SET NOT NULL;

ALTER TABLE rooms
    ALTER COLUMN room_slug SET NOT NULL;