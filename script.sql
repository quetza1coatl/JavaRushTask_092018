USE test;

DROP TABLE IF EXISTS part;
CREATE TABLE part(
id INT(11) NOT NULL AUTO_INCREMENT,
`type` VARCHAR(255),
is_necessary BIT(1),
quantity INT(11),
PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
GRANT ALL ON test.* TO 'root'@'localhost';
INSERT INTO part (id, `type`, is_necessary,quantity)
	VALUES
		(1,'Материнская плата',1,9),
		(2,'Дисковод CD RW',0,2),
		(3,'Звуковая карта',0,7),
		(4,'Процессор',1,13),
		(5,'Wi-Fi адаптер',0,2),
		(6,'Веб-камера',0,4),
		(7,'Сетевой адаптер',0,6),
		(8,'Корпус',1,15),
		(9,'Оперативная память',1,17),
		(10,'Жесткий диск HDD',1,22),
		(11,'Диск SSD',0,1),
		(12,'Клавиатура',0,3),
		(13,'Модем',0,7),
		(14,'Подсветка корпуса',0,11),
		(15,'Блок питания',1,14),
		(16,'Дисковод 3.5',0,1),
		(17,'Адаптер SD',0,12),
		(18,'Дисковод DVD RW',0,7),
		(19,'Видеокарта',1,18),
		(20,'Кулер для винчестера',0,3),
		(21,'Blue-ray привод',0,1),
		(22,'Кулер процессора',1,12),
		(23,'Внешний жесткий диск',0,17),
		(24,'ИБП',0,3),
		(25,'Адаптер USB 3.1',0,2);