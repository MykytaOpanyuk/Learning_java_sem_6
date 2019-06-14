create table agents
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    RentalCompany_id  INTEGER      NOT NULL,
    agent_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (RentalCompany_id) references Rental_companies (id)
);