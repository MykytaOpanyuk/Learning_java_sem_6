create table tours
(
    id               INTEGER PRIMARY KEY AUTO_INCREMENT,
    tour_RentalCompany      INTEGER      NOT NULL,
    tour_name        VARCHAR(255) NOT NULL,
    tour_description VARCHAR(255) NOT NULL,
    cost             INTEGER      NOT NULL,
    FOREIGN KEY (tour_RentalCompany) references agencies (id)
)