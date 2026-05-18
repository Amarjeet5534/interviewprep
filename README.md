# InterviewPrep AI

InterviewPrep AI is a full-stack MCQ interview preparation platform. It lets users register, log in, attempt timed quizzes, view AI-generated explanations, and track scores on a leaderboard.

## What this project does

- User authentication with JWT
- Quiz browsing and timed quiz attempts
- Score persistence after submission
- AI answer explanations using Gemini
- Leaderboard based on quiz performance
- Admin endpoints for quiz management

## Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL 8
- Maven
- Swagger/OpenAPI

### Frontend

- React 18
- Vite
- Tailwind CSS
- Axios
- React Router

### Infrastructure

- Docker
- Docker Compose

### AI

- Google Gemini API

## Project Structure

```text
.
├── interviewprep-backend/
│   ├── src/main/java/com/interviewprep/
│   │   ├── ai/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── entity/
│   │   ├── exception/
│   │   ├── repository/
│   │   ├── security/
│   │   ├── service/
│   │   └── util/
│   └── src/main/resources/application.yml
├── interviewprep-frontend/
│   ├── src/
│   │   ├── api/
│   │   ├── components/
│   │   ├── context/
│   │   ├── layouts/
│   │   ├── pages/
│   │   ├── routes/
│   │   ├── styles/
│   │   └── utils/
│   └── package.json
├── docker-compose.yml
├── add-new-quizzes.sql
├── add-missing-questions.sql
└── sample-data.sql
```

## Main Features

### User Flow

1. Register or log in.
2. View quizzes on the dashboard.
3. Start a quiz and answer multiple-choice questions.
4. Submit answers and receive a score.
5. See AI explanations for answers.
6. Check updated score and leaderboard position.

### Admin Flow

1. Create and manage quizzes.
2. Add questions and options.
3. Review quiz and user data.

## Database Overview

The application uses MySQL tables for users, roles, quizzes, questions, options, quiz attempts, and leaderboard data.

Important relationships:

- A user can create quizzes.
- A quiz contains many questions.
- A question contains many options.
- A user can submit many quiz attempts.
- Leaderboard data is derived from user score and quiz attempts.

## API Overview

### Authentication

- `POST /api/auth/register` - register a user
- `POST /api/auth/login` - log in a user
- `GET /api/auth/me` - get the current authenticated user

### Quizzes

- `GET /api/quizzes` - list quizzes
- `GET /api/quizzes/{id}` - get a quiz by id
- `GET /api/quizzes/category/{category}` - filter by category
- `GET /api/quizzes/difficulty/{difficulty}` - filter by difficulty
- `POST /api/quizzes/submit` - submit quiz answers

### AI

- `POST /api/ai/explain` - generate an explanation for an answer

### Leaderboard

- `GET /api/leaderboard` - get leaderboard entries
- `GET /api/leaderboard/user/{userId}` - get a user ranking

### Admin

- `POST /api/admin/quizzes` - create a quiz
- `PUT /api/admin/quizzes/{id}` - update a quiz
- `DELETE /api/admin/quizzes/{id}` - delete a quiz

## Authentication Flow

The app uses JWT authentication.

1. User logs in.
2. Backend returns a JWT token.
3. Frontend stores the token in `localStorage`.
4. Axios adds the token to protected requests.
5. Backend validates the token on each request.

## Setup Requirements

- Java 21
- Node.js 18 or later
- MySQL 8
- Docker and Docker Compose

## Local Setup

### 1. Start MySQL, backend, and frontend with Docker

```bash
docker compose up -d mysql backend frontend
```

### 2. Check containers

```bash
docker compose ps
```

### 3. Open the app

- Frontend: `http://localhost:3000`
- Backend API: `http://localhost:8080/api`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

## Manual Development Setup

### Backend

```bash
cd interviewprep-backend
mvn clean package
mvn spring-boot:run
```

### Frontend

```bash
cd interviewprep-frontend
npm install
npm run dev
```

## Environment Variables

### Backend

```env
JWT_SECRET=your-super-secret-key
JWT_EXPIRATION=86400000
GEMINI_API_KEY=your-gemini-api-key
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/interviewprep_db
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root
```

### Frontend

```env
VITE_API_BASE_URL=http://localhost:8080/api
VITE_APP_NAME=InterviewPrep AI
```

## SQL Seed Files

These files can be used to seed or extend quiz data:

- `sample-data.sql`
- `add-new-quizzes.sql`
- `add-missing-questions.sql`

## How to Demo the Project

1. Open the frontend.
2. Register or log in.
3. Start a quiz.
4. Submit answers.
5. Show the updated score on the dashboard.
6. Open the leaderboard.
7. Show AI explanations for quiz answers.

## Troubleshooting

### Backend does not start

- Make sure MySQL is running.
- Check the datasource URL and credentials.
- Confirm the JWT secret and Gemini key are set.

### Frontend cannot call the API

- Make sure the backend is running on port 8080.
- Check `VITE_API_BASE_URL`.
- Verify the JWT token exists in `localStorage`.

### Leaderboard is empty

- Verify quiz attempts exist in the database.
- Confirm the backend can read user and attempt data.

## Notes for Submission

- The backend is structured using controller, service, repository, and entity layers.
- Score updates are saved in the database after quiz submission.
- The dashboard refreshes the current user data after submission so the score stays in sync.
- The leaderboard is derived from live quiz and user data.
- The app is containerized and can be started with Docker Compose.

## Future Improvements

- Add more automated tests
- Add role-based admin UI
- Add progress charts and analytics
- Move secrets to a production secrets manager
- Add migrations with Flyway or Liquibase

## License

This project is intended for academic and demo use.
