CREATE TABLE IF NOT EXISTS event_types (
    name VARCHAR(255) NOT NULL PRIMARY KEY
);

INSERT INTO event_types (name) VALUES
    ('DEBUG'),
    ('INFO'),
    ('WARNING'),
    ('ERROR')
    ;