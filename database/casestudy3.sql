CREATE DATABASE CaseStudy3;
USE CaseStudy3;


CREATE TABLE shop (
                      shop_id INT AUTO_INCREMENT PRIMARY KEY,
                      shop_code nVARCHAR(150) NOT NULL UNIQUE,
                      shop_name nVARCHAR(150) NOT NULL,
                      shop_email nVARCHAR(150) NOT NULL UNIQUE,
                      shop_phone nVARCHAR(150) NOT NULL UNIQUE,
                      shop_address nVARCHAR(150) NOT NULL,
                      shop_account nVARCHAR(150) NOT NULL UNIQUE,
                      shop_password nVARCHAR(150) NOT NULL,
                      shop_image nvarchar(150),
                      status bit
);
CREATE TABLE category (
                          category_id INT AUTO_INCREMENT PRIMARY KEY,
                          category_code nVARCHAR(150) NOT NULL UNIQUE,
                          category_name nVARCHAR(150) NOT NULL unique,

                          category_description nVARCHAR(150),
                          item_id INT,
                          status bit
);
CREATE TABLE deal (
                      deal_id INT AUTO_INCREMENT PRIMARY KEY,
                      deal_code nVARCHAR(150) NOT NULL UNIQUE,
                      deal_name nVARCHAR(150) NOT NULL,
                      deal_startDate DATETIME,
                      deal_endDate DATETIME,
                      deal_description nVARCHAR(150),
                      deal_image nVARCHAR(150),
                      status bit
);
CREATE TABLE item (
                      item_id INT AUTO_INCREMENT PRIMARY KEY,
                      item_code nVARCHAR(150) NOT NULL UNIQUE,
                      shop_id INT NOT NULL,
                      category_id INT NOT NULL,
                      deal_id INT NOT NULL,
                      item_name nVARCHAR(150) NOT NULL,
                      item_price DOUBLE NOT NULL,
                      item_description nVARCHAR(150),
                      item_image nVARCHAR(150),
                      status bit,
                      FOREIGN KEY (shop_id)
                          REFERENCES shop (shop_id),
                      FOREIGN KEY (category_id)
                          REFERENCES category (category_id),
                      FOREIGN KEY (deal_id)
                          REFERENCES deal (deal_id)
);
CREATE TABLE customer (

                          customer_id INT AUTO_INCREMENT PRIMARY KEY,
                          customer_code nVARCHAR(150) UNIQUE,
                          customer_name nVARCHAR(150) ,

                          customer_phone nVARCHAR(150) UNIQUE,
                          customer_address nVARCHAR(150) ,
                          customer_email nVARCHAR(150) UNIQUE,
                          customer_account nVARCHAR(150) UNIQUE,
                          customer_password nVARCHAR(150),
                          status bit
);



CREATE TABLE bill (

                      bill_id INT AUTO_INCREMENT PRIMARY KEY,
                      bill_code nVARCHAR(150) NOT NULL UNIQUE,
                      bill_date DATETIME NOT NULL,
                      bill_totalCost DOUBLE NOT NULL,
                      customer_id INT NOT NULL,
                      shop_id INT NOT NULL,
                      status BIT NOT NULL,
                      FOREIGN KEY (customer_id)
                          REFERENCES customer (customer_id),
                      FOREIGN KEY (shop_id)
                          REFERENCES shop (shop_id)
);


CREATE TABLE bill_detail (
                             bill_detail_id int auto_increment primary key ,
                             bill_id INT ,
                             item_id INT,
                             quantity INT NOT NULL,
                             price DOUBLE NOT NULL,
                             status bit,
                             FOREIGN KEY(bill_id) REFERENCES bill(bill_id),
                             FOREIGN KEY(item_id) REFERENCES item(item_id)
);


ALTER TABLE category
    ADD CONSTRAINT fk_item_category
        FOREIGN KEY (item_id)
            REFERENCES item (item_id);

SELECT * FROM shop;
INSERT INTO shop VALUES (1,'s1','sunny','sunny@gmail.com','0916287678','89 nguy???n ho??ng','a','123456','s01.jpg',1);
INSERT INTO shop VALUES (2,'s2','n???ng m???i','nangmoi@gmail.com','0916287675','39 tr???n nh??n t??ng','b','123456','s02.jpg',1);
INSERT INTO shop VALUES (3,'s3','sao mai','saomai@gmail.com','0916287674','89 t??n th???t t??ng','c','123456','s03.jpg',1);
INSERT INTO shop VALUES (4,'s4','b??nh minh','binhminh@gmail.com','0916287672','89 y???t ki???u','d','123456','s04.jpg',1);
INSERT INTO shop VALUES (5,'s5','gi??? m???i','giomoi@gmail.com','0916287671','89 ????? ?????c','f','123456','s05.jpg',1);
INSERT INTO shop VALUES (6,'s6','kimYoun','kimYoun@gmail.com','0916287670','10shop0 nguy???n ho??ng 2','g','123456','s06.jpg',1);
INSERT INTO shop VALUES (7,'s7','kimYoung','kimYoung@gmail.com','091628767','100 nguy???n ho??ng 3','h','123456','s07.jpg',1);
INSERT INTO shop VALUES (8,'s8','kimoung','kimYo@gmail.com','09162876','100 nguy???n ho??ng 3','k','123456','s08.jpg',1);
INSERT INTO shop VALUES (9,'s9','kimYn','mYoung@gmail.com','09162867','100 nguy???n ho??ng 3','i','123456','s09.jpg',1);

