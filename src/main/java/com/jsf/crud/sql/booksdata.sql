CREATE DATABASE sidhu;

USE sidhu;


CREATE TABLE BooksData (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, book_catagoery VARCHAR(100), book_title VARCHAR(100), book_price VARCHAR(100), first_author VARCHAR(100), second_author VARCHAR(100));

INSERT INTO BooksData (book_catagoery, book_title, book_price, first_author, second_author) VALUES ('John', 'johnwick', 'access@123', 'sid', 'Siv');


SELECT * FROM BooksData;
