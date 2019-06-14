create table cars
(
    id                  INTEGER PRIMARY KEY AUTO_INCREMENT,
    CarRentalCompany    INTEGER      NOT NULL,
    car_name            VARCHAR(255) NOT NULL,
    car_description     VARCHAR(255) NOT NULL,
    cost                INTEGER      NOT NULL,
    FOREIGN KEY (CarRentalCompany) references Rental_companies (id)
)