CREATE TABLE post (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ownerid INT,
    content VARCHAR(255) NOT NULL,
    reportcount INT DEFAULT 0,
    FOREIGN KEY (ownerid) REFERENCES user (id)
);

-- Post table
INSERT INTO post (ownerid, content, reportcount)
VALUES
(1, 'Hello world!', 0),
(2, 'Check out this cool photo.', 0),
(3, 'Any recommendations for good restaurants?', 0),
(4, 'Excited for the weekend!', 0);
