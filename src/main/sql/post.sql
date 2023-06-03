CREATE TABLE post (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ownerid INT,
    countryname VARCHAR(255) NOT NULL,
    cityname VARCHAR(255) NOT NULL,
    status INT,
    isclean INT,
    residents INT,
    content VARCHAR(255) NOT NULL,
    trafficcount INT DEFAULT 0,
    stopcount INT DEFAULT 0,
    jaywalkcount INT DEFAULT 0,
    littercount INT DEFAULT 0,
    FOREIGN KEY (ownerid) REFERENCES user (id)
);

INSERT INTO post (
    ownerid,
    countryname,
    cityname,
    status,
    isclean,
    residents,
    content,
    trafficcount,
    stopcount,
    jaywalkcount,
    littercount
)
VALUES
(
    1,
    'United States',
    'New York',
    1,
    1,
    1000,
    'Lorem ipsum dolor sit amet',
    10,
    2,
    5,
    3
),
(
    2,
    'United Kingdom',
    'London',
    1,
    0,
    500,
    'Consectetur adipiscing elit',
    5,
    1,
    0,
    1
),
(
    3,
    'Canada',
    'Toronto',
    0,
    1,
    2000,
    'Nulla facilisi',
    15,
    3,
    7,
    2
),
(
    4,
    'Australia',
    'Sydney',
    1,
    1,
    800,
    'Pellentesque habitant morbi',
    8,
    1,
    3,
    0
),
(
    5,
    'Germany',
    'Berlin',
    0,
    0,
    1500,
    'Aenean nec magna euismod',
    2,
    0,
    1,
    1
);
