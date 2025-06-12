CREATE TABLE borrow_record (
                               id INT PRIMARY KEY AUTO_INCREMENT,
                               user_id INT NOT NULL,
                               book_id INT NOT NULL,
                               borrow_date DATETIME NOT NULL,
                               due_date DATETIME NOT NULL,
                               return_date DATETIME,
                               status INT NOT NULL DEFAULT 0,
                               create_time DATETIME NOT NULL,
                               update_time DATETIME NOT NULL,
                               FOREIGN KEY (user_id) REFERENCES user(id),
                               FOREIGN KEY (book_id) REFERENCES book(id)
);