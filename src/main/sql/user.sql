CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phonenumber VARCHAR(20),
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    city VARCHAR(255),
    age INT,
    CONSTRAINT unique_username UNIQUE (username)
);

INSERT INTO user (
    username, password, phonenumber, firstname, lastname, city, age
)
VALUES
('user', 'password1', '1234567890', 'Hashem', 'Radaideh', 'Irbid', 21),
('john_doe', 'password1', '1234567890', 'John', 'Doe', 'New York', 30),
(
    'jane_smith',
    'password2',
    '9876543210',
    'Jane',
    'Smith',
    'Los Angeles',
    25
),
('mike_wilson', 'password3', '5555555555', 'Mike', 'Wilson', 'Chicago', 40),
(
    'amy_johnson',
    'password4',
    '1112223333',
    'Amy',
    'Johnson',
    'San Francisco',
    35
);
