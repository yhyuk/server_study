-- D:\class\server\JSPTest 저장..



create table tblAddress (
    seq number primary key,         -- PK
    name varchar2(30) not null,     -- 이름
    age number(2) not null,         -- 나이
    gender char(1) not null check (gender in('m', 'f')), -- 성별
    address varchar2(500) not null  -- 주소
);

create sequence seqAddress;

insert into tblAddress(seq, name, age, gender, address) values(seqAddress.nextval, '홍길동', 27, 'm', '인천시 남동구 구월동');

select * from tblAddress;
select name from tblAddress where seq = 5;


delete from tblAddress;

commit;