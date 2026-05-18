-- Sample Data for InterviewPrep Database

-- Insert Roles
INSERT INTO roles (role_name) VALUES ('USER');
INSERT INTO roles (role_name) VALUES ('ADMIN');

-- Insert Admin User
INSERT INTO users (name, email, password, total_score, created_at, updated_at) 
VALUES ('Admin User', 'admin@interviewprep.com', '$2a$10$zMnk1kG2KF3bVlm7Cz5N8.qzR2H8p8B1Q0L3D5E2F1G4H5I6J7K8L9M', 0, NOW(), NOW());

-- Insert Sample Users
INSERT INTO users (name, email, password, total_score, created_at, updated_at) 
VALUES ('John Doe', 'john@example.com', '$2a$10$zMnk1kG2KF3bVlm7Cz5N8.qzR2H8p8B1Q0L3D5E2F1G4H5I6J7K8L9M', 850, NOW(), NOW());

INSERT INTO users (name, email, password, total_score, created_at, updated_at) 
VALUES ('Jane Smith', 'jane@example.com', '$2a$10$zMnk1kG2KF3bVlm7Cz5N8.qzR2H8p8B1Q0L3D5E2F1G4H5I6J7K8L9M', 920, NOW(), NOW());

-- Link users to roles (USER: 1, ADMIN: 2)
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2); -- Admin
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1); -- User
INSERT INTO user_roles (user_id, role_id) VALUES (3, 1); -- User

-- Insert Sample Quizzes
INSERT INTO quizzes (title, description, category, difficulty, duration, created_by, created_at, updated_at)
VALUES ('DSA Basics', 'Learn basic data structures and algorithms', 'DSA', 'Easy', 30, 1, NOW(), NOW());

INSERT INTO quizzes (title, description, category, difficulty, duration, created_by, created_at, updated_at)
VALUES ('Advanced Trees', 'Master tree data structures', 'DSA', 'Hard', 45, 1, NOW(), NOW());

INSERT INTO quizzes (title, description, category, difficulty, duration, created_by, created_at, updated_at)
VALUES ('SQL Fundamentals', 'Learn basic SQL queries', 'DBMS', 'Easy', 30, 1, NOW(), NOW());

-- Insert Sample Questions for Quiz 1
INSERT INTO questions (quiz_id, question_text, explanation, difficulty)
VALUES (1, 'What is the time complexity of Binary Search?', 'Binary Search divides the array in half each time, resulting in O(log n) time complexity.', 'Easy');

INSERT INTO questions (quiz_id, question_text, explanation, difficulty)
VALUES (1, 'Which data structure is LIFO?', 'Stack (Last In First Out) is a data structure where the last element added is the first one removed.', 'Easy');

-- Insert Options for Question 1
INSERT INTO options (question_id, option_text, is_correct) VALUES (1, 'O(1)', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (1, 'O(log n)', true);
INSERT INTO options (question_id, option_text, is_correct) VALUES (1, 'O(n)', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (1, 'O(n log n)', false);

-- Insert Options for Question 2
INSERT INTO options (question_id, option_text, is_correct) VALUES (2, 'Queue', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (2, 'Stack', true);
INSERT INTO options (question_id, option_text, is_correct) VALUES (2, 'Array', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (2, 'LinkedList', false);

-- Insert Sample Questions for Quiz 2
INSERT INTO questions (quiz_id, question_text, explanation, difficulty)
VALUES (2, 'What is AVL tree?', 'An AVL tree is a self-balancing binary search tree where the heights of the two child subtrees of any node differ by at most one.', 'Hard');

INSERT INTO questions (quiz_id, question_text, explanation, difficulty)
VALUES (2, 'What is the time complexity of insertion in AVL tree?', 'Insertion in AVL tree takes O(log n) time complexity due to self-balancing.', 'Hard');

-- Insert Options for Question 3
INSERT INTO options (question_id, option_text, is_correct) VALUES (3, 'A binary tree', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (3, 'A self-balancing binary search tree', true);
INSERT INTO options (question_id, option_text, is_correct) VALUES (3, 'A hash table', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (3, 'A linked list', false);

-- Insert Options for Question 4
INSERT INTO options (question_id, option_text, is_correct) VALUES (4, 'O(1)', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (4, 'O(log n)', true);
INSERT INTO options (question_id, option_text, is_correct) VALUES (4, 'O(n)', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (4, 'O(n^2)', false);

-- Insert Sample Questions for Quiz 3
INSERT INTO questions (quiz_id, question_text, explanation, difficulty)
VALUES (3, 'What is a PRIMARY KEY?', 'A PRIMARY KEY is a constraint that uniquely identifies each record in a table. It cannot contain NULL values.', 'Easy');

INSERT INTO questions (quiz_id, question_text, explanation, difficulty)
VALUES (3, 'What is the difference between INNER JOIN and LEFT JOIN?', 'INNER JOIN returns only matching records from both tables, while LEFT JOIN returns all records from the left table plus matching records from the right table.', 'Easy');

-- Insert Options for Question 5
INSERT INTO options (question_id, option_text, is_correct) VALUES (5, 'A constraint that uniquely identifies each record', true);
INSERT INTO options (question_id, option_text, is_correct) VALUES (5, 'A foreign key reference', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (5, 'An index on the table', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (5, 'A default value for a column', false);

-- Insert Options for Question 6
INSERT INTO options (question_id, option_text, is_correct) VALUES (6, 'INNER JOIN returns all records, LEFT JOIN returns matching records', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (6, 'INNER JOIN returns matching records, LEFT JOIN returns all from left table', true);
INSERT INTO options (question_id, option_text, is_correct) VALUES (6, 'They are the same', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (6, 'LEFT JOIN is never used', false);

-- Insert Sample Leaderboard Entries
INSERT INTO leaderboard (user_id, total_score, rank, attempted_quizzes, average_score)
VALUES (2, 850, 1, 5, 85.0);

INSERT INTO leaderboard (user_id, total_score, rank, attempted_quizzes, average_score)
VALUES (3, 920, 2, 6, 92.0);

-- Insert Sample Quiz Attempts
INSERT INTO quiz_attempts (user_id, quiz_id, score, total_questions, correct_answers, completed_at)
VALUES (2, 1, 85, 2, 2, NOW());

INSERT INTO quiz_attempts (user_id, quiz_id, score, total_questions, correct_answers, completed_at)
VALUES (3, 1, 90, 2, 2, NOW());
