create table t_video(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  uploadTime DATETIME not null,
  termCode VARCHAR(50) ,
  seconds INT ,
  sizeKb INT ,
  videoName VARCHAR(255),
  description  VARCHAR(250),
  picPath VARCHAR (60),
  savePath VARCHAR (60),
  viewCount tinyint(3) default 0,
  state tinyint(2) default 0
) DEFAULT CHARSET=utf8 ;

create table t_audio(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  uploadTime DATETIME not null,
  termCode VARCHAR(50) ,
  seconds INT ,
  sizeKb INT ,
  audioName VARCHAR(255),
  description  VARCHAR(250),
  savePath VARCHAR (60),
  viewCount tinyint(3) default 0,
  state tinyint(2) default 0
) DEFAULT CHARSET=utf8 ;



create table t_image(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  uploadTime DATETIME not null,
  termCode VARCHAR(50) ,
  sizeKb INT ,
  imageName VARCHAR(255),
  description  VARCHAR(250),
  picPath VARCHAR (60),
  savePath VARCHAR (60),
  viewCount tinyint(3) default 0
) DEFAULT CHARSET=utf8 ;
