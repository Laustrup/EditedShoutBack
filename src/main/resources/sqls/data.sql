USE shout_db_test;

/* Gender is an enum with values such as:

   0: Male
   1: Female
   2: Other

 */

## Users
INSERT INTO user(id, username, description, gender, password)
VALUES (1, 'Frankensteiner', 'Have a very bad humor and loves old movies', 0, 'AsdF4D%');

INSERT INTO user(id, username, description, gender, password)
VALUES (2, 'KittenLover', 'Loves every animals, especially cats', 1, '123456');

INSERT INTO user(id, username, description, gender, password)
VALUES (3, 'JE', 'People call me a troll, because I am...', 2, 'QSCQSCqsc');

## Posts
INSERT INTO post(id, title, content, date, is_political_correct, user_id, hashtag)
VALUES (1,'Cats are amazing',
        'My cat is red and white with stripes, her name is Pluffy and she is amazing!',
        '1995-06-15 11:31:31',1,2,'#animals');

INSERT INTO post(id, title, content, date, is_political_correct, user_id, hashtag)
VALUES (2,'Vaccines sucks',
        'Do not take the vaccine, it destroys your dna!',
        '2020-06-15 11:31:31',0,3,'#conspiracy');

INSERT INTO post(id, title, content, date, is_political_correct, user_id, hashtag)
VALUES (3,'Y not the first on moon?:D',
        'I find it so retarded, yet funny that some people don\'t believe, but then there is something we can laugh about. :P',
        '2003-06-15 11:31:31',0,1,'#conspiracy');
