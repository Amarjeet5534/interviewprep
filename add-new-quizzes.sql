-- Add 3 new quizzes
INSERT INTO quizzes (title, description, category, difficulty, duration, created_at, updated_at, created_by) VALUES
('Object-Oriented Programming', 'Master OOP concepts like inheritance, polymorphism, and encapsulation', 'Java', 'Medium', 40, NOW(), NOW(), 4),
('System Design Basics', 'Learn scalability, load balancing, and microservices architecture', 'CN', 'Hard', 50, NOW(), NOW(), 4),
('Dynamic Programming', 'Solve complex problems using dynamic programming techniques', 'DSA', 'Hard', 45, NOW(), NOW(), 4);

-- Get quiz IDs  
SET @quiz_oop = LAST_INSERT_ID() - 2;
SET @quiz_sd = LAST_INSERT_ID() - 1;
SET @quiz_dp = LAST_INSERT_ID();

-- Add questions for OOP
INSERT INTO questions (quiz_id, question_text, difficulty) VALUES
(@quiz_oop, 'What is the primary advantage of inheritance?', 'Medium'),
(@quiz_oop, 'Which keyword is used to prevent method overriding in Java?', 'Medium'),
(@quiz_oop, 'What is polymorphism?', 'Medium');

-- Add questions for System Design
INSERT INTO questions (quiz_id, question_text, difficulty) VALUES
(@quiz_sd, 'What is horizontal scaling?', 'Hard'),
(@quiz_sd, 'What is a microservice architecture?', 'Hard'),
(@quiz_sd, 'What does CAP theorem stand for?', 'Hard');

-- Add questions for DP
INSERT INTO questions (quiz_id, question_text, difficulty) VALUES
(@quiz_dp, 'What is the main principle of dynamic programming?', 'Hard'),
(@quiz_dp, 'What is memoization?', 'Hard'),
(@quiz_dp, 'What is the time complexity of the Fibonacci sequence using DP?', 'Hard');

-- Get question IDs for OOP
SELECT @q1 := id FROM questions WHERE quiz_id = @quiz_oop ORDER BY id LIMIT 1;
SELECT @q2 := id FROM questions WHERE quiz_id = @quiz_oop ORDER BY id LIMIT 1 OFFSET 1;
SELECT @q3 := id FROM questions WHERE quiz_id = @quiz_oop ORDER BY id LIMIT 1 OFFSET 2;

-- Add options for OOP Q1
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q1, 'Code reusability', 1),
(@q1, 'Increased memory usage', 0),
(@q1, 'Slower execution', 0),
(@q1, 'Complex syntax', 0);

-- Add options for OOP Q2
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q2, 'final', 1),
(@q2, 'private', 0),
(@q2, 'protected', 0),
(@q2, 'static', 0);

-- Add options for OOP Q3
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q3, 'Ability of objects to take multiple forms', 1),
(@q3, 'Hiding implementation details', 0),
(@q3, 'Bundling data and methods', 0),
(@q3, 'Creating multiple objects', 0);

-- Get question IDs for System Design
SELECT @q4 := id FROM questions WHERE quiz_id = @quiz_sd ORDER BY id LIMIT 1;
SELECT @q5 := id FROM questions WHERE quiz_id = @quiz_sd ORDER BY id LIMIT 1 OFFSET 1;
SELECT @q6 := id FROM questions WHERE quiz_id = @quiz_sd ORDER BY id LIMIT 1 OFFSET 2;

-- Add options for System Design Q1
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q4, 'Adding more servers to handle load', 1),
(@q4, 'Increasing CPU in single server', 0),
(@q4, 'Reducing database size', 0),
(@q4, 'Caching data locally', 0);

-- Add options for System Design Q2
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q5, 'Single large application split into small services', 1),
(@q5, 'Multiple databases in one server', 0),
(@q5, 'Multiple servers running same code', 0),
(@q5, 'Distributed caching layer', 0);

-- Add options for System Design Q3
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q6, 'Consistency, Availability, Partition tolerance', 1),
(@q6, 'Cache, API, Processing', 0),
(@q6, 'CPU, Architecture, Performance', 0),
(@q6, 'Cloud, AWS, Performance', 0);

-- Get question IDs for DP
SELECT @q7 := id FROM questions WHERE quiz_id = @quiz_dp ORDER BY id LIMIT 1;
SELECT @q8 := id FROM questions WHERE quiz_id = @quiz_dp ORDER BY id LIMIT 1 OFFSET 1;
SELECT @q9 := id FROM questions WHERE quiz_id = @quiz_dp ORDER BY id LIMIT 1 OFFSET 2;

-- Add options for DP Q1
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q7, 'Optimal substructure and overlapping subproblems', 1),
(@q7, 'Divide and conquer strategy', 0),
(@q7, 'Greedy approach', 0),
(@q7, 'Brute force method', 0);

-- Add options for DP Q2
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q8, 'Storing results to avoid recomputation', 1),
(@q8, 'Splitting problem into subproblems', 0),
(@q8, 'Using recursion without storing', 0),
(@q8, 'Sorting the input data', 0);

-- Add options for DP Q3
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q9, 'O(n)', 1),
(@q9, 'O(n^2)', 0),
(@q9, 'O(2^n)', 0),
(@q9, 'O(log n)', 0);
