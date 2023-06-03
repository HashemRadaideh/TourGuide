CREATE TABLE admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    CONSTRAINT unique_username UNIQUE (username)
);

INSERT INTO admin (username, password, email, firstname, lastname)
VALUES
('admin1', 'password1', 'admin1@example.com', 'John', 'Doe'),
('admin2', 'password2', 'admin2@example.com', 'Jane', 'Smith'),
('admin3', 'password3', 'admin3@example.com', 'Mike', 'Wilson'),
('admin4', 'password4', 'admin4@example.com', 'Amy', 'Johnson');
