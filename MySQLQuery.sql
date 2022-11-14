create table usersyjh(
id varchar(28) primary key,
password varchar(8) not null,
name varchar(30) not null,
role varchar(5) default 'User'
)

create table boardyjh(
seq int primary key,   -- 순번
title varchar(200) not null,-- 제목
nickname varchar(30) not null, -- 이름
content varchar(2000) not null, -- 내용
regdate datetime default now(), 
cnt int default 0, -- 조회수
userid varchar(8)
)

-- 오라클 sysdate()   ->  my-sql now()

create table boardlikeyjh(
id varchar(28) not null,
seq int not null,
good int,
bad int,
isCheckCode varchar(10) default 'G0B0',  
primary key(id,seq)
)

create table replyboardyjh(
boardseq int not null, 
seq int not null,
nickname varchar(30) not null,
regdate datetime default now(), 
content varchar(2000) not null,
userid varchar(8), 
primary key(boardseq,seq) 
)


create table replyboard_2yjh(
boardseq int not null, 
seq int not null,
rseq int not null,
nickname varchar(30) not null,
regdate datetime default now(), 
content varchar(2000) not null,
userid varchar(8), 
primary key(boardseq,seq,rseq) 
)

select * from usersyjh


-- 게시판 리스트 검색 -> 오라클
select * from (select rownum as rnum,B.* from (select seq,title,nickname,content,date_format(regdate,'%Y-%m-%d') as regdate,cnt,userid from boardyjh order by seq desc) B) where rnum between ? and ?

-- Limit연산 사용하여 페이징 처리 ->My SQL
select seq,title,nickname,content,date_format(regdate,'%Y-%m-%d') 
as regdate,cnt,userid from boardyjh order by seq desc limit 0,10

select seq,title,nickname,content,date_format(regdate,'%Y-%m-%d') 
as regdate,cnt,userid from boardyjh order by seq desc limit 10,10

select seq,title,nickname,content,date_format(regdate,'%Y-%m-%d') 
as regdate,cnt,userid from boardyjh order by seq desc limit 20,10


-- 페이징 처리 테스트 레코드 삽입
insert into boardyjh(seq,title,nickname,content,regdate,userid) 
values((select ifnull(max(seq),0)+1 from boardyjh subquery),'1 번째 게시물','일지매','1 번째 게시물 내용.','2022-02-01','guest')

insert into boardyjh(seq,title,nickname,content,regdate,userid) 
values((select ifnull(max(seq),0)+1 from boardyjh subquery),'13 번째 게시물','일지매','2 번째 게시물 내용.','2022-02-01','guest');
insert into boardyjh(seq,title,nickname,content,regdate,userid) 
values((select ifnull(max(seq),0)+1 from boardyjh subquery),'14 번째 게시물','일지매','3 번째 게시물 내용.','2022-02-01','guest');
insert into boardyjh(seq,title,nickname,content,regdate,userid) 
values((select ifnull(max(seq),0)+1 from boardyjh subquery),'15 번째 게시물','일지매','4 번째 게시물 내용.','2022-02-01','guest');
insert into boardyjh(seq,title,nickname,content,regdate,userid) 
values((select ifnull(max(seq),0)+1 from boardyjh subquery),'16 번째 게시물','일지매','5 번째 게시물 내용.','2022-02-01','guest');
insert into boardyjh(seq,title,nickname,content,regdate,userid) 
values((select ifnull(max(seq),0)+1 from boardyjh subquery),'17 번째 게시물','일지매','6 번째 게시물 내용.','2022-02-01','guest');
insert into boardyjh(seq,title,nickname,content,regdate,userid) 
values((select ifnull(max(seq),0)+1 from boardyjh subquery),'18 번째 게시물','일지매','7 번째 게시물 내용.','2022-02-01','guest');
insert into boardyjh(seq,title,nickname,content,regdate,userid) 
values((select ifnull(max(seq),0)+1 from boardyjh subquery),'19 번째 게시물','일지매','8 번째 게시물 내용.','2022-02-01','guest');
insert into boardyjh(seq,title,nickname,content,regdate,userid) 
values((select ifnull(max(seq),0)+1 from boardyjh subquery),'20 번째 게시물','일지매','9 번째 게시물 내용.','2022-02-01','guest');
insert into boardyjh(seq,title,nickname,content,regdate,userid) 
values((select ifnull(max(seq),0)+1 from boardyjh subquery),'21 번째 게시물','일지매','10 번째 게시물 내용.','2022-02-01','guest');
insert into boardyjh(seq,title,nickname,content,regdate,userid) 
values((select ifnull(max(seq),0)+1 from boardyjh subquery),'22 번째 게시물','일지매','11 번째 게시물 내용.','2022-02-01','guest');
insert into boardyjh(seq,title,nickname,content,regdate,userid) 
values((select ifnull(max(seq),0)+1 from boardyjh subquery),'23 번째 게시물','일지매','12 번째 게시물 내용.','2022-02-01','guest')


-- 게시판 글 조회
select * from boardyjh


-- 게시글 검색 조회
select seq,title,nickname,content,date_format(regdate,'%Y-%m-%d') as regdate,cnt,userid from boardyjh
where like Concat('%' , IFNULL(?, '') , '%') order by seq desc

select seq,title,nickname,content,date_format(regdate,'%Y-%m-%d') as regdate,cnt,userid from boardyjh
where like Concat('%' , '게시물' , '%') order by seq desc

select seq,title,nickname,content,date_format(regdate,'%Y-%m-%d') as regdate,cnt,userid from boardyjh
where ? like Concat('%' , IFNULL(?, '') , '%') order by seq desc





