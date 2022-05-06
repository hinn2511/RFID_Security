use rfid;

CREATE TABLE `productline` (
  `product_line_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `stock` int NOT NULL
);

CREATE TABLE `tag` (
  `tag_id` varchar(255) NOT NULL,
  `product_line_id` varchar(255) NOT NULL,
  `is_purchased` tinyint(1) NOT NULL
);


CREATE TABLE `log` (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `tag_id` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `gate_number` int(10) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (log_id)
); 

ALTER TABLE `productline`
  ADD PRIMARY KEY (`product_line_id`);

ALTER TABLE `tag`
  ADD PRIMARY KEY (`tag_id`);

ALTER TABLE `tag`
  ADD CONSTRAINT `tag_ibfk_1` FOREIGN KEY (`product_line_id`) REFERENCES `productline` (`product_line_id`);
  
ALTER TABLE `log`
  ADD CONSTRAINT `log_ibfk_1` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`);

INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000001', 'iPhone 12 Pro Max 128GB', 1099, '8');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000002', 'iPhone 12 256GB ', 749, '8');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000003', 'Xiaomi 11 Youth ', 315, '5');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000004', 'Xiaomi 12', 570, '5');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000005', 'Sonny Xperia 5', 750, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000006', 'Sony Xperia Pro', 2450, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000007', 'Samsung Galaxy F10 Lite', 499, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000008', 'Samsung Galaxy Z Flip 3', 1230, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000009', 'Oppo A56 5G', 250, '2');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000010', 'Oppo Reno7 4G', 359, '2');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000011', 'iPhone SE 2022', 429, '8');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000012', 'iPhone 13 512GB', 1299, '8');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000013', 'Xiaomi 12X ', 470, '5');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000014', 'Xiaomi 11i', 335, '5');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000015', 'Huawei P50E', 620, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000016', 'Huawei Nova 8', 550, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000017', 'Samsung Galaxy S20 Fe 2022', 575, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000018', 'Samsung Galaxy M33', 380, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000019', 'Oppo K10 Pro', 385, '2');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000020', 'Oppo F21 Pro 5g', 355, '2');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000021', 'iPhone 12 Pro Max 128GB', 1099, '8');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000022', 'iPhone 12 256GB ', 749, '8');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000023', 'Xiaomi 11 Youth ', 315, '5');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000024', 'Xiaomi 12', 570, '5');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000025', 'Sonny Xperia 5', 750, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000026', 'Sony Xperia Pro', 2450, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000027', 'Samsung Galaxy F10 Lite', 499, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000028', 'Samsung Galaxy Z Flip 3', 1230, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000029', 'Oppo A56 5G', 250, '2');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000030', 'Oppo Reno7 4G', 359, '2');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000031', 'iPhone SE 2022', 429, '8');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000032', 'iPhone 13 512GB', 1299, '8');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000033', 'Xiaomi 12X ', 470, '5');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000034', 'Xiaomi 11i', 335, '5');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000035', 'Huawei P50E', 620, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000036', 'Huawei Nova 8', 550, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000037', 'Samsung Galaxy S20 Fe 2022', 575, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000038', 'Samsung Galaxy M33', 380, '3');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000039', 'Oppo K10 Pro', 385, '2');
INSERT INTO `productline` (`product_line_id`, `name`, `price`, `stock`) VALUES('PL000040', 'Oppo F21 Pro 5g', 355, '2');


INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('00B0 7A14 2C2B 2848 0800 0166', 'PL000002', 1);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('1041 7A15 2C33 2848 6940 0U75', 'PL000012', 0);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('1670 4UP2 24CB 32U4 58CD C843', 'PL000008', 1);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('6830 1160 6000 GF4U 67CD 35MQ', 'PL000014', 0);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('CCCC 1355 DDRR 0209 58CD DFJD', 'PL000038', 1);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('CD81 1360 65R7 0267 58CD D76D', 'PL000018', 1);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('D680 5748 TJ10 294J 58CD F86E', 'PL000003', 1);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('E200 4106 240B 0075 1320 9168', 'PL000001', 1);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('E230 4106 240B 0275 1320 9533', 'PL000005', 1);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('E246 485J F564 54UF 53CD D4MD', 'PL000017', 1);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('E280 1160 6000 0209 58CD DE5D', 'PL000031', 0);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('E280 1160 6000 0209 58CD F86E', 'PL000006', 0);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('EEEE 1IT0 4EB5 0209 E564 DE5D', 'PL000023', 1);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('FNRI BFHU 45TG RFB2 58CD 45UD', 'PL000040', 1);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('G350 1R50 6TY0 0E09 48CD D84D', 'PL000026', 0);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('GGU5 Y2NT 6BG0 T8NU 383D D8MD', 'PL000034', 0);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('GH24 1160 FG86 0209 E34G D241', 'PL000030', 0);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('H400 58DH 7853 0209 74H1 24NG', 'PL000022', 0);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('H541 7A15 2C2B 2848 0800 78BF', 'PL000011', 1);
INSERT INTO `tag` (`tag_id`, `product_line_id`, `is_purchased`) VALUES('P293 2456 46GD G14E 245D D45E', 'PL000025', 0);