create table if not exists country(
    id serial primary key not null,
    title varchar(20) not null unique
);

create table if not exists genre(
    id serial primary key not null,
    title varchar(20) not null unique
);

create table if not exists position(
    id serial primary key not null,
    title varchar(20) not null unique
);

create table if not exists publisher(
    id serial primary key not null,
    title varchar(20) not null unique
);

create table if not exists studio(
    id serial primary key not null,
    title varchar(20) not null unique
);

create table if not exists person(
    id bigserial primary key not null,
    first_name varchar(20),
    last_name varchar(20),
    date_of_birth date not null,
    date_of_death date
);

create table if not exists person_position(
    id bigserial primary key not null,
    person_id bigint not null references person(id) not null,
    position_id bigint not null references position(id) not null,
    unique (person_id, position_id)
);

create table if not exists person_country(
    id bigserial primary key not null,
    person_id bigint not null references person(id) not null,
    country_id bigint not null references country(id) not null,
    unique (person_id, country_id)
);

create table if not exists film(
    id bigserial primary key not null,
    title varchar(20) not null,
    duration int not null,
    release_date date not null,
    age_limit int,
    rank numeric(1,1),
    created_at timestamp not null,
    updated_at timestamp
);

create table if not exists film_country(
    id bigserial primary key not null,
    film_id bigint not null references film(id) not null,
    country_id bigint not null references country(id) not null,
    unique (film_id, country_id)
);

create table if not exists film_genre(
    id bigserial primary key not null,
    film_id bigint not null references film(id) not null,
    genre_id bigint not null references genre(id) not null,
    unique (film_id, genre_id)
);

create table if not exists film_author(
    id bigserial primary key not null,
    film_id bigint not null references film(id) not null,
    person_id bigint not null references person(id) not null,
    unique (film_id, person_id)
);

create table if not exists film_operator(
    id bigserial primary key not null,
    film_id bigint not null references film(id) not null,
    person_id bigint not null references person(id) not null,
    unique (film_id, person_id)
);

create table if not exists film_studio(
    id bigserial primary key not null,
    film_id bigint not null references film(id) not null,
    studio_id bigint not null references studio(id) not null,
    unique (film_id, studio_id)
);

create table if not exists film_actor(
    id bigserial primary key not null,
    film_id bigint not null references film(id) not null,
    actor_id bigint not null references person(id) not null,
    unique (film_id, actor_id)
);

create table if not exists series(
    id bigserial primary key not null,
    title varchar(20) not null,
    year_started date not null,
    year_finished date,
    season_amount int not null,
    age_limit int,
    rank numeric(1,1),
    created_at timestamp not null,
    updated_at timestamp
);

create table if not exists series_country(
    id bigserial primary key not null,
    series_id bigint not null references film(id) not null,
    country_id bigint not null references country(id) not null,
    unique (series_id, country_id)
);

create table if not exists series_genre(
    id bigserial primary key not null,
    series_id bigint not null references film(id) not null,
    genre_id bigint not null references genre(id) not null,
    unique (series_id, genre_id)
);

create table if not exists series_author(
    id bigserial primary key not null,
    series_id bigint not null references film(id) not null,
    person_id bigint not null references person(id) not null,
    unique (series_id, person_id)
);

create table if not exists series_operator(
    id bigserial primary key not null,
    series_id bigint not null references film(id) not null,
    person_id bigint not null references person(id) not null,
    unique (series_id, person_id)
);

create table if not exists series_studio(
    id bigserial primary key not null,
    series_id bigint not null references film(id) not null,
    studio_id bigint not null references studio(id) not null,
    unique (series_id, studio_id)
);

create table if not exists series_actor(
    id bigserial primary key not null,
    series_id bigint not null references series(id) not null,
    actor_id bigint not null references person(id) not null,
    unique (series_id, actor_id)
);

create table if not exists book(
    id bigserial primary key not null,
    title varchar(20) not null,
    pages_amount int not null,
    release_date date not null,
    age_limit int,
    rank numeric(1,1),
    created_at timestamp not null,
    updated_at timestamp
);

create table if not exists book_genre(
    id bigserial primary key not null,
    book_id bigint not null references book(id) not null,
    genre_id bigint not null references genre(id) not null,
    unique (book_id, genre_id)
);

create table if not exists book_publisher(
    id bigserial primary key not null,
    book_id bigint not null references book(id) not null,
    publisher_id bigint not null references publisher(id) not null,
    unique (book_id, publisher_id)
);

create table if not exists book_author(
    id bigserial primary key not null,
    book_id bigint not null references book(id) not null,
    person_id bigint not null references person(id) not null,
    unique (book_id, person_id)
);

create table if not exists review(
    id varchar(36) primary key not null,
    user_id bigserial not null,
    book_id bigint not null references book(id),
    film_id bigint not null references film(id),
    series_id bigint not null references series(id),
    title varchar(40) not null,
    comment text,
    is_recommend bool not null
);
