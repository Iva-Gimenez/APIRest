INSERT INTO tbl_user (id, name, last_name, email, fecha_alta, city_name, state_name, country, status) VALUES (1, 'Franco', 'Toñanes', 'adriantonanes@gmail.com', '2018-09-05', 'Resistencia', 'Chaco', 'Argentina', 'CREATED');
INSERT INTO tbl_user (id, name, last_name, email, fecha_alta, city_name, state_name, country, status) VALUES (2, 'Roberto', 'Alegre', 'robertuam@gmail.com', '2019-09-05', 'Corrientes', 'Chaco', 'Argentina', 'CREATED');
INSERT INTO tbl_user (id, name, last_name, email, fecha_alta, city_name, state_name, country, status) VALUES (3, 'Alejandro', 'Lencina', 'alejandro7p@gmail.com', '2020-09-05', 'Resistencia', 'Chaco', 'Argentina', 'CREATED');
INSERT INTO tbl_user (id, name, last_name, email, fecha_alta, city_name, state_name, country, status) VALUES (4, 'Ivana', 'Gimenez', 'iva@gmail.com', '2015-02-05', 'Buenos Aires', 'Chaco', 'Argentina', 'CREATED');
INSERT INTO tbl_user (id, name, last_name, email, fecha_alta, city_name, state_name, country, status) VALUES (5, 'Itati', 'Araujo', 'itafull18@gmail.com', '2021-05-23', 'Resistencia', 'Chaco', 'Argentina', 'CREATED');

INSERT INTO tbl_product (id, name, description, price, content, fecha_alta, published, status) VALUES (1, 'Parlante usb', 'Parlante para la pc o tv, hdmi o aux', 1700.5, 'Parlante samsung de la parra para todo lo que sea audio', '2018-05-10', 'true', 'CREATED');
INSERT INTO tbl_product (id, name, description, price, content, fecha_alta, published, status) VALUES (2, 'Tv Samsung 32 pulgas', 'Televisón smart tv con 4 entradas hdmi', 60000.5, 'Tv para ver netflix, youtube, facebook', '2017-05-10', 'false', 'CREATED');
INSERT INTO tbl_product (id, name, description, price, content, fecha_alta, published, status) VALUES (3, 'Samsung a32', '4gb de ram y 128gb de memoria interna', 36000.5, 'Un celular con lo último en cámara y software', '2019-05-10', 'true', 'CREATED');
INSERT INTO tbl_product (id, name, description, price, content, fecha_alta, published, status) VALUES (4, 'Moto g100', '8gb de ram y 256gb de memoria interna', 80000.2, 'La gama alta de motorola es lo mejor en calidad-precio', '2016-05-10', 'true', 'CREATED');
INSERT INTO tbl_product (id, name, description, price, content, fecha_alta, published, status) VALUES (5, 'Parlante Genius Gx', 'Parlante para la pc o tv, hdmi o aux, 40watts', 6000.5, 'Parlante gx para los gamer más exigentes', '2015-05-10', 'false', 'CREATED');


INSERT INTO tbl_carrito (id, client_platform, user_carrito_id, created_at, status) VALUES(1, 'Mobile', 1, '2021-05-23', true);
INSERT INTO tbl_carrito (id, client_platform, user_carrito_id, created_at, status) VALUES(2, 'Mobile', 1, '2021-05-23', false);
INSERT INTO tbl_carrito (id, client_platform, user_carrito_id, created_at, status) VALUES(3, 'Pc', 2, '2021-05-23', true);
INSERT INTO tbl_carrito (id, client_platform, user_carrito_id, created_at, status) VALUES(4, 'Web service', 5, '2021-05-23', true);

INSERT INTO tbl_carrito_item ( carrito_item_id,  quantity, price, carrito_item_product_id) VALUES(1, 1 , 36000.5, 3);
INSERT INTO tbl_carrito_item ( carrito_item_id, quantity, price, carrito_item_product_id)  VALUES(2, 2 , 6000.5, 5);
INSERT INTO tbl_carrito_item ( carrito_item_id, quantity, price, carrito_item_product_id)  VALUES(3, 2 , 80000.2, 4);
INSERT INTO tbl_carrito_item ( carrito_item_id, quantity, price, carrito_item_product_id)  VALUES(4, 3 , 60000.5, 2);