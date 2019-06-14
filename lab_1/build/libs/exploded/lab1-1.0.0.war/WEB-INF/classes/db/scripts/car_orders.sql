create table orders
(
    id                          INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_id                     INTEGER NOT NULL,
    car_id                      INTEGER NOT NULL,
    rental_company_agent_id     INTEGER NOT NULL,
    cost                        INTEGER,
    FOREIGN KEY (rental_company_agent_id) references agents (id),
    FOREIGN KEY (car_id) references cars (id)
)