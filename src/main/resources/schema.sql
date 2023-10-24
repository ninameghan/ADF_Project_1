CREATE TABLE salons (
    salon_id INT PRIMARY KEY,
    salon_name VARCHAR(225) NOT NULL ,
    salon_address VARCHAR(225) NOT NULL ,
    salon_phone_number VARCHAR(225) NOT NULL ,
    salon_days_open VARCHAR(225) NOT NULL
);

CREATE TABLE stylists (
    stylist_id INT PRIMARY KEY,
    stylist_name VARCHAR(225) NOT NULL,
    stylist_phone_number VARCHAR(225) NOT NULL,
    stylist_annual_salary DECIMAL NOT NULL,
    salon_id INT,
    FOREIGN KEY (salon_id) REFERENCES salons(salon_id) ON DELETE CASCADE
)