# Quick Start Guide - InterviewPrep AI

## ⚡ 5-Minute Setup with Docker

### Prerequisites
- Docker and Docker Compose installed
- Gemini API key (free from Google AI Studio)

### Setup Steps

1. **Clone or download the project**
   ```bash
   cd interviewprep
   ```

2. **Get your Gemini API key**
   - Visit: https://makersuite.google.com/app/apikey
   - Create new API key
   - Copy the key

3. **Create .env file**
   ```bash
   echo "GEMINI_API_KEY=your-gemini-key-here" > .env
   ```

4. **Run the application**
   ```bash
   # Linux/Mac
   chmod +x setup.sh && ./setup.sh
   
   # Windows
   setup.bat
   ```

5. **Wait for containers to start** (2-3 minutes)

6. **Access the application**
   - **Frontend**: http://localhost:3000
   - **Backend API**: http://localhost:8080
   - **API Docs**: http://localhost:8080/swagger-ui.html

## 📝 First Steps

### Create Admin Account
1. Register new user at http://localhost:3000/register
   - Name: Admin User
   - Email: admin@example.com
   - Password: Admin@123

### Manually Assign Admin Role (Optional)
```bash
docker exec interviewprep-mysql mysql -uroot -proot -e "
USE interviewprep_db;
UPDATE user_roles SET role_id = 2 WHERE user_id = 1;
"
```

### Create Your First Quiz
1. Login with admin account
2. Navigate to Admin Panel
3. Click "Create Quiz"
4. Fill in quiz details:
   - Title: "My First Quiz"
   - Category: "DSA"
   - Difficulty: "Easy"
   - Duration: 30 minutes
5. Add questions with multiple choice options
6. Save quiz

### Take a Quiz as User
1. Register as a regular user
2. Go to Dashboard
3. Click on a quiz to attempt
4. Answer questions
5. Click "Explain with AI" for explanations
6. Check your score

## 🛑 Stop the Application

```bash
docker-compose down
```

## 🔧 Manual Setup (Without Docker)

### Backend Setup
```bash
cd interviewprep-backend

# Configure MySQL
# 1. Create database
mysql> CREATE DATABASE interviewprep_db;

# 2. Update application.yml with your MySQL credentials

# 3. Build and run
mvn clean package
mvn spring-boot:run
```

### Frontend Setup
```bash
cd interviewprep-frontend

npm install
npm run dev
```

## 📚 Sample Credentials

After running sample-data.sql:

**Admin:**
- Email: admin@interviewprep.com
- Password: password123 (change this!)

**Regular User:**
- Email: john@example.com
- Password: password123

## ⚠️ Important Notes

- Change all default passwords in production
- Get Gemini API key for AI explanations
- Configure proper JWT secret in production
- Use HTTPS in production
- Set up proper database backups

## 🆘 Troubleshooting

### Port Already in Use
```bash
# Change port in docker-compose.yml or .env
# For backend: 8081:8080
# For frontend: 3001:3000
```

### Database Connection Error
```bash
# Check if MySQL is running
docker ps

# Check logs
docker logs interviewprep-mysql
```

### Frontend Can't Connect to Backend
- Verify backend is running: http://localhost:8080
- Check VITE_API_BASE_URL in .env
- Check browser console for errors

### Out of Memory
```bash
# Increase Docker memory allocation
# Docker Desktop → Preferences → Resources
```

## 📖 Next Steps

1. Read [README.md](./README.md) for comprehensive documentation
2. Read [DEPLOYMENT.md](./DEPLOYMENT.md) for production deployment
3. Explore [Swagger API Docs](http://localhost:8080/swagger-ui.html)
4. Check [Source Code Structure](./interviewprep-backend) 
5. Customize UI in [Frontend Components](./interviewprep-frontend/src)

## 🚀 Production Deployment

When ready for production:
1. Follow [DEPLOYMENT.md](./DEPLOYMENT.md)
2. Use managed database (AWS RDS, Planetscale, etc.)
3. Deploy backend to Railway/Render
4. Deploy frontend to Vercel/Netlify
5. Set up SSL certificates
6. Configure domain name

## 💡 Quick Tips

- **API Base URL**: http://localhost:8080/api
- **Default Admin Role ID**: 2
- **Default User Role ID**: 1
- **JWT Expiration**: 24 hours
- **Quiz Duration**: In minutes
- **Score Calculation**: (Correct Answers / Total Questions) * 100

## 📞 Support

- Check GitHub Issues
- Read documentation in README.md
- Review error logs: `docker logs container-name`
- Check application logs: `docker-compose logs -f`

---

**Ready to go! Happy coding! 🎉**
