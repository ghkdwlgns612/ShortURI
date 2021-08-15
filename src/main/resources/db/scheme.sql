create table urlstore (
  id  varchar(255) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255),
  hashvalue varchar(255) NOT NULL,
  originurl varchar(255) NOT NULL
)