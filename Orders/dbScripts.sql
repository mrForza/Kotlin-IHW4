-- Clear "order" table
DELETE FROM "order" WHERE id > 0;

-- Clear "station" table
DELETE FROM station WHERE id > 0;

-- Add some rows in "session" table
INSERT INTO station (name) VALUES
('First Station'),
('Second Station'),
('Third Station'),
('Fourth Station'),
('Fifth Station');