CREATE TABLE post (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ownerid INT,
    countryname VARCHAR(255) NOT NULL,
    cityname VARCHAR(255) NOT NULL,
    status INT,
    isclean INT,
    residents INT,
    content VARCHAR(300) NOT NULL,
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
    'Italy',
    'Rome',
    1,
    1,
    1000,
    "Rome, the Eternal City, is a must-visit destination for history enthusiasts. Explore iconic landmarks such as the Colosseum and the Roman Forum, where ancient Roman civilization thrived. Don't forget to toss a coin into the Trevi Fountain for good luck!",
    10,
    2,
    5,
    3
),
(
    2,
    'Japan',
    'Tokyo',
    1,
    0,
    500,
    'Tokyo, the bustling capital of Japan, offers a perfect blend of tradition and modernity. Discover the serene beauty of the Meiji Shrine and the lively atmosphere of Shibuya Crossing. Indulge in mouthwatering sushi and experience the unique Harajuku fashion scene!',
    5,
    1,
    0,
    1
),
(
    3,
    'France',
    'Paris',
    0,
    1,
    2000,
    "Paris, the City of Love, is synonymous with romance and charm. Visit the iconic Eiffel Tower for breathtaking views and wander through the enchanting streets of Montmartre. Don't miss out on world-class art at the Louvre Museum, home to the famous Mona Lisa!",
    15,
    3,
    7,
    2
),
(
    4,
    'Spain',
    'Barcelona',
    1,
    1,
    800,
    'Barcelona, a vibrant city on the Mediterranean coast, boasts unique architecture by Antoni Gaudí. Explore the awe-inspiring Sagrada Familia and stroll along the lively promenade of La Rambla. Indulge in delicious tapas and immerse yourself in the vibrant nightlife!',
    8,
    1,
    3,
    0
),
(
    5,
    'Greece',
    'Athens',
    0,
    0,
    1500,
    'Athens, the birthplace of democracy, is a treasure trove of ancient history. Marvel at the Acropolis and its iconic Parthenon, symbols of ancient Greek civilization. Explore the charming Plaka neighborhood and savor authentic Greek cuisine!',
    2,
    0,
    1,
    1
);
