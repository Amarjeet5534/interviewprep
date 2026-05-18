# Testing Guide - InterviewPrep AI

## 🧪 Testing Strategy

This guide covers manual testing, automated testing, and quality assurance procedures for InterviewPrep AI.

---

## 📝 Manual Testing Checklist

### Authentication Flow
- [ ] User can register with new account
  - [ ] Validates email format
  - [ ] Validates password strength
  - [ ] Prevents duplicate email registration
  - [ ] Creates user with USER role
  - [ ] Returns JWT token

- [ ] User can login with valid credentials
  - [ ] Login with correct email and password succeeds
  - [ ] Login with incorrect password fails
  - [ ] Login with non-existent email fails
  - [ ] Returns valid JWT token
  - [ ] Token stored in localStorage

- [ ] User can logout
  - [ ] Logout clears token from localStorage
  - [ ] Redirects to login page
  - [ ] Protected routes redirect to login

### Quiz Management
- [ ] User can view all quizzes
  - [ ] Quizzes display with title, category, difficulty
  - [ ] Pagination works correctly
  - [ ] Can filter by category
  - [ ] Can filter by difficulty

- [ ] User can start a quiz
  - [ ] Quiz timer starts correctly
  - [ ] Questions display one at a time
  - [ ] Options are clickable
  - [ ] Previous/Next navigation works
  - [ ] Can revisit previous questions

- [ ] User can submit quiz
  - [ ] Score calculated correctly: (correct/total)*100
  - [ ] Correct answers counted properly
  - [ ] Result page displays score
  - [ ] Result page shows category and difficulty
  - [ ] Can retry quiz

- [ ] Quiz attempt is saved
  - [ ] Attempt stored in database
  - [ ] User score updated
  - [ ] Leaderboard updated

### Admin Features
- [ ] Admin can create quiz
  - [ ] Can add multiple questions
  - [ ] Can add multiple options per question
  - [ ] Can mark correct answer
  - [ ] Quiz saved to database
  - [ ] Appears in quiz list

- [ ] Admin can edit quiz
  - [ ] Can modify quiz details
  - [ ] Can add/remove questions
  - [ ] Changes saved to database

- [ ] Admin can delete quiz
  - [ ] Confirms deletion
  - [ ] Quiz removed from database
  - [ ] Questions/options deleted cascaded

### AI Explanations
- [ ] User can request explanation
  - [ ] Loads explanation component
  - [ ] Displays correct answer
  - [ ] Shows why answer is wrong
  - [ ] Provides interview tips
  - [ ] Shows follow-up question

### Leaderboard
- [ ] Leaderboard displays correctly
  - [ ] Shows user rankings
  - [ ] Sorted by score descending
  - [ ] Displays top users
  - [ ] Shows global statistics

### User Profile
- [ ] User can view profile
  - [ ] Displays current stats
  - [ ] Shows total score
  - [ ] Shows quiz attempts
  - [ ] Shows average score

- [ ] User can update profile
  - [ ] Can change name
  - [ ] Can update bio
  - [ ] Can change profile picture
  - [ ] Changes saved to database

---

## 🔧 API Testing (via Swagger)

### Access Swagger UI
```
http://localhost:8080/swagger-ui.html
```

### Test Endpoints

#### Auth Endpoints
1. **POST /api/auth/register**
   ```json
   {
     "name": "Test User",
     "email": "test@example.com",
     "password": "Password@123"
   }
   ```
   Expected: 201 Created with token

2. **POST /api/auth/login**
   ```json
   {
     "email": "test@example.com",
     "password": "Password@123"
   }
   ```
   Expected: 200 OK with token

#### Quiz Endpoints
3. **GET /api/quizzes**
   Expected: 200 OK with list of quizzes

4. **GET /api/quizzes/{id}**
   Expected: 200 OK with quiz details

5. **POST /api/quizzes/submit**
   ```json
   {
     "quizId": 1,
     "answers": {
       "1": 1,
       "2": 3
     }
   }
   ```
   Expected: 200 OK with result

#### Leaderboard Endpoints
6. **GET /api/leaderboard**
   Expected: 200 OK with leaderboard data

7. **GET /api/leaderboard/user/{userId}**
   Expected: 200 OK with user ranking

---

## 🧪 Automated Testing

### Backend Unit Tests

Create `src/test/java/com/interviewprep/service/QuizServiceTest.java`:

```java
@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private QuizServiceImpl quizService;

    @Test
    public void testGetAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        when(quizRepository.findAll()).thenReturn(quizzes);
        
        List<Quiz> result = quizService.getAllQuizzes();
        
        assertEquals(0, result.size());
        verify(quizRepository, times(1)).findAll();
    }

    @Test
    public void testGetQuizById() {
        Quiz quiz = new Quiz();
        quiz.setId(1L);
        when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));
        
        Quiz result = quizService.getQuizById(1L);
        
        assertEquals(1L, result.getId());
    }
}
```

### Frontend Unit Tests

Create `src/__tests__/components/Loader.test.jsx`:

```javascript
import { render } from '@testing-library/react';
import Loader from '../../components/common/Loader';

describe('Loader Component', () => {
  it('renders loader', () => {
    const { container } = render(<Loader />);
    expect(container.querySelector('.animate-spin')).toBeInTheDocument();
  });
});
```

### Run Tests

```bash
# Backend
cd interviewprep-backend
mvn test

# Frontend
cd interviewprep-frontend
npm test
```

---

## 🔍 Performance Testing

### Database Query Performance
1. Check slow query log
2. Verify indexes are used
3. Analyze query execution plans

### Load Testing with JMeter
```bash
# Install JMeter
# Create test plan with:
# - 100 concurrent users
# - 1000 requests total
# - Login endpoint
# - Quiz retrieval endpoint
# - Results analysis
```

### Frontend Performance
```bash
# Check Lighthouse score
# Audit with DevTools
# Monitor bundle size
npm run build
```

---

## 🐛 Bug Testing

### Known Issue Areas
1. **Token Expiration**
   - [ ] Test after 24 hours
   - [ ] Verify refresh mechanism

2. **Database Constraints**
   - [ ] Try duplicate email registration
   - [ ] Try null fields
   - [ ] Try SQL injection

3. **Concurrent Operations**
   - [ ] Multiple quiz submissions
   - [ ] Simultaneous user updates
   - [ ] Race conditions

### Test Data
Use sample-data.sql for consistent test environment:
- 3 sample users
- 3 sample quizzes
- 6 sample questions
- 24 sample options

---

## 🔐 Security Testing

### Authentication
- [ ] JWT token validation
- [ ] Token expiration enforcement
- [ ] Password hashing verification
- [ ] Salt usage in BCrypt

### Authorization
- [ ] User can only access own profile
- [ ] Admin operations restricted to ROLE_ADMIN
- [ ] Cannot access /admin endpoints as regular user
- [ ] Cannot modify other user's data

### Input Validation
- [ ] SQL injection attempts blocked
- [ ] XSS attempts blocked
- [ ] CSRF protection enabled
- [ ] File upload restrictions

### API Security
- [ ] HTTPS enforced (production)
- [ ] CORS properly configured
- [ ] API rate limiting (recommended)
- [ ] Sensitive data logged minimally

---

## 📊 Browser Compatibility Testing

Test on multiple browsers:
- [ ] Chrome (latest)
- [ ] Firefox (latest)
- [ ] Safari (latest)
- [ ] Edge (latest)
- [ ] Mobile Chrome
- [ ] Mobile Safari

### Responsive Design
- [ ] Desktop (1920x1080)
- [ ] Tablet (768x1024)
- [ ] Mobile (375x667)
- [ ] All components responsive

---

## ✅ Pre-Deployment Testing Checklist

### Functionality
- [ ] All CRUD operations working
- [ ] Authentication flow complete
- [ ] Quiz attempt tracking accurate
- [ ] Score calculation correct
- [ ] Leaderboard ranking correct

### Performance
- [ ] Response time < 200ms
- [ ] Database queries optimized
- [ ] No N+1 query problems
- [ ] Static assets cached
- [ ] API compression enabled

### Security
- [ ] All endpoints authenticated
- [ ] Authorization rules enforced
- [ ] Input validation working
- [ ] Error messages don't expose sensitive data
- [ ] HTTPS configured (production)

### Database
- [ ] All migrations run successfully
- [ ] Data integrity constraints working
- [ ] Indexes present on foreign keys
- [ ] Backups configured
- [ ] Replication tested

### Deployment
- [ ] Docker images build successfully
- [ ] Environment variables set correctly
- [ ] Database connection stable
- [ ] Frontend loads without errors
- [ ] API documentation accessible

---

## 🔍 Quality Metrics

### Code Coverage
Target: 80%+
```bash
mvn test jacoco:report
# Check target/site/jacoco/index.html
```

### Code Quality
Use SonarQube:
```bash
mvn sonar:sonar \
  -Dsonar.projectKey=interviewprep \
  -Dsonar.sources=src \
  -Dsonar.host.url=http://localhost:9000
```

### Performance Metrics
- Lighhouse Score: 90+
- Time to First Contentful Paint: < 1s
- Time to Interactive: < 3s

---

## 📝 Test Cases Example

### Test Case 1: User Registration
**Title:** Valid User Registration
**Precondition:** No existing user with email
**Steps:**
1. Navigate to /register
2. Enter: Name="John", Email="john@test.com", Password="Pass@123"
3. Click Submit

**Expected Result:**
- Account created
- Redirected to dashboard
- Toast: "Registration successful"
- User logged in

### Test Case 2: Quiz Submission
**Title:** Complete Quiz with Correct Answers
**Precondition:** User logged in, Quiz available
**Steps:**
1. Click quiz
2. Select all correct answers
3. Click Submit

**Expected Result:**
- Score: 100%
- Correct answers: All
- Result page displays
- Score saved to database

### Test Case 3: Admin Quiz Creation
**Title:** Create Quiz with Questions
**Precondition:** Logged in as admin
**Steps:**
1. Go to Admin Panel
2. Click Create Quiz
3. Enter details
4. Add 3 questions with options
5. Submit

**Expected Result:**
- Quiz created
- Questions saved
- Appears in quiz list
- Can be attempted by users

---

## 🚀 Continuous Integration

### GitHub Actions Setup
Create `.github/workflows/test.yml`:

```yaml
name: Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Run backend tests
        run: |
          cd interviewprep-backend
          mvn test
      - name: Run frontend tests
        run: |
          cd interviewprep-frontend
          npm install
          npm test
```

---

## 📞 Testing Support

For testing issues:
1. Check application logs: `docker logs container-name`
2. Review database state: `mysql -u root -p interviewprep_db`
3. Check browser console for JS errors
4. Verify API responses in Network tab

---

## ✨ Final Notes

- Keep sample data consistent
- Reset database before each full test cycle
- Document any new issues found
- Update test cases as features change
- Maintain test data backup

**Happy Testing! 🎉**
