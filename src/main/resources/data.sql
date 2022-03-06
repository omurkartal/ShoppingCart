INSERT INTO product(code,name,price,product_type,image_path) VALUES ('001', 'Laptop'           , 12500 , 'ELEC', 'images/1.png');
INSERT INTO product(code,name,price,product_type,image_path) VALUES ('002', 'Tablet'           , 5000  , 'ELEC', 'images/2.png');
INSERT INTO product(code,name,price,product_type,image_path) VALUES ('003', 'Core Java'        , 40.14 , 'BOOK', 'images/3.png');
INSERT INTO product(code,name,price,product_type,image_path) VALUES ('004', 'Spring Boot'      , 95.59 , 'BOOK', 'images/4.png');
INSERT INTO product(code,name,price,product_type,image_path) VALUES ('005', 'Welcome to DevOps', 85.58 , 'BOOK', 'images/5.png');
INSERT INTO product(code,name,price,product_type,image_path) VALUES ('006', 'Turkey Map'       , 85.58 , 'BOOK', 'images/tr.png');
INSERT INTO product(code,name,price,product_type,image_path) VALUES ('007', 'Guitar'           , 250   , 'MUS' , 'images/7.png');
INSERT INTO product(code,name,price,product_type,image_path) VALUES ('008', 'Piano'            , 4500  , 'MUS' , 'images/8.png');
INSERT INTO product(code,name,price,product_type,image_path) VALUES ('009', 'Violin'           , 500   , 'MUS' , null);

INSERT INTO product_type(code,name,discount_id) VALUES ('ELEC', 'Electronics', 0);
INSERT INTO product_type(code,name,discount_id) VALUES ('BOOK', 'Books', 0);
INSERT INTO product_type(code,name,discount_id) VALUES ('MUS', 'Music', 0);
INSERT INTO product_type(code,name,discount_id) VALUES ('ART', 'Art', 0);
