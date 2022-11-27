create table role(
  id serial not null primary key,
  type_of_role varchar(9) not null unique
);

create table users(
  id varchar(36) not null primary key,
  username varchar(20) not null unique,
  firstName varchar(20) not null,
  lastName varchar(20),
  password varchar(128) not null,
  email varchar(36) not null,
  phone varchar(12),
  role_id int references role(id) not null,
  is_enabled bool not null
);