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
                      shop_image nvarchar(150)
);
CREATE TABLE category (
                          category_id INT AUTO_INCREMENT PRIMARY KEY,
                          category_code nVARCHAR(150) NOT NULL UNIQUE,
                          category_name nVARCHAR(150) NOT NULL unique,
                          category_description nVARCHAR(150),
                          item_id INT
);
CREATE TABLE deal (
                      deal_id INT AUTO_INCREMENT PRIMARY KEY,
                      deal_code nVARCHAR(150) NOT NULL UNIQUE,
                      deal_name nVARCHAR(150) NOT NULL,
                      deal_startDate DATETIME,
                      deal_endDate DATETIME,
                      deal_description nVARCHAR(150),
                      deal_image nVARCHAR(150)
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
                      FOREIGN KEY (shop_id)
                          REFERENCES shop (shop_id),
                      FOREIGN KEY (category_id)
                          REFERENCES category (category_id),
                      FOREIGN KEY (deal_id)
                          REFERENCES deal (deal_id)
);
CREATE TABLE customer (
                          customer_id INT AUTO_INCREMENT PRIMARY KEY,
                          customer_code nVARCHAR(150) NOT NULL UNIQUE,
                          customer_name nVARCHAR(150) NOT NULL,
                          customer_phone nVARCHAR(150) NOT NULL UNIQUE,
                          customer_address nVARCHAR(150) NOT NULL,
                          customer_email nVARCHAR(150) NOT NULL UNIQUE,
                          customer_account nVARCHAR(150) NOT NULL UNIQUE,
                          customer_password nVARCHAR(150) NOT NULL
);



CREATE TABLE bill (
                      bill_id INT AUTO_INCREMENT PRIMARY KEY,
                      bill_code nVARCHAR(150) NOT NULL UNIQUE,
                      status BIT NOT NULL,
                      bill_date DATETIME NOT NULL,
                      bill_totalCost DOUBLE NOT NULL,
                      customer_id INT NOT NULL,
                      shop_id INT NOT NULL,
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
                             FOREIGN KEY(bill_id) REFERENCES bill(bill_id),
                             FOREIGN KEY(item_id) REFERENCES item(item_id)
);
ALTER TABLE category
    ADD CONSTRAINT fk_item_category
        FOREIGN KEY (item_id)
            REFERENCES item (item_id);

SELECT * FROM shop;
INSERT INTO shop(shop_code,shop_name,shop_email,shop_phone,shop_address,shop_account,shop_password,shop_image) VALUES ('s1','sunny','sunny@gmail.com','0916287678','89 nguyễn hoàng','a','123456','s01.jpg');
INSERT INTO shop(shop_code,shop_name,shop_email,shop_phone,shop_address,shop_account,shop_password,shop_image) VALUES ('s2','nắng mới','nangmoi@gmail.com','0916287675','39 trần nhân tông','b','123456','s01.jpg');
INSERT INTO shop(shop_code,shop_name,shop_email,shop_phone,shop_address,shop_account,shop_password,shop_image) VALUES ('s3','sao mai','saomai@gmail.com','0916287674','89 tôn thất tùng','c','123456','s01.jpg');
INSERT INTO shop(shop_code,shop_name,shop_email,shop_phone,shop_address,shop_account,shop_password,shop_image) VALUES ('s4','bình minh','binhminh@gmail.com','0916287672','89 yết kiều','d','123456','s01.jpg');
INSERT INTO shop(shop_code,shop_name,shop_email,shop_phone,shop_address,shop_account,shop_password,shop_image) VALUES ('s5','giờ mới','giomoi@gmail.com','0916287671','89 đỗ đức','f','123456','s01.jpg');
INSERT INTO shop(shop_code,shop_name,shop_email,shop_phone,shop_address,shop_account,shop_password,shop_image) VALUES ('s6','kimYoun','kimYoun@gmail.com','0916287670','100 nguyễn hoàng 2','g','123456','s01.jpg');
INSERT INTO shop(shop_code,shop_name,shop_email,shop_phone,shop_address,shop_account,shop_password,shop_image) VALUES ('s7','kimYoung','kimYoung@gmail.com','091628767','100 nguyễn hoàng 3','h','123456','s01.jpg');

select * from category;
INSERT INTO category(category_code, category_name, category_description, item_id)values('ctg1','đồ ăn','ăn những đồ ăn ngon',null);
INSERT INTO category(category_code, category_name, category_description, item_id)values('ctg2','đồ uống','uống những đồ uống ngon',null);
INSERT INTO category(category_code, category_name, category_description, item_id)values('ctg3','đồ chay','ăn những đồ ăn lành mạnh',null);
INSERT INTO category(category_code, category_name, category_description, item_id)values('ctg4','bánh kem','ăn những đồ ăn lạnh',null);
INSERT INTO category(category_code, category_name, category_description, item_id)values('ctg5','tráng miệng','ăn những đồ ăn nhẹ ngon',null);
INSERT INTO category(category_code, category_name, category_description, item_id)values('ctg6','cơm hộp','ăn những đồ ăn ngon lo bụng',null);
INSERT INTO category(category_code, category_name, category_description, item_id)values('ctg7','mì phở','ăn những đồ ăn ngon nhẹ',null);

select * from deal;
insert into deal(deal_code, deal_name, deal_startDate, deal_endDate, deal_description, deal_image) values('d1','Tiệc sang giá xịn giảm giá mạnh','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d01.jpg');
insert into deal(deal_code, deal_name, deal_startDate, deal_endDate, deal_description, deal_image) values('d2','Món gì cũng free ship','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d02.jpg');
insert into deal(deal_code, deal_name, deal_startDate, deal_endDate, deal_description, deal_image) values('d3','Tặng 1 lon coca khi mua combo 125k','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d03.jpg');
insert into deal(deal_code, deal_name, deal_startDate, deal_endDate, deal_description, deal_image) values('d4','Free ship tới 25k','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d04.jpg');
insert into deal(deal_code, deal_name, deal_startDate, deal_endDate, deal_description, deal_image) values('d5','Siêu tiệc giảm 99k','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d05.jpg');
insert into deal(deal_code, deal_name, deal_startDate, deal_endDate, deal_description, deal_image) values('d6','Siêu tiệc 0 đồng','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d06.jpg');
insert into deal(deal_code, deal_name, deal_startDate, deal_endDate, deal_description, deal_image) values('d7','Thứ 3 sale cực mạnh','2022-11-05 20:29:36','2022-11-06 14:29:36','Ăn thả ga, không lo về giá','d07.jpg');
select * from item;
INSERT INTO item(item_code, shop_id, category_id, deal_id, item_name, item_price, item_description, item_image) VALUE ('it1',1,1,1,'Chả Cốm',35000,'Chả cốm làm từ cốm khá ngon','da01.jpg');
INSERT INTO item(item_code, shop_id, category_id, deal_id, item_name, item_price, item_description, item_image) VALUE ('it2',2,2,2,'Trà Sữa',40000,'Trà sữa làm từ sữa tươi ','da02.jpg');
INSERT INTO item(item_code, shop_id, category_id, deal_id, item_name, item_price, item_description, item_image) VALUE ('it3',3,3,3,'Gỏi Cuốn Diệu Âm',45000,'Gỏi cuốn chay thuần chay','da03.jpg');
INSERT INTO item(item_code, shop_id, category_id, deal_id, item_name, item_price, item_description, item_image) VALUE ('it4',4,4,4,'Bánh Sâu Gà Cay Phomai',35000,'Bánh sâu làm từ bột và sâu','da04.jpg');
INSERT INTO item(item_code, shop_id, category_id, deal_id, item_name, item_price, item_description, item_image) VALUE ('it5',5,5,5,'Chè đậu đỏ cốt dừa',35000,'Chè đậu đỏ làm từ đậu đỏ','da05.jpg');
INSERT INTO item(item_code, shop_id, category_id, deal_id, item_name, item_price, item_description, item_image) VALUE ('it6',6,6,6,'Cơm thố',35000,'Cơm thố làm từ cơm được chiên','da06.jpg');
INSERT INTO item(item_code, shop_id, category_id, deal_id, item_name, item_price, item_description, item_image) VALUE ('it7',7,7,7,'Phở chín',35000,'Phở chín là từ phở gạo nguyên chất','da07.jpg');
select * from customer;
INSERT INTO customer(customer_code, customer_name, customer_phone, customer_address, customer_email, customer_account, customer_password) values('kh01','Nguyễn Hữu Quyết','0393883210','Bắc Ninh','nguyenhuuquyet07092001@gmail.com','quyet','123456');
INSERT INTO customer(customer_code, customer_name, customer_phone, customer_address, customer_email, customer_account, customer_password) values('kh02','Nguyễn Văn Phương','0993883210','Bắc Giang','nguyenvanphuong@gmail.com','quyet1','123456');
INSERT INTO customer(customer_code, customer_name, customer_phone, customer_address, customer_email, customer_account, customer_password) values('kh03','Nguyễn Hoàng Hiệp','0893883210','Thanh Hóa','nguyenhuuq@gmail.com','quyet2','123456');
INSERT INTO customer(customer_code, customer_name, customer_phone, customer_address, customer_email, customer_account, customer_password) values('kh04','Nguyễn Văn Dĩnh','0397883210','Nghệ An','nguyenhuuquye07092001@gmail.com','quye3','123456');
INSERT INTO customer(customer_code, customer_name, customer_phone, customer_address, customer_email, customer_account, customer_password) values('kh05','Nguyễn Hữu Nam','0396883210','Hà Tĩnh','nguyenhuuqt92001@gmail.com','quyet4','123456');
INSERT INTO customer(customer_code, customer_name, customer_phone, customer_address, customer_email, customer_account, customer_password) values('kh06','Nguyễn Hữu Anh','0593883210','Quảng Ninh','nguyeuquyet07092001@gmail.com','quyet5','123456');
INSERT INTO customer(customer_code, customer_name, customer_phone, customer_address, customer_email, customer_account, customer_password) values('kh07','Nguyễn Hữu Vy','0493883210','Bắc Ninh','nnhuuquyet07092001@gmail.com','quyet6','123456');
select * from bill;
INSERT INTO bill(bill_code, status, customer_id, shop_id, bill_date, bill_totalCost) VALUE ('bill01',0,1,1,'2022-8-30 20:29:36',125.500);
INSERT INTO bill(bill_code, status, customer_id, shop_id, bill_date, bill_totalCost) VALUE ('bill02',1,1,2,'2022-8-30 19:29:36',125.500);
INSERT INTO bill(bill_code, status, customer_id, shop_id, bill_date, bill_totalCost) VALUE ('bill03',0,1,3,'2022-8-30 18:29:36',125.500);
INSERT INTO bill(bill_code, status, customer_id, shop_id, bill_date, bill_totalCost) VALUE ('bill04',1,1,4,'2022-8-30 17:29:36',125.500);
INSERT INTO bill(bill_code, status, customer_id, shop_id, bill_date, bill_totalCost) VALUE ('bill05',1,1,5,'2022-8-30 16:29:36',125.500);
INSERT INTO bill(bill_code, status, customer_id, shop_id, bill_date, bill_totalCost) VALUE ('bill06',0,1,6,'2022-8-30 15:29:36',125.500);
INSERT INTO bill(bill_code, status, customer_id, shop_id, bill_date, bill_totalCost) VALUE ('bill07',0,1,7,'2022-8-30 14:29:36',125.500);
select  * from bill_detail;
INSERT INTO bill_detail(bill_id, item_id, quantity, price) values (1,1,10,350000);
INSERT INTO bill_detail(bill_id, item_id, quantity, price) values (2,2,10,400000);
INSERT INTO bill_detail(bill_id, item_id, quantity, price) values (3,3,10,450000);
INSERT INTO bill_detail(bill_id, item_id, quantity, price) values (4,4,10,350000);
INSERT INTO bill_detail(bill_id, item_id, quantity, price) values (5,5,10,350000);
INSERT INTO bill_detail(bill_id, item_id, quantity, price) values (6,6,10,350000);
INSERT INTO bill_detail(bill_id, item_id, quantity, price) values (7,7,10,350000);

CREATE TABLE book_category(
    book_id int,
    category_id int

);