DROP TABLE IF EXISTS student;
CREATE TABLE student (id BIGINT NOT NULL PRIMARY KEY UNIQUE, name VARCHAR, surename VARCHAR, groups_id BIGINT REFERENCES groups(id) ON DELETE SET NULL);