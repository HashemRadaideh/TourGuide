CREATE TABLE report (
    reportid VARCHAR(255) PRIMARY KEY,
    userid VARCHAR(255) NOT NULL,
    postid VARCHAR(255) NOT NULL,
    date DATETIME NOT NULL,
    phonenumber VARCHAR(20),
    country VARCHAR(255),
    city VARCHAR(255),
    mediaurl VARCHAR(255),
    violationtype VARCHAR(255)
);

-- Generate fake reports
INSERT INTO report (
    reportid,
    userid,
    postid,
    date,
    phonenumber,
    country,
    city,
    mediaurl,
    violationtype
)
VALUES
(
    'report1',
    'user1',
    'post1',
    '2023-06-01 09:00:00',
    '1234567890',
    'USA',
    'New York',
    'https://example.com/media/1',
    'Spam'
),
(
    'report2',
    'user2',
    'post2',
    '2023-06-01 10:00:00',
    '9876543210',
    'Canada',
    'Toronto',
    'https://example.com/media/2',
    'Harassment'
),
(
    'report3',
    'user3',
    'post3',
    '2023-06-02 11:00:00',
    '5555555555',
    'UK',
    'London',
    'https://example.com/media/3',
    'Inappropriate Content'
);
