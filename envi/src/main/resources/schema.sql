--每次程序启动时会运行该SQL文件，用以改变数据库的结构
create table if not exists USER (
USE_ID int not null primary key auto_increment,
USE_NAME varchar(100),
USE_SEX varchar(1),
USE_AGE NUMBER(3),
USE_ID_NO VARCHAR(18),
USE_PHONE_NUM VARCHAR(11),
USE_EMAIL VARCHAR(100),
CREATE_TIME DATE,
MODIFY_TIME DATE,
USE_STATE VARCHAR(1));