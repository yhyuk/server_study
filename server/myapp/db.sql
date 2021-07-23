-- db.sql
-- 게시판 만들기


-- 회원 테이블
select *from tblUsers;

-- 게시판 테이블 > 단계 + 확장
-- 기본 게시판

drop sequence seqBoard;


create table tblBoards (
    seq number primary key,                             -- 글번호(PK)
    id varchar2(30) not null references tblUsers(id),   -- 아이디(FK)
    subject varchar2(500) not null,                     -- 제목
    content varchar2(400) not null,                     -- 내용
    regdate date default sysdate not null,              -- 작성시각
    readcount number default 0 not null,                -- 조회수
    tag varchar2(1) not null check(tag in ('y', 'n'))   -- 글내용에 HTML 태그 허용 유무
);

create sequence seqBoards;

commit;

select * from tblBoards;

select seq, (select name from tblUsers where id = tblBoards.id) as name, subject, readcount, regdate from tblBoards order by seq desc;

create or replace view vwBoard
as
select 
    seq, id, 
    (select name from tblUsers where id = tblBoards.id) as name, 
    subject, readcount, regdate,
    (sysdate - regdate) as isnew,
    content,
    (select count(*) from tblComment where pseq = tblBoards.seq) as ccnt
from tblBoards;

drop view vwBoard;





-- 댓글 테이블 2021.07.20
create table tblComment (

    seq number primary key,                             -- 댓글번호(PK)
    id varchar2(30) not null references tblUsers(id),   -- 아이디(FK)
    content varchar2(2000) not null,                    -- 댓글내용
    regdate date default sysdate not null,              -- 작성날짜
    pseq number not null references tblBoards(seq)      -- 부모글번호(FK)

);

create sequence seqComment;










-- 2021.07.22 페이징
-- 게시물을 일정 단위로 끊어서 가져오는 기법
select * from vwBoard 조건;

select subject, rownum from vwBoard v where rownum > 11 and rownum < 20;


-- 기존의 뷰 새로 만들기
create or replace view vwBoard
as
select a.*, rownum as rnum from
    (select 
        seq, id, 
        (select name from tblUsers where id = tblBoards.id) as name, 
        subject, readcount, regdate,
        (sysdate - regdate) as isnew,
        content,
        (select count(*) from tblComment where pseq = tblBoards.seq) as ccnt,
        thread, depth
    from tblBoards order by thread desc) a;


create or replace view vwBoard2
as
select * from
    (select 
        seq, id, 
        (select name from tblUsers where id = tblBoards.id) as name, 
        subject, readcount, regdate,
        (sysdate - regdate) as isnew,
        content,
        (select count(*) from tblComment where pseq = tblBoards.seq) as ccnt,
        thread, depth
    from tblBoards order by thread desc);
    

select * from vwBoard where rnum = 5;
select * from vwBoard where rnum > 5 and rnum < 10;

create or replace view vwBoard3
as
select 
        seq, id, 
        (select name from tblUsers where id = tblBoards.id) as name, 
        subject, readcount, regdate,
        (sysdate - regdate) as isnew,
        content,
        (select count(*) from tblComment where pseq = tblBoards.seq) as ccnt,
        thread, depth
from tblBoards order by thread desc;

select * from vwBoard3;


-- 답변기능 추가를 위해 테이블 수정하기

drop table tblComment;
drop sequence seqComment;

drop table tblBoards;
drop sequence seqBoards;

create table tblBoards (
    seq number primary key,                             -- 글번호(PK)
    id varchar2(30) not null references tblUsers(id),   -- 아이디(FK)
    subject varchar2(500) not null,                     -- 제목
    content varchar2(400) not null,                     -- 내용
    regdate date default sysdate not null,              -- 작성시각
    readcount number default 0 not null,                -- 조회수
    tag varchar2(1) not null check(tag in ('y', 'n')),  -- 글내용에 HTML 태그 허용 유무
    thread number not null,                             -- 정렬 기준
    depth number not null                               -- 출력
);

create sequence seqBoards;

-- 지웠던 Comment 테이블도 다시 만들것!

select nvl(max(thread), 0) + 1000 as thread from tblBoards;

select * from tblBoards;
select * from tblComment;


----------------- 2021.07.22 차트 만들기

-- 유저당 게시물 개수?
-- 유저당 댓글 개수?

--{
--    name: '홍길동',
--    y: 10
--}, 
--{
--    name: '아무개',
--    y: 3
--}, 
--{
--    name: '관리자',
--    y: 6
--}

select 
    name, 
    (select count(*) from tblBoards where id = tblUsers.id) as cnt 
from tblUsers;

select 
    name, 
    (select count(*) from tblComment where id = tblUsers.id) as cnt 
from tblUsers;



----------------- 2021.07.23 지도 DB 만들기

create table tblPlace (
    seq number primary key,         -- PK
    name varchar2(100) not null,    -- 장소명
    lat number not null,            -- 위도(Latitude)
    lng number not null             -- 경도(Longitude)
);


create sequence seqPlace;


create user web identified by java1234;
grant create session to web;
grant connect, resource to web; 