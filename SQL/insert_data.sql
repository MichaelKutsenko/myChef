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
INSERT INTO ugroups (group_id, group_name, description) VALUES(1,'Chef','Chef searches events');
INSERT INTO ugroups (group_id, group_name, description) VALUES(2,'User','User creates event and looks throw chef forms');
INSERT INTO ugroups (group_id, group_name, description) VALUES(99,'Admin','Admin does administrative work ');