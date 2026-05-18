-- Add missing questions for System Design (quiz 12) and Dynamic Programming (quiz 13)

-- Add questions for System Design (quiz 12)
INSERT INTO questions (quiz_id, question_text, difficulty) VALUES
(12, 'What is horizontal scaling?', 'Hard'),
(12, 'What is a microservice architecture?', 'Hard'),
(12, 'What does CAP theorem stand for?', 'Hard');

-- Add questions for Dynamic Programming (quiz 13)
INSERT INTO questions (quiz_id, question_text, difficulty) VALUES
(13, 'What is the main principle of dynamic programming?', 'Hard'),
(13, 'What is memoization?', 'Hard'),
(13, 'What is the time complexity of the Fibonacci sequence using DP?', 'Hard');

-- Get question IDs for System Design (quiz 12)
SELECT @q1 := id FROM questions WHERE quiz_id = 12 ORDER BY id LIMIT 1;
SELECT @q2 := id FROM questions WHERE quiz_id = 12 ORDER BY id LIMIT 1 OFFSET 1;
SELECT @q3 := id FROM questions WHERE quiz_id = 12 ORDER BY id LIMIT 1 OFFSET 2;

-- Add options for System Design Q1
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q1, 'Adding more servers to handle load', 1),
(@q1, 'Increasing CPU in single server', 0),
(@q1, 'Reducing database size', 0),
(@q1, 'Caching data locally', 0);

-- Add options for System Design Q2
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q2, 'Single large application split into small services', 1),
(@q2, 'Multiple databases in one server', 0),
(@q2, 'Multiple servers running same code', 0),
(@q2, 'Distributed caching layer', 0);

-- Add options for System Design Q3
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q3, 'Consistency, Availability, Partition tolerance', 1),
(@q3, 'Cache, API, Processing', 0),
(@q3, 'CPU, Architecture, Performance', 0),
(@q3, 'Cloud, AWS, Performance', 0);

-- Get question IDs for Dynamic Programming (quiz 13)
SELECT @q4 := id FROM questions WHERE quiz_id = 13 ORDER BY id LIMIT 1;
SELECT @q5 := id FROM questions WHERE quiz_id = 13 ORDER BY id LIMIT 1 OFFSET 1;
SELECT @q6 := id FROM questions WHERE quiz_id = 13 ORDER BY id LIMIT 1 OFFSET 2;

-- Add options for DP Q1
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q4, 'Optimal substructure and overlapping subproblems', 1),
(@q4, 'Divide and conquer strategy', 0),
(@q4, 'Greedy approach', 0),
(@q4, 'Brute force method', 0);

-- Add options for DP Q2
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q5, 'Storing results to avoid recomputation', 1),
(@q5, 'Splitting problem into subproblems', 0),
(@q5, 'Using recursion without storing', 0),
(@q5, 'Sorting the input data', 0);

-- Add options for DP Q3
INSERT INTO options (question_id, option_text, is_correct) VALUES
(@q6, 'O(n)', 1),
(@q6, 'O(n^2)', 0),
(@q6, 'O(2^n)', 0),
(@q6, 'O(log n)', 0);
