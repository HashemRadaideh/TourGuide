CREATE TABLE report (
    reportid INT PRIMARY KEY AUTO_INCREMENT,
    userid INT NOT NULL,
    postid INT NOT NULL,
    reportdate DATETIME NOT NULL,
    phonenumber VARCHAR(20),
    country VARCHAR(255),
    city VARCHAR(255),
    mediaurl VARCHAR(255),
    violationtype VARCHAR(255),
    isactive INT DEFAULT 1,
    FOREIGN KEY (userid) REFERENCES user (id),
    FOREIGN KEY (postid) REFERENCES post (id)
);

INSERT INTO report (
    userid,
    postid,
    reportdate,
    phonenumber,
    country,
    city,
    mediaurl,
    violationtype
)
VALUES
(
    2,
    3,
    '2023-06-02 10:15:00',
    '555-5555',
    'USA',
    'New York',
    'http://example.com/image4.jpg',
    'littering'
),
(
    1,
    4,
    '2023-06-03 08:30:00',
    '555-3333',
    'Italy',
    'Rome',
    'http://example.com/image5.jpg',
    'jaywalking'
),
(
    5,
    2,
    '2023-06-03 13:45:00',
    '555-4444',
    'Greece',
    'Athens',
    'http://example.com/image6.jpg',
    'red-light'
);
