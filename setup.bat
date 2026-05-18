@echo off
REM InterviewPrep Setup Script for Windows

echo 🚀 InterviewPrep Setup Script
echo ==============================
echo.

REM Check if Docker is installed
docker --version >nul 2>&1
if errorlevel 1 (
    echo ❌ Docker is not installed. Please install Docker first.
    exit /b 1
)

echo ✅ Docker found

REM Check if Docker Compose is installed
docker-compose --version >nul 2>&1
if errorlevel 1 (
    echo ❌ Docker Compose is not installed. Please install Docker Compose first.
    exit /b 1
)

echo ✅ Docker Compose found

REM Create .env file if it doesn't exist
if not exist ".env" (
    echo Creating .env file...
    (
        echo GEMINI_API_KEY=your-gemini-api-key
    ) > .env
    echo ⚠️  Please update .env with your Gemini API key
)

REM Build and start containers
echo.
echo Building and starting containers...
docker-compose up --build

echo.
echo ✅ Setup complete!
echo.
echo Access the application:
echo Frontend: http://localhost:3000
echo Backend API: http://localhost:8080
echo Swagger Docs: http://localhost:8080/swagger-ui.html
