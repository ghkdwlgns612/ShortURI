DROP TABLE URLSTORE;

create table urlstore (
  id  varchar(255) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255),
  hashvalue varchar(255) NOT NULL,
  originurl varchar(255) NOT NULL,
  count int
)

INSERT INTO urlstore (id, name,hashvalue,originurl,count) values (null,'jihuhhwan','1234512345','daum.net',0);
INSERT INTO urlstore (id, name,hashvalue,originurl,count) values (null,'jihuhhwan','5678956789','https://profile.intra.42.fr/',0);