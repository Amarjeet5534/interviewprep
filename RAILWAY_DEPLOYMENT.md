# Railway Deployment Guide

## Step-by-Step Deployment

### 1. Connect GitHub to Railway
- Go to [https://railway.app/dashboard](https://railway.app/dashboard)
- Click **New Project** → **Deploy from GitHub repo**
- Select `Amarjeet5534/interviewprep`
- Click **Deploy**

### 2. Add Services

Railway will auto-detect services from your repo structure. You need:

#### Service 1: MySQL Database
- Click **Add Service** → **Database** → **MySQL**
- Use default settings
- Railway will auto-generate `DATABASE_URL`, `DATABASE_USER`, `DATABASE_PASSWORD`

#### Service 2: Backend (Spring Boot)
- Click **Add Service** → **GitHub repo**
- Select this repo and branch: `interviewprep-backend/` directory
- Set startup command: `java -jar app.jar`
- Environment variables:
  ```
  JWT_SECRET=your-super-secret-key
  JWT_EXPIRATION=86400000
  GEMINI_API_KEY=your-gemini-api-key
  SPRING_DATASOURCE_URL=${{DATABASE_URL}}
  SPRING_DATASOURCE_USERNAME=${{DATABASE_USER}}
  SPRING_DATASOURCE_PASSWORD=${{DATABASE_PASSWORD}}
  ```

#### Service 3: Frontend (React)
- Click **Add Service** → **GitHub repo**
- Select this repo and branch: `interviewprep-frontend/` directory
- Set startup command: `npm run build && npm run preview`
- Environment variable:
  ```
  VITE_API_BASE_URL=https://<your-backend-domain>/api
  ```
  (Replace `<your-backend-domain>` with the actual backend domain Railway provides)

### 3. Enable Domains
- Go to each service
- Click **Settings** → **Domains**
- Generate public URL for backend and frontend

### 4. Set Environment Variables
- For each service, click **Variables**
- Add the variables listed above

### 5. Deploy
- Railway will auto-build and deploy all services
- You'll get public URLs for frontend and backend

### 6. Test
- Open frontend URL
- Register/Login
- Take a quiz
- Check leaderboard

## Environment Variables Reference

**Backend needs:**
- `JWT_SECRET` - Change this to a strong secret
- `GEMINI_API_KEY` - Your Google Gemini API key
- `DATABASE_URL` - Auto-provided by Railway MySQL
- `DATABASE_USER` - Auto-provided by Railway MySQL
- `DATABASE_PASSWORD` - Auto-provided by Railway MySQL

**Frontend needs:**
- `VITE_API_BASE_URL` - Backend API URL (Railway domain)

## Notes
- Railway free tier includes:
  - 500 hours/month compute
  - 5GB storage
  - MySQL database
- Domains are auto-generated and public
- Builds happen automatically on push to GitHub

## Troubleshooting
- Check **Deployments** tab for logs
- Ensure all environment variables are set
- Backend needs healthy MySQL before starting
- Frontend needs correct `VITE_API_BASE_URL` to reach backend
