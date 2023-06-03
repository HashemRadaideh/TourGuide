CREATE TABLE post (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    reportcount INT DEFAULT 0
);

INSERT INTO post (username, content, reportcount)
VALUES
('user1', 'This is the content of post 1', 0),
('user2', 'This is the content of post 2', 3),
('user3', 'This is the content of post 3', 1),
('user4', 'This is the content of post 4', 2);
