create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

INSERT INTO `customer` (`email`, `pwd`, `role`)
VALUES ('happy@example.com', '{noop}EazyBytes@12345', 'read');

INSERT INTO `customer` (`email`, `pwd`, `role`)
VALUES ('admin@example.com', '{bcrypt}$2a$12$HcJ41PMazjZM7knSZF3aO.Tr/SirNv10ckUlOkmLVrs2eTH9YvA5O', 'admin');
