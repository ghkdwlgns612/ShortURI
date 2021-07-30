<--클리어기능-->
drop table uristore

<--서버 재가동 시 새로운 테이블생성-->
create table uristore
(
    seq INT AUTO_INCREMENT  PRIMARY KEY,
    originuri varchar(255) not null,
    changeduri varchar(255) not null
);