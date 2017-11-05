drop TABLE IF EXISTS country;
CREATE TABLE country (
  id integer NOT NULL,
  name varchar(256) NOT NULL,
  PRIMARY KEY (id)
) ;


drop TABLE IF EXISTS genre;
CREATE TABLE genre (
  id integer NOT NULL ,
  name varchar(256) NOT NULL,
  PRIMARY KEY (id)
);
drop TABLE IF EXISTS movie;
CREATE TABLE movie (
  id integer NOT NULL ,
  name_Russian varchar(256) NOT NULL,
  name_Native varchar(256) NOT NULL,
  year_Of_Release integer NOT NULL,
  description varchar(5000) NOT NULL,
  rating double NOT NULL,
  price double NOT NULL,
  picture_path varchar(50000) DEFAULT NULL,
  PRIMARY KEY (id)
);
drop TABLE IF EXISTS movie_country;
CREATE TABLE movie_country (
  movie_id integer DEFAULT NULL,
  country_id integer DEFAULT NULL
);
drop TABLE IF EXISTS movie_genre;
CREATE TABLE movie_genre (
  movie_id integer DEFAULT NULL,
  genre_id integer DEFAULT NULL
);

drop TABLE IF EXISTS movie_reviews;
CREATE TABLE movie_reviews (
  movie_id integer DEFAULT NULL,
  review_id integer DEFAULT NULL
);




drop TABLE IF EXISTS reviews;
CREATE TABLE reviews (
  id integer NOT NULL ,
  text varchar(5000) NOT NULL,
  user_id integer NOT NULL,
  PRIMARY KEY (id)
);


drop TABLE IF EXISTS users;
CREATE TABLE users (
  id integer NOT NULL ,
  user_name varchar(256) NOT NULL,
  user_email varchar(256) NOT NULL,
  password varchar(256) NOT NULL,
  PRIMARY KEY (id)
);