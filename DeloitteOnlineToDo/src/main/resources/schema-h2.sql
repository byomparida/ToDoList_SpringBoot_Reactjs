CREATE TABLE tbl_todolist(todolist_id BIGINT PRIMARY KEY AUTO_INCREMENT, todolist_type VARCHAR(30), todolist_name VARCHAR(50), created_time DATETIME, updated_time DATETIME);

CREATE TABLE tbl_user(user_id INT PRIMARY KEY AUTO_INCREMENT, user_name VARCHAR(30), password VARCHAR(256), created_time DATETIME, updated_time DATETIME);