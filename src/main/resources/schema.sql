CREATE TABLE IF NOT EXISTS uuser(
	id INT  AUTO_INCREMENT,
	uname VARCHAR(20) NOT NULL UNIQUE,
	upass VARCHAR(20) NOT NULL,
	email VARCHAR(50),
	sex VARCHAR(5) DEFAULT '保密',
	born VARCHAR(20),
	address VARCHAR(50),
	profession VARCHAR(20),
	udescribe VARCHAR(500),
	website VARCHAR(50),
	headpic VARCHAR(50)  DEFAULT 'default.jpg',
	ulevel INT DEFAULT 1,
	realname varchar(50),
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
COMMIT;

CREATE TABLE IF NOT EXISTS admin(
   id int  not null AUTO_INCREMENT,
   aname VARCHAR(50) not null ,
   apass VARCHAR(50) not null,
   pic mediumblob COMMENT '图片',
   telephone VARCHAR(20) ,
   email VARCHAR(50) ,
   role VARCHAR(50),
   begintime datetime,
   status int ,
   PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
COMMIT;


CREATE TABLE IF NOT EXISTS type(
   id int  not null AUTO_INCREMENT,
   name VARCHAR(50) not null ,
   number int default 0,
   PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
COMMIT;


CREATE TABLE IF NOT EXISTS blog(
   id int  not null AUTO_INCREMENT,
   name VARCHAR(50) not null,
   title VARCHAR(50) not null ,
   types VARCHAR(50) not null,
   createtime datetime ,
   updatetime datetime,
   content longtext ,
   firstpic varchar(255),
   views int default 1,
   flag varchar(50),
   PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
COMMIT;


CREATE TABLE IF NOT EXISTS content(
   id int  not null AUTO_INCREMENT,
   blogid int not null,
   pic mediumblob COMMENT '图片',
   name VARCHAR(50) not null,
   createtime datetime ,
   content VARCHAR(255) ,
   PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
COMMIT;

