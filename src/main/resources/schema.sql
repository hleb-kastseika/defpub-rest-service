create table if not exists user (
    id char(32) not null,
    username varchar(255),
    encrypted_password varchar(255),
    role char(32),
    primary key (id));

create table if not exists publication (
    id char(32) not null,
    message varchar(255),
    user_id char(32) not null,
    primary key (id),
    constraint fk_publication_user_id foreign key (user_id) references user (id) on delete cascade);

create table if not exists user_config (
    id char(32) not null,
    user_id char(32) not null,
    cron_schedule varchar(255),
    primary key (id),
    constraint fk_user_config_user_id foreign key (user_id) references user (id) on delete cascade);
