CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phonenumber VARCHAR(20),
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    age INT,
    country VARCHAR(255),
    city VARCHAR(255),
    CONSTRAINT unique_username UNIQUE (username)
);

INSERT INTO user (
    username, password, phonenumber, firstname, lastname, city, age, country
)
VALUES
('user', 'password1234', '555-1234', 'firstname', 'lastname', 30, 'Jordan', 'Irbid'),
('john123', 'password123', '555-1234', 'John', 'Doe', 30, 'USA', 'New York'),
(
    'jane456',
    'password456',
    '555-5678',
    'Jane',
    'Smith',
    25,
    'USA'
    'Los Angeles',
),
('mike789', 'password789', '555-9876', 'Mike', 'Johnson', 35, 'USA', 'Chicago'),
(
    'sarah123',
    'password123',
    '555-2468',
    'Sarah',
    'Williams',
    28,
    'USA'
    'San Francisco',
);
