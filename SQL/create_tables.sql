#1 create 'regions'
# DROP TABLE IF EXISTS regions;
CREATE TABLE IF NOT EXISTS regions (
  region_id   BIGINT       NOT NULL,
  region_name VARCHAR(100) NOT NULL,
  PRIMARY KEY (region_id)
)ENGINE InnoDB;

#2 create 'cities'
# DROP TABLE IF EXISTS cities;
CREATE TABLE IF NOT EXISTS cities (
  city_id   BIGINT       NOT NULL,
  region_id BIGINT       NOT NULL,
  city_name VARCHAR(100) NOT NULL,
  is_center  BOOLEAN      NOT NULL DEFAULT FALSE,
  PRIMARY KEY (city_id),
  FOREIGN KEY (region_id) REFERENCES regions (region_id)
)ENGINE InnoDB;

#3 create 'users'
# DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
  user_id    BIGINT      NOT NULL,
  user_name  VARCHAR(20) NOT NULL,
  email      VARCHAR(20) NOT NULL,
  pswrd_hash VARCHAR(64) NOT NULL,
#   is_chef    BOOLEAN     NOT NULL DEFAULT TRUE,
  PRIMARY KEY (user_id)
)ENGINE InnoDB;

#4 create 'user_details'
# DROP TABLE IF EXISTS user_details;
CREATE TABLE IF NOT EXISTS user_details (
  user_id       BIGINT      NOT NULL,
  city_id       BIGINT      NOT NULL,
  first_name    VARCHAR(64) NOT NULL,
  last_name     VARCHAR(64) NOT NULL,
  phone         VARCHAR(64)          DEFAULT NULL,
  last_online   DATE                 DEFAULT NULL,
  grade         DECIMAL               DEFAULT 0,
  grade_counter INT                  DEFAULT 0,
  is_chef       BOOLEAN     NOT NULL DEFAULT FALSE,
  PRIMARY KEY (user_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id),
  FOREIGN KEY (city_id) REFERENCES cities (city_id)
)ENGINE InnoDB;

#5 create 'chef_details'
# DROP TABLE IF EXISTS chef_details;
CREATE TABLE IF NOT EXISTS chef_details (
  user_id        BIGINT       NOT NULL,
  price_per_hour INT          NOT NULL,
  min_price      INT          NOT NULL,
  description    VARCHAR(500) NOT NULL,
  PRIMARY KEY (user_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id)
)ENGINE InnoDB;

#6 create 'ugroup'
# DROP TABLE IF EXISTS ugroup;
CREATE TABLE IF NOT EXISTS ugroups (
  group_id    BIGINT      NOT NULL,
  group_name  VARCHAR(20) NOT NULL,
  description VARCHAR(200),
  PRIMARY KEY (group_id)
)ENGINE InnoDB;

#7 create 'user_group'
# DROP TABLE IF EXISTS user_group;
CREATE TABLE IF NOT EXISTS user_group (
  group_id BIGINT NOT NULL,
  user_id  BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (user_id),
  FOREIGN KEY (group_id) REFERENCES ugroups (group_id)
)ENGINE InnoDB;

#8 create 'events'
# DROP TABLE IF EXISTS events;
CREATE TABLE IF NOT EXISTS events (
  event_id    BIGINT       NOT NULL,
  user_id     BIGINT       NOT NULL,
  city_id     BIGINT       NOT NULL,
  event_date  DATE         NOT NULL,
  description VARCHAR(500) NOT NULL,
  PRIMARY KEY (event_id),
  FOREIGN KEY (user_id) REFERENCES user_details (user_id),
  FOREIGN KEY (city_id) REFERENCES cities (city_id)
)ENGINE InnoDB;

#9 create 'feedbacks'
# DROP TABLE IF EXISTS feedbacks;
CREATE TABLE IF NOT EXISTS feedbacks (
  feedback_id  BIGINT       NOT NULL,
  from_user_id BIGINT       NOT NULL,
  to_user_id   BIGINT       NOT NULL,
  description  VARCHAR(500) NOT NULL,
  grade        INT          NOT NULL,
  date        DATETIME         NOT NULL,
  PRIMARY KEY (feedback_id),
  FOREIGN KEY (from_user_id) REFERENCES user_details (user_id),
  FOREIGN KEY (to_user_id) REFERENCES user_details (user_id)
)ENGINE InnoDB;

#10 create 'messages'
# DROP TABLE IF EXISTS messages;
CREATE TABLE IF NOT EXISTS messages (
  message_id  BIGINT       NOT NULL,
  user_id     BIGINT       NOT NULL,
  event_id    BIGINT       NOT NULL,
  description VARCHAR(500) NOT NULL,
  date        DATETIME         NOT NULL,
  PRIMARY KEY (message_id),
  FOREIGN KEY (user_id) REFERENCES user_details (user_id),
  FOREIGN KEY (event_id) REFERENCES events (event_id)
)ENGINE InnoDB;