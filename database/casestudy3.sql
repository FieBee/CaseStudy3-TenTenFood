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
INSERT INTO shop VALUES (1,'s1','sunny','sunny@gmail.com','0916287678','89 nguyễn hoàng','a','123456','s01.jpg',1);
INSERT INTO shop VALUES (2,'s2','nắng mới','nangmoi@gmail.com','0916287675','39 trần nhân tông','b','123456','s01.jpg',1);
INSERT INTO shop VALUES (3,'s3','sao mai','saomai@gmail.com','0916287674','89 tôn thất tùng','c','123456','s01.jpg',1);
INSERT INTO shop VALUES (4,'s4','bình minh','binhminh@gmail.com','0916287672','89 yết kiều','d','123456','s01.jpg',1);
INSERT INTO shop VALUES (5,'s5','giờ mới','giomoi@gmail.com','0916287671','89 đỗ đức','f','123456','s01.jpg',1);
INSERT INTO shop VALUES (6,'s6','kimYoun','kimYoun@gmail.com','0916287670','10shop0 nguyễn hoàng 2','g','123456','s01.jpg',1);
INSERT INTO shop VALUES (7,'s7','kimYoung','kimYoung@gmail.com','091628767','100 nguyễn hoàng 3','h','123456','s01.jpg',1);

select * from category;
INSERT INTO category values(1,'ctg1','đồ ăn','ăn những đồ ăn ngon',null,1);
INSERT INTO category values(2,'ctg2','đồ uống','uống những đồ uống ngon',null,1);
INSERT INTO category values(3,'ctg3','đồ chay','ăn những đồ ăn lành mạnh',null,1);
INSERT INTO category values(4,'ctg4','bánh kem','ăn những đồ ăn lạnh',null,1);
INSERT INTO category values(5,'ctg5','tráng miệng','ăn những đồ ăn nhẹ ngon',null,1);
INSERT INTO category values(6,'ctg6','cơm hộp','ăn những đồ ăn ngon lo bụng',null,1);
INSERT INTO category values(7,'ctg7','mì phở','ăn những đồ ăn ngon nhẹ',null,1);

select * from deal;
insert into deal values(1,'d1','Tiệc sang giá xịn giảm giá mạnh','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d01.jpg',1);
insert into deal values(2,'d2','Món gì cũng free ship','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d02.jpg',1);
insert into deal values(3,'d3','Tặng 1 lon coca khi mua combo 125k','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d03.jpg',1);
insert into deal  values(4,'d4','Free ship tới 25k','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d04.jpg',1);
insert into deal  values(5,'d5','Siêu tiệc giảm 99k','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d05.jpg',1);
insert into deal  values(6,'d6','Siêu tiệc 0 đồng','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d06.jpg',1);
insert into deal  values(7,'d7','Thứ 3 sale cực mạnh','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d07.jpg',1);

select * from item;
INSERT INTO item VALUE (1,'it1',1,1,1,'Chả Cốm',35000,'Chả cốm làm từ cốm khá ngon','da01.jpg',1);
INSERT INTO item VALUE (2,'it2',2,2,2,'Trà Sữa',40000,'Trà sữa làm từ sữa tươi ','da02.jpg',1);
INSERT INTO item VALUE (3,'it3',3,3,3,'Gỏi Cuốn Diệu Âm',45000,'Gỏi cuốn chay thuần chay','da03.jpg',1);
INSERT INTO item VALUE (4,'it4',4,4,4,'Bánh Sâu Gà Cay Phomai',35000,'Bánh sâu làm từ bột và sâu','da04.jpg',1);
INSERT INTO item  VALUE (5,'it5',5,5,5,'Chè đậu đỏ cốt dừa',35000,'Chè đậu đỏ làm từ đậu đỏ','da05.jpg',1);
INSERT INTO item  VALUE (6,'it6',6,6,6,'Cơm thố',35000,'Cơm thố làm từ cơm được chiên','da06.jpg',1);
INSERT INTO item  VALUE (7,'it7',7,7,7,'Phở chín',35000,'Phở chín là từ phở gạo nguyên chất','da07.jpg',1);
select * from customer;
INSERT INTO customer  values(1,'kh01','Nguyễn Hữu Quyết','0393883210','Bắc Ninh','nguyenhuuquyet07092001@gmail.com','quyet','123456',1);
INSERT INTO customer  values(2,'kh02','Nguyễn Văn Phương','0993883210','Bắc Giang','nguyenvanphuong@gmail.com','quyet1','123456',1);
INSERT INTO customer values(3,'kh03','Nguyễn Hoàng Hiệp','0893883210','Thanh Hóa','nguyenhuuq@gmail.com','quyet2','123456',1);
INSERT INTO customer  values(4,'kh04','Nguyễn Văn Dĩnh','0397883210','Nghệ An','nguyenhuuquye07092001@gmail.com','quye3','123456',1);
INSERT INTO customer  values(5,'kh05','Nguyễn Hữu Nam','0396883210','Hà Tĩnh','nguyenhuuqt92001@gmail.com','quyet4','123456',1);
INSERT INTO customer values(6,'kh06','Nguyễn Hữu Anh','0593883210','Quảng Ninh','nguyeuquyet07092001@gmail.com','quyet5','123456',1);
INSERT INTO customer  values(7,'kh07','Nguyễn Hữu Vy','0493883210','Bắc Ninh','nnhuuquyet07092001@gmail.com','quyet6','123456',1);
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


CREATE TABLE admin(
                      account varchar(16),
                      password varchar(16)
);

INSERT INTO admin value ('admin','123456');