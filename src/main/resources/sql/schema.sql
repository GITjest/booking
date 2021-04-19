CREATE TABLE users
(
    user_id       INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`        VARCHAR(25) NOT NULL,
    date_of_birth DATETIME    NOT NULL,
    email         VARCHAR(25) NOT NULL,
    sex           VARCHAR(15) NOT NULL
);

CREATE TABLE movies
(
    movie_id      INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`        VARCHAR(50)   NOT NULL,
    `description` VARCHAR(1000) NOT NULL,
    cover_link    VARCHAR(100)  NOT NULL
);

CREATE TABLE addresses
(
    address_id    INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    city          VARCHAR(50) NOT NULL,
    zip_code      VARCHAR(50) NOT NULL,
    street        VARCHAR(50) NOT NULL,
    street_number INT         NOT NULL
);

CREATE TABLE theaters
(
    theater_id INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`     VARCHAR(50) NOT NULL,
    address_id INT         NOT NULL,
    foreign key (address_id) references addresses (address_id) ON DELETE CASCADE
);

CREATE TABLE film_screenings
(
    film_screening_id INT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    price             INT      NOT NULL,
    `date`            DATETIME NOT NULL,
    number_of_seats   INT      NOT NULL,
    theater_id        INT      NOT NULL,
    movie_id          INT      NOT NULL,
    foreign key (theater_id) references theaters (theater_id) ON DELETE CASCADE,
    foreign key (movie_id) references movies (movie_id) ON DELETE CASCADE
);

CREATE TABLE bookings
(
    user_id           INT NOT NULL,
    film_screening_id INT NOT NULL,
    foreign key (user_id) references users (user_id) ON DELETE CASCADE,
    foreign key (film_screening_id) references film_screenings (film_screening_id) ON DELETE CASCADE
);