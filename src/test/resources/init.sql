CREATE TABLE songs(id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                  name VARCHAR(100) NOT NULL,
                  author VARCHAR(100) NOT NULL,
                  album VARCHAR(100) NOT NULL,
                  price FLOAT DEFAULT 0,
                  rating INT DEFAULT 0);

INSERT INTO songs (name, author, album, price, rating) VALUES ('55Nothing Else Matters', 'Metallica', 'Metallica',12,5);
INSERT INTO songs (name, author, album, price, rating) VALUES ('American Pie','Don McLean','American Pie',9.5,5);
INSERT INTO songs (name, author, album, price, rating) VALUES ('Bohemian Rhapsody','Queen','A night at the Opera',10,5);
