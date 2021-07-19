-- db.sql
-- 게시판 만들기


-- 회원 테이블
select *from tblUsers;

-- 게시판 테이블 > 단계 + 확장
-- 기본 게시판
drop table tblBoard;
select * from tblboard;
delete from tblboard;

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
        subject, 
        readcount, 
        regdate,
        (sysdate - regdate) as isnew
    from tblBoards;

select * from vwBoard;
