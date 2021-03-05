INSERT INTO `client` (`id_client`, `client_name`)
VALUES 	(1, 'Клиент1'),
	(2, 'Клиент2'),
	(3, 'Клиент3'),
        (4, 'Клиент4'),
        (5, 'Клиент5');       
        
INSERT INTO `payment` (`id_payment`, `id_card`, `payment_date`, `payment_sum`)
VALUES	(1, 1, '2020-01-10 10:00:00', 10.02),
	(2, 2, '2020-01-10 10:00:00', 20.00);
        (3, 3, '2020-01-10 10:00:00', 30.00),
        (4, 4, '2020-02-10 11:00:00', 40.00),
        (5, 5, '2020-03-10 12:00:00', 50.00);
        
INSERT INTO `card` (`id_card`, `id_client`, `card_type`)
VALUES	(1, 1, 'Visa'),
	(2, 2, 'Mastercard');
	(3, 3, 'Mir'),
        (4, 4, 'Visa'),
        (5, 5, 'Visa');
