INSERT INTO salons (salon_id, salon_name, salon_address, salon_phone_number, salon_days_open)
VALUES
    (1, 'Salon 1', 'Address 1', '0123456789', '0000001'),
    (2, 'Salon 2', 'Address 2', '0123456789', '0110001'),
    (3, 'Salon 3', 'Address 3', '0123456789', '1111111');

INSERT INTO stylists (stylist_id, stylist_name, stylist_phone_number, stylist_annual_salary, salon_id)
VALUES
    (1, 'Stylist 1', '0123456789', 20000, 1),
    (2, 'Stylist 2', '0123456789', 30000, 1),
    (3, 'Stylist 3', '0123456789', 40000, 2);

