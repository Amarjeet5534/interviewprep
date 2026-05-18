-- Insert Questions for Quiz 2 (DSA Basics)
INSERT INTO questions (quiz_id, question_text, explanation, difficulty) 
VALUES (2, 'What is the time complexity of Binary Search?', 'Binary Search divides the array in half each time, resulting in O(log n) time complexity.', 'Easy');

INSERT INTO questions (quiz_id, question_text, explanation, difficulty) 
VALUES (2, 'Which data structure is LIFO?', 'Stack (Last In First Out) is a data structure where the last element added is the first one removed.', 'Easy');

-- Insert Options for Question 2
INSERT INTO options (question_id, option_text, is_correct) VALUES (2, 'O(1)', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (2, 'O(log n)', true);
INSERT INTO options (question_id, option_text, is_correct) VALUES (2, 'O(n)', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (2, 'O(n log n)', false);

-- Insert Options for Question 3
INSERT INTO options (question_id, option_text, is_correct) VALUES (3, 'Queue', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (3, 'Stack', true);
INSERT INTO options (question_id, option_text, is_correct) VALUES (3, 'Array', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (3, 'LinkedList', false);

-- Insert Questions for Quiz 3 (Advanced Trees)
INSERT INTO questions (quiz_id, question_text, explanation, difficulty) 
VALUES (3, 'What is AVL tree?', 'An AVL tree is a self-balancing binary search tree where the heights of the two child subtrees of any node differ by at most one.', 'Hard');

INSERT INTO questions (quiz_id, question_text, explanation, difficulty) 
VALUES (3, 'What is the time complexity of insertion in AVL tree?', 'Insertion in AVL tree takes O(log n) time complexity due to self-balancing.', 'Hard');

-- Insert Options for Question 4
INSERT INTO options (question_id, option_text, is_correct) VALUES (4, 'A binary tree', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (4, 'A self-balancing binary search tree', true);
INSERT INTO options (question_id, option_text, is_correct) VALUES (4, 'A hash table', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (4, 'A linked list', false);

-- Insert Options for Question 5
INSERT INTO options (question_id, option_text, is_correct) VALUES (5, 'O(1)', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (5, 'O(log n)', true);
INSERT INTO options (question_id, option_text, is_correct) VALUES (5, 'O(n)', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (5, 'O(n^2)', false);

-- Insert Questions for Quiz 4 (SQL Fundamentals)
INSERT INTO questions (quiz_id, question_text, explanation, difficulty) 
VALUES (4, 'What is a PRIMARY KEY?', 'A PRIMARY KEY is a constraint that uniquely identifies each record in a table. It cannot contain NULL values.', 'Easy');

INSERT INTO questions (quiz_id, question_text, explanation, difficulty) 
VALUES (4, 'What is the difference between INNER JOIN and LEFT JOIN?', 'INNER JOIN returns only matching records from both tables, while LEFT JOIN returns all records from the left table plus matching records from the right table.', 'Easy');

-- Insert Options for Question 6
INSERT INTO options (question_id, option_text, is_correct) VALUES (6, 'A constraint that uniquely identifies each record', true);
INSERT INTO options (question_id, option_text, is_correct) VALUES (6, 'A foreign key reference', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (6, 'An index on the table', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (6, 'A default value for a column', false);

-- Insert Options for Question 7
INSERT INTO options (question_id, option_text, is_correct) VALUES (7, 'INNER JOIN returns all records, LEFT JOIN returns matching records', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (7, 'INNER JOIN returns matching records, LEFT JOIN returns all from left table', true);
INSERT INTO options (question_id, option_text, is_correct) VALUES (7, 'They are the same', false);
INSERT INTO options (question_id, option_text, is_correct) VALUES (7, 'LEFT JOIN is never used', false);
