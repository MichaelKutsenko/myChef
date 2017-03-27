# INSERT users
INSERT INTO users (user_id, user_name, email, pswrd_hash) VALUES(1, 'Ivan', 'Ivan@email', 111111);
INSERT INTO users (user_id, user_name, email, pswrd_hash) VALUES(2, 'Anna', 'Anna@email', 111111);
INSERT INTO users (user_id, user_name, email, pswrd_hash) VALUES(3, 'Oleg', 'Oleg@email', 111111);
INSERT INTO users (user_id, user_name, email, pswrd_hash) VALUES(4, 'Olya', 'Olya@email', 111111);
INSERT INTO users (user_id, user_name, email, pswrd_hash) VALUES(5, 'Manya', 'Manya@email', 111111);


# INSERT user_details
INSERT INTO user_details (user_id, city_id, first_name, last_name, is_chef) VALUES(1, 2, 'Ivan', 'Kim', FALSE );
INSERT INTO user_details (user_id, city_id, first_name, last_name, is_chef) VALUES(2, 4, 'Anna', 'Annovna',  FALSE );
INSERT INTO user_details (user_id, city_id, first_name, last_name, is_chef) VALUES(3, 2, 'Oleg', 'Trubachev', TRUE );
INSERT INTO user_details (user_id, city_id, first_name, last_name, is_chef) VALUES(4, 4, 'Olya', 'Franko', TRUE );
INSERT INTO user_details (user_id, city_id, first_name, last_name, is_chef) VALUES(5, 2, 'Manya', 'Cesar', TRUE );

# INSERT chef_details
INSERT INTO chef_details (user_id, price_per_hour, min_price, description) VALUES(3, 200, 400, 'Bababalamaga' );
INSERT INTO chef_details (user_id, price_per_hour, min_price, description) VALUES(4, 140, 500, 'I`m chef' );
INSERT INTO chef_details (user_id, price_per_hour, min_price, description) VALUES(5, 120, 600, 'Hello, world' );

#  INSERT ugroups
INSERT INTO ugroups (group_id, group_name, description) VALUES(1,'CHEF','Chef searches events');
INSERT INTO ugroups (group_id, group_name, description) VALUES(2,'USER','User creates event and looks throw chef forms');
INSERT INTO ugroups (group_id, group_name, description) VALUES(99,'ADMIN','Admin does administrative work ');

#  INSERT events
INSERT INTO events (event_id, user_id, city_id, event_date, description) VALUES(1, 1, 15, '2017-10-23', 'big party');
INSERT INTO events (event_id, user_id, city_id, event_date, description) VALUES(2, 1, 15, '2017-11-24', 'party');
INSERT INTO events (event_id, user_id, city_id, event_date, description) VALUES(3, 1, 15, '2017-12-25', 'little party');
INSERT INTO events (event_id, user_id, city_id, event_date, description) VALUES(4, 2, 15, '2017-10-26', 'after-dinner');
INSERT INTO events (event_id, user_id, city_id, event_date, description) VALUES(5, 2, 15, '2017-10-27', 'christening');

#  INSERT messages
INSERT INTO messages (message_id, event_id, user_id, description, date) VALUES(1, 1, 2, '1', '2017-10-27 01:02:03');
INSERT INTO messages (message_id, event_id, user_id, description, date) VALUES(2, 1, 3, '2', '2018-10-27 02:22:03');
INSERT INTO messages (message_id, event_id, user_id, description, date) VALUES(3, 2, 3, '1', '2019-10-27 03:32:04');
INSERT INTO messages (message_id, event_id, user_id, description, date) VALUES(4, 2, 2, '2', '2019-10-27 03:32:03');

#  INSERT feedbacks
INSERT INTO feedbacks (feedback_id, from_user_id, to_user_id, description, date, grade) VALUES(1, 1, 3, '1', '2017-10-27 01:02:03', 5);
INSERT INTO feedbacks (feedback_id, from_user_id, to_user_id, description, date, grade) VALUES(2, 3, 3, '1', '2017-10-27 01:02:03', 5);
