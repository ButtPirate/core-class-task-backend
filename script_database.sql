CREATE TABLE files (
  id SERIAL,
  timestamp TIMESTAMP,
  original_filename VARCHAR(255),
  saved_filename VARCHAR(255),
  content_id INTEGER,
  PRIMARY KEY (id)
);
CREATE TABLE content (
  id SERIAL,
  content TEXT,
  PRIMARY KEY (id)
);
