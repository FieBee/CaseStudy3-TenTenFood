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
                      shop_password nVARCHAR(150) NOT NULL
);
CREATE TABLE category (
                          category_id INT AUTO_INCREMENT PRIMARY KEY,
                          category_code nVARCHAR(150) NOT NULL UNIQUE,
                          category_name nVARCHAR(150) NOT NULL,
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
                             bill_detail_id INT ,
                             item_id INT,
                             quantity INT NOT NULL,
                             price DOUBLE NOT NULL,
                             PRIMARY KEY(bill_detail_id,item_id),
                             FOREIGN KEY(bill_detail_id) REFERENCES bill(bill_id),
                             FOREIGN KEY(item_id) REFERENCES item(item_id)
);
ALTER TABLE category
    ADD CONSTRAINT fk_item_category
        FOREIGN KEY (item_id)
            REFERENCES item (item_id);

SELECT * FROM shop;
INSERT INTO shop(shop_code,shop_name,shop_email,shop_phone,shop_address,shop_account,shop_password) VALUES ('s1','sunny','sunny@gmail.com','0916287678','89 nguyễn hoàng','a','123456');
INSERT INTO shop(shop_code,shop_name,shop_email,shop_phone,shop_address,shop_account,shop_password) VALUES ('s2','food','nangmoi@gmail.com','0916287675','39 trần nhân tông','b','123456');
INSERT INTO shop(shop_code,shop_name,shop_email,shop_phone,shop_address,shop_account,shop_password) VALUES ('s3','sammy','saomai@gmail.com','0916287674','89 tôn thất tùng','c','123456');
INSERT INTO shop(shop_code,shop_name,shop_email,shop_phone,shop_address,shop_account,shop_password) VALUES ('s4','funny','binhminh@gmail.com','0916287672','89 yết kiều','d','123456');
INSERT INTO shop(shop_code,shop_name,shop_email,shop_phone,shop_address,shop_account,shop_password) VALUES ('s5','cookie','giomoi@gmail.com','0916287671','89 đỗ đức','f','123456');
INSERT INTO shop(shop_code,shop_name,shop_email,shop_phone,shop_address,shop_account,shop_password) VALUES ('s6','kimYoun','bihminh@gmail.com','0916287670','100 nguyễn hoàng 2','g','123456');

select * from category;