select * from category;
INSERT INTO category values(1,'ctg1','????? ??n','??n nh???ng ????? ??n ngon',null,1);
INSERT INTO category values(2,'ctg2','????? u???ng','u???ng nh???ng ????? u???ng ngon',null,1);
INSERT INTO category values(3,'ctg3','????? chay','??n nh???ng ????? ??n l??nh m???nh',null,1);
INSERT INTO category values(4,'ctg4','b??nh kem','??n nh???ng ????? ??n l???nh',null,1);
INSERT INTO category values(5,'ctg5','tr??ng mi???ng','??n nh???ng ????? ??n nh??? ngon',null,1);
INSERT INTO category values(6,'ctg6','c??m h???p','??n nh???ng ????? ??n ngon lo b???ng',null,1);
INSERT INTO category values(7,'ctg7','m?? ph???','??n nh???ng ????? ??n ngon nh???',null,1);

select * from deal;
insert into deal values(1,'d1','Ti???c sang gi?? x???n gi???m gi?? m???nh','2022-11-05 20:29:36','2022-11-06 14:29:36','??n th??? ga, kh??ng lo v??? gi??','d01.png',1);
insert into deal values(2,'d2','M??n g?? c??ng free ship','2022-11-05 20:29:36','2022-11-06 14:29:36','??n th??? ga, kh??ng lo v??? gi??','d02.png',1);
insert into deal values(3,'d3','T???ng 1 lon coca khi mua combo 125k','2022-11-05 20:29:36','2022-11-06 14:29:36','??n th??? ga, kh??ng lo v??? gi??','d03.png',1);
insert into deal  values(4,'d4','Free ship t???i 25k','2022-11-05 20:29:36','2022-11-06 14:29:36','??n th??? ga, kh??ng lo v??? gi??','d04.png',1);
insert into deal  values(5,'d5','Si??u ti???c gi???m 99k','2022-11-05 20:29:36','2022-11-06 14:29:36','??n th??? ga, kh??ng lo v??? gi??','d05.png',1);
insert into deal  values(6,'d6','Si??u ti???c 0 ?????ng','2022-11-05 20:29:36','2022-11-06 14:29:36','??n th??? ga, kh??ng lo v??? gi??','d06.png',0);
insert into deal  values(7,'d7','Th??? 3 sale c???c m???nh','2022-11-05 20:29:36','2022-11-06 14:29:36','??n th??? ga, kh??ng lo v??? gi??','d07.png',1);
insert into deal  values(8,'d8','Th??? 4 sale c???c m???nh','2022-11-05 20:29:36','2022-11-06 14:29:36','??n th??? ga, kh??ng lo v??? gi??','d08.png',0);
insert into deal  values(9,'d9','Th??? 5 sale c???c m???nh','2022-11-05 20:29:36','2022-11-06 14:29:36','??n th??? ga, kh??ng lo v??? gi??','d09.png',1);

select * from item;
INSERT INTO item VALUE (1,'it1',1,1,1,'Ch??? C???m',35000,'Ch??? c???m l??m t??? c???m kh?? ngon','da01.jpg',1);
INSERT INTO item VALUE (2,'it2',2,2,2,'Tr?? S???a',40000,'Tr?? s???a l??m t??? s???a t????i ','da01.jpg',1);
INSERT INTO item VALUE (3,'it3',3,3,3,'G???i Cu???n Di???u ??m',45000,'G???i cu???n chay thu???n chay','da01.jpg',1);
INSERT INTO item VALUE (4,'it4',4,4,4,'B??nh S??u G?? Cay Phomai',35000,'B??nh s??u l??m t??? b???t v?? s??u','da01.jpg',1);
INSERT INTO item  VALUE (5,'it5',5,5,5,'Ch?? ?????u ????? c???t d???a',35000,'Ch?? ?????u ????? l??m t??? ?????u ?????','da01.jpg',1);
INSERT INTO item  VALUE (6,'it6',6,6,6,'C??m th???',35000,'C??m th??? l??m t??? c??m ???????c chi??n','da01.jpg',1);
INSERT INTO item  VALUE (7,'it7',7,7,7,'Ph??? ch??n',35000,'Ph??? ch??n l?? t??? ph??? g???o nguy??n ch???t','da01.jpg',1);
INSERT INTO item VALUE (8,'it8',1,1,1,'Ch??? C???',35000,'Ch??? c???m l??m t??? c???m kh?? ngon','da01.jpg',1);
INSERT INTO item VALUE (9,'it9',1,1,1,'Ch??? Cm',35000,'Ch??? c???m l??m t??? c???m kh?? ngon','da01.jpg',1);
INSERT INTO item VALUE (10,'it10',1,1,1,'Ch C???m',35000,'Ch??? c???m l??m t??? c???m kh?? ngon','da01.jpg',1);
select * from customer;
INSERT INTO customer  values(1,'kh01','Nguy???n H???u Quy???t','0393883210','B???c Ninh','nguyenhuuquyet07092001@gmail.com','quyet','123456',1);
INSERT INTO customer  values(2,'kh02','Nguy???n V??n Ph????ng','0993883210','B???c Giang','nguyenvanphuong@gmail.com','quyet1','123456',1);
INSERT INTO customer values(3,'kh03','Nguy???n Ho??ng Hi???p','0893883210','Thanh H??a','nguyenhuuq@gmail.com','quyet2','123456',1);
INSERT INTO customer  values(4,'kh04','Nguy???n V??n D??nh','0397883210','Ngh??? An','nguyenhuuquye07092001@gmail.com','quye3','123456',1);
INSERT INTO customer  values(5,'kh05','Nguy???n H???u Nam','0396883210','H?? T??nh','nguyenhuuqt92001@gmail.com','quyet4','123456',1);
INSERT INTO customer values(6,'kh06','Nguy???n H???u Anh','0593883210','Qu???ng Ninh','nguyeuquyet07092001@gmail.com','quyet5','123456',1);
INSERT INTO customer  values(7,'kh07','Nguy???n H???u Vy','0493883210','B???c Ninh','nnhuuquyet07092001@gmail.com','quyet6','123456',1);
select * from bill;
INSERT INTO bill(bill_id,bill_code, customer_id, shop_id, bill_date, bill_totalCost, status) VALUE (1,'bill01',1,1,'2022-8-30 20:29:36',125.500,1);
INSERT INTO bill(bill_id,bill_code, customer_id, shop_id, bill_date, bill_totalCost, status) VALUE (2,'bill02',1,2,'2022-8-30 19:29:36',125.500,1);
INSERT INTO bill(bill_id,bill_code, customer_id, shop_id, bill_date, bill_totalCost, status) VALUE (3,'bill03',1,3,'2022-8-30 18:29:36',125.500,1);
INSERT INTO bill(bill_id,bill_code, customer_id, shop_id, bill_date, bill_totalCost, status) VALUE (4,'bill04',1,4,'2022-8-30 17:29:36',125.500,1);
INSERT INTO bill(bill_id,bill_code, customer_id, shop_id, bill_date, bill_totalCost, status) VALUE (5,'bill05',1,5,'2022-8-30 16:29:36',125.500,1);
INSERT INTO bill(bill_id,bill_code, customer_id, shop_id, bill_date, bill_totalCost, status) VALUE (6,'bill06',1,6,'2022-8-30 15:29:36',125.500,1);
INSERT INTO bill(bill_id,bill_code, customer_id, shop_id, bill_date, bill_totalCost, status) VALUE (7,'bill07',1,7,'2022-8-30 14:29:36',125.500,1);
select  * from bill_detail;
INSERT INTO bill_detail values (1,1,1,10,350000,1);
INSERT INTO bill_detail values (2,2,2,10,400000,1);
INSERT INTO bill_detail values (3,3,3,10,450000,1);
INSERT INTO bill_detail values (4,4,4,10,350000,1);
INSERT INTO bill_detail values (5,5,5,10,350000,1);
INSERT INTO bill_detail values (6,6,6,10,350000,1);
INSERT INTO bill_detail values (7,7,7,10,350000,1);

CREATE TABLE item_category(
                              item_id int,
                              category_id int,
                              FOREIGN KEY(item_id) REFERENCES item(item_id),
                              FOREIGN KEY(category_id) REFERENCES category(category_id)
);

insert into item_category value (1,1);
insert into item_category value (1,2);
insert into item_category value (1,3);
insert into item_category value (1,4);
insert into item_category value (2,2);
insert into item_category value (4,2);
insert into item_category value (3,5);
insert into item_category value (5,3);
insert into item_category value (6,3);
insert into item_category value (6,2);


CREATE TABLE order(
    id int primary key auto_increment,
    custome_id int,
    item_id int,
    status BIT,
    quantityItem int
)