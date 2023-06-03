CREATE TABLE report (
    reportid INT PRIMARY KEY AUTO_INCREMENT,
    userid INT NOT NULL,
    postid INT NOT NULL,
    date DATETIME NOT NULL,
    phonenumber VARCHAR(20),
    country VARCHAR(255),
    city VARCHAR(255),
    mediaurl VARCHAR(255),
    violationtype VARCHAR(255),
    FOREIGN KEY (userid) REFERENCES user (id),
    FOREIGN KEY (postid) REFERENCES post (id)
);

-- Report table
INSERT INTO report (
    userid, postid, date, phonenumber, country, city, mediaurl, violationtype
)
VALUES
(
    2,
    1,
    '2023-06-01 10:30:00',
    '555-7777',
    'USA',
    'Los Angeles',
    'http://example.com/image1.jpg',
    'Harassment'
),
(
    3,
    1,
    '2023-06-02 09:45:00',
    '555-9999',
    'USA',
    'Chicago',
    'http://example.com/image2.jpg',
    'Spam'
),
(
    4,
    2,
    '2023-06-02 15:20:00',
    '555-1111',
    'USA',
    'San Francisco',
    'http://example.com/image3.jpg',
    'Inappropriate content'
);
