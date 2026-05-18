# Deployment Guide for InterviewPrep AI

## Prerequisites
- Docker and Docker Compose installed
- Git account and repository
- Cloud provider account (Railway, Render, Vercel, Netlify, etc.)
- Gemini API key from Google

## Local Development Setup

### Using Docker Compose (Recommended)

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd interviewprep
   ```

2. **Set environment variables**
   ```bash
   # On Linux/Mac
   export GEMINI_API_KEY=your-gemini-api-key
   
   # On Windows PowerShell
   $env:GEMINI_API_KEY="your-gemini-api-key"
   ```

3. **Run setup script**
   ```bash
   # On Linux/Mac
   chmod +x setup.sh
   ./setup.sh
   
   # On Windows
   setup.bat
   ```

4. **Access the application**
   - Frontend: http://localhost:3000
   - Backend: http://localhost:8080
   - Swagger Docs: http://localhost:8080/swagger-ui.html

## Production Deployment

### Option 1: Railway Deployment (Backend)

1. **Push code to GitHub**
   ```bash
   git add .
   git commit -m "Initial commit"
   git push origin main
   ```

2. **Connect to Railway**
   - Visit https://railway.app
   - Click "New Project"
   - Select "Deploy from GitHub"
   - Choose your repository

3. **Configure environment variables**
   ```
   SPRING_DATASOURCE_URL=<your-mysql-url>
   SPRING_DATASOURCE_USERNAME=<username>
   SPRING_DATASOURCE_PASSWORD=<password>
   JWT_SECRET=<your-secret-key>
   GEMINI_API_KEY=<your-gemini-key>
   ```

4. **Deploy**
   - Railway will automatically build and deploy

### Option 2: Render Deployment (Backend)

1. **Connect repository to Render**
   - Visit https://render.com
   - Click "New+"
   - Select "Web Service"
   - Connect GitHub repository

2. **Configure build and start commands**
   ```
   Build: mvn clean package
   Start: java -jar target/*.jar
   ```

3. **Set environment variables**
   - Add all necessary environment variables in Render dashboard

4. **Deploy**
   - Render will build and deploy automatically

### Option 3: Vercel Deployment (Frontend)

1. **Push code to GitHub**

2. **Connect to Vercel**
   - Visit https://vercel.com
   - Click "New Project"
   - Import GitHub repository

3. **Configure environment variables**
   ```
   VITE_API_BASE_URL=<your-backend-api-url>
   ```

4. **Deploy**
   - Vercel will automatically build and deploy

### Option 4: Netlify Deployment (Frontend)

1. **Build the frontend**
   ```bash
   cd interviewprep-frontend
   npm run build
   ```

2. **Deploy to Netlify**
   ```bash
   npm install -g netlify-cli
   netlify deploy --prod --dir=dist
   ```

Or connect GitHub and Netlify will auto-deploy:
- Visit https://netlify.com
- Click "Add new site"
- Choose "Import an existing project"
- Connect GitHub
- Set build command: `npm run build`
- Set publish directory: `dist`

### Option 5: Database Setup (MySQL)

#### Using AWS RDS
1. Go to AWS RDS dashboard
2. Create new DB instance (MySQL 8.0)
3. Note the endpoint
4. Set in backend environment:
   ```
   SPRING_DATASOURCE_URL=jdbc:mysql://<endpoint>:3306/interviewprep_db
   ```

#### Using Planetscale
1. Visit https://planetscale.com
2. Create new database
3. Get connection string
4. Set in backend environment:
   ```
   SPRING_DATASOURCE_URL=<planetscale-connection-string>
   ```

#### Using DBeaver/MySQL Workbench
1. Create new database
2. Run sample-data.sql to populate data
3. Update connection string in backend

## Docker Deployment

### Push to Docker Hub

1. **Build images**
   ```bash
   # Backend
   cd interviewprep-backend
   docker build -t yourusername/interviewprep-backend:latest .
   
   # Frontend
   cd interviewprep-frontend
   docker build -t yourusername/interviewprep-frontend:latest .
   ```

2. **Push to Docker Hub**
   ```bash
   docker login
   docker push yourusername/interviewprep-backend:latest
   docker push yourusername/interviewprep-frontend:latest
   ```

3. **Deploy on any Docker-supported platform**
   - AWS ECS, Google Cloud Run, Azure Container Instances, DigitalOcean, etc.

## Kubernetes Deployment

Create `k8s-deployment.yaml`:

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: interviewprep-backend
spec:
  replicas: 2
  selector:
    matchLabels:
      app: interviewprep-backend
  template:
    metadata:
      labels:
        app: interviewprep-backend
    spec:
      containers:
      - name: backend
        image: yourusername/interviewprep-backend:latest
        ports:
        - containerPort: 8080
        env:
        - name: SPRING_DATASOURCE_URL
          valueFrom:
            secretKeyRef:
              name: db-secret
              key: url
        - name: SPRING_DATASOURCE_USERNAME
          valueFrom:
            secretKeyRef:
              name: db-secret
              key: username
        - name: SPRING_DATASOURCE_PASSWORD
          valueFrom:
            secretKeyRef:
              name: db-secret
              key: password

---
apiVersion: v1
kind: Service
metadata:
  name: interviewprep-backend
spec:
  selector:
    app: interviewprep-backend
  ports:
  - protocol: TCP
    port: 8080
    targetPort: 8080
  type: LoadBalancer
```

Deploy:
```bash
kubectl apply -f k8s-deployment.yaml
```

## SSL Certificate Setup

### Using Let's Encrypt with Nginx

1. **Install Certbot**
   ```bash
   apt-get install certbot python3-certbot-nginx
   ```

2. **Generate certificate**
   ```bash
   certbot certonly --standalone -d yourdomain.com
   ```

3. **Configure Nginx**
   ```nginx
   server {
       listen 443 ssl;
       server_name yourdomain.com;
       
       ssl_certificate /etc/letsencrypt/live/yourdomain.com/fullchain.pem;
       ssl_certificate_key /etc/letsencrypt/live/yourdomain.com/privkey.pem;
       
       location / {
           proxy_pass http://localhost:3000;
       }
   }
   ```

## Monitoring and Logging

### Backend Monitoring
- Enable Spring Boot Actuator
- Use CloudWatch (AWS) or similar monitoring service
- Set up alerts for errors

### Database Backups
```bash
# MySQL backup
mysqldump -u root -p interviewprep_db > backup.sql

# Restore
mysql -u root -p interviewprep_db < backup.sql
```

## Performance Optimization

1. **Enable compression in Spring Boot**
   ```yaml
   server:
     compression:
       enabled: true
   ```

2. **Configure database connection pooling**
   ```yaml
   spring:
     datasource:
       hikari:
         maximum-pool-size: 20
   ```

3. **Frontend optimization**
   - Enable code splitting in Vite
   - Use lazy loading for routes
   - Optimize images

## Troubleshooting

### Backend not connecting to database
- Check database URL and credentials
- Verify database is running
- Check network connectivity

### Frontend API calls failing
- Check API URL in .env
- Verify backend is running
- Check CORS configuration

### Performance issues
- Check database queries
- Monitor memory usage
- Check network latency

## Security Checklist

- [ ] Change default JWT secret
- [ ] Use strong database password
- [ ] Enable HTTPS
- [ ] Set up rate limiting
- [ ] Configure CORS properly
- [ ] Enable SQL injection prevention
- [ ] Use environment variables for secrets
- [ ] Regular security updates
- [ ] Enable logging and monitoring
- [ ] Set up backups

## Support

For deployment support, refer to:
- Spring Boot docs: https://spring.io/projects/spring-boot
- Vite docs: https://vitejs.dev/
- Docker docs: https://docs.docker.com/
- Your cloud provider's documentation

---

Happy deploying! 🚀
