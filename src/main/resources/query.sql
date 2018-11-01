drop table if exists user;
drop table if exists publication;

create table user (id char(32) not null, encrypted_password varchar(255), username varchar(255), role char(32), primary key (id));
create table publication (id char(32) not null, message varchar(255), user_id char(32) not null, primary key (id));

--alter table publication add constraint fk_user_id_cascade foreign key (user_id) references user(id) on delete cascade;