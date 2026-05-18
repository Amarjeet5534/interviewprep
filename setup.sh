#!/bin/bash

# InterviewPrep Setup Script

echo "🚀 InterviewPrep Setup Script"
echo "=============================="
echo ""

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo "❌ Docker is not installed. Please install Docker first."
    exit 1
fi

echo "✅ Docker found"

# Check if Docker Compose is installed
if ! command -v docker-compose &> /dev/null; then
    echo "❌ Docker Compose is not installed. Please install Docker Compose first."
    exit 1
fi

echo "✅ Docker Compose found"

# Create .env file if it doesn't exist
if [ ! -f ".env" ]; then
    echo "Creating .env file..."
    echo "GEMINI_API_KEY=your-gemini-api-key" > .env
    echo "⚠️  Please update .env with your Gemini API key"
fi

# Build and start containers
echo ""
echo "Building and starting containers..."
docker-compose up --build

echo ""
echo "✅ Setup complete!"
echo ""
echo "Access the application:"
echo "Frontend: http://localhost:3000"
echo "Backend API: http://localhost:8080"
echo "Swagger Docs: http://localhost:8080/swagger-ui.html"
