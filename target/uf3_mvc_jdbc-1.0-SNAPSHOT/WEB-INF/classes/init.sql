CREATE TABLE IF NOT EXISTS  songs(id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                  name VARCHAR(100) NOT NULL,
                  author VARCHAR(100) NOT NULL,
                  album VARCHAR(100) NOT NULL,
                  price FLOAT DEFAULT 0,
                  rating INT DEFAULT 0);

INSERT INTO songs (name, author, album, price, rating) VALUES ('55Nothing Else Matters', 'Metallica', 'Metallica',12,5);
INSERT INTO songs (name, author, album, price, rating) VALUES ('American Pie','Don McLean','American Pie',9.5,4);
INSERT INTO songs (name, author, album, price, rating) VALUES ('Bohemian Rhapsody','Queen','A night at the Opera',10,3);
INSERT INTO songs (name, author, album, price, rating) VALUES ('Stairway to Heaven', 'Led Zeppelin', 'Led Zeppelin IV', 8.5, 2);
INSERT INTO songs (name, author, album, price, rating) VALUES ('Hotel California', 'Eagles', 'Hotel California', 11, 1);
INSERT INTO songs (name, author, album, price, rating) VALUES ('Like a Rolling Stone', 'Bob Dylan', 'Highway 61 Revisited', 7.5, 2);
INSERT INTO songs (name, author, album, price, rating) VALUES ('Smells Like Teen Spirit', 'Nirvana', 'Nevermind', 9, 3);
INSERT INTO songs (name, author, album, price, rating) VALUES ('Imagine', 'John Lennon', 'Imagine', 6.5, 4);
INSERT INTO songs (name, author, album, price, rating) VALUES ('Sweet Child o Mine', 'Guns N Roses', 'Appetite for Destruction', 10.5, 5);
INSERT INTO songs (name, author, album, price, rating) VALUES ('Thriller', 'Michael Jackson', 'Thriller', 8, 2);
INSERT INTO songs (name, author, album, price, rating) VALUES ('Billie Jean', 'Michael Jackson', 'Thriller', 7.5, 4);

