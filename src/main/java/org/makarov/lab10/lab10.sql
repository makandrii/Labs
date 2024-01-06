-- Make schemes
CREATE TABLE IF NOT EXISTS animals
(
    animal_id     SERIAL PRIMARY KEY,
    name          VARCHAR(50),
    species       VARCHAR(50),
    date_of_birth DATE,
    habitat       VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS caretakers
(
    caretaker_id   SERIAL PRIMARY KEY,
    name           VARCHAR(50),
    specialization VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS feeding_schedules
(
    feed_id      SERIAL PRIMARY KEY,
    animal_id    INTEGER,
    FOREIGN KEY (animal_id) REFERENCES animals (animal_id),
    caretaker_id integer,
    FOREIGN KEY (caretaker_id) REFERENCES caretakers (caretaker_id),
    feed_time    TIME,
    feed_type    VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS visitors
(
    visitor_id SERIAL PRIMARY KEY,
    name       VARCHAR(50),
    visit_date DATE
);

CREATE TABLE IF NOT EXISTS visits
(
    visit_id   SERIAL PRIMARY KEY,
    visitor_id INTEGER,
    FOREIGN KEY (visit_id) REFERENCES visitors (visitor_id),
    animal_id  INTEGER,
    FOREIGN KEY (animal_id) REFERENCES animals (animal_id)
);

-- Insert 5 lines into each scheme
INSERT INTO animals(name, species, date_of_birth, habitat)
VALUES ('Leo', 'Lion', '2020-03-15', 'Grasslands'),
       ('Molly', 'Elephant', '2018-06-22', 'Forest'),
       ('Luffy', 'Monkey', '2019-01-10', 'Jungle'),
       ('Bella', 'Penguin', '2021-04-05', 'Arctic'),
       ('Rocky', 'Tiger', '2017-11-28', 'Savannas');

INSERT INTO caretakers(name, specialization)
VALUES ('Anna', 'Reptiles'),
       ('John', 'Mammals'),
       ('Maria', 'Birds'),
       ('Alex', 'Aquatic Animals'),
       ('Peter', 'Insects');

INSERT INTO feeding_schedules(animal_id, caretaker_id, feed_time, feed_type)
VALUES (2, 2, '09:00', 'Hay'),
       (3, 2, '12:30', 'Fruits'),
       (1, 2, '15:45', 'Meat'),
       (4, 3, '11:00', 'Fish'),
       (5, 2, '14:15', 'Live Prey');

INSERT INTO visitors(name, visit_date)
VALUES ('Sophie', '2023-01-05'),
       ('Michael', '2023-02-12'),
       ('Emma', '2023-03-20'),
       ('David', '2023-04-15'),
       ('Olivia', '2023-05-22');

INSERT INTO visits(visitor_id, animal_id)
VALUES (2, 3),
       (4, 3),
       (3, 5),
       (1, 2),
       (5, 4);

-- Get a list of all animals and their respective caretakers
SELECT a.name animal_name, c.name caretaker_name
FROM feeding_schedules fs
         INNER JOIN animals a USING (animal_id)
         LEFT JOIN caretakers c USING (caretaker_id);

-- Determine which type of animal is most often visited by visitors
SELECT species
FROM visits
         INNER JOIN animals USING (animal_id)
GROUP BY species
ORDER BY count(species) DESC
LIMIT 1;

-- Determine which keeper has the most diverse specialization, that is, he cares for the largest number of species
SELECT c.name careraker_name
FROM feeding_schedules
         INNER JOIN caretakers c USING (caretaker_id)
GROUP BY c.name
ORDER BY count(c.name) DESC
LIMIT 1;

-- Detect days when more than 10 visitors visited the zoo
SELECT visit_date
FROM visitors
GROUP BY visit_date
HAVING count(visit_date) > 10;

-- Create a feeding schedule report that includes pet name, keeper name, feeding time and feed type,
-- sorted by feeding time
SELECT a.name animal_name, c.name caretaker_name, feed_time, feed_type
FROM feeding_schedules
         INNER JOIN animals a USING (animal_id)
         INNER JOIN caretakers c USING (caretaker_id)
ORDER BY feed_time;