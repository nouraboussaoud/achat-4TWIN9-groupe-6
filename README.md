# TP DevOps – Front-Achats / Back-Achats

This is a complete DevOps project simulating a purchase management system using Angular and Spring Boot. It integrates full CI/CD pipelines, monitoring, code quality checks, and artifact management using tools like Jenkins, Docker, SonarQube, Nexus, Prometheus, and Grafana.

---

## ✅ Project Goals

- Implement CI/CD using Jenkins and Docker
- Ensure code quality via SonarQube
- Store build artifacts in Nexus Repository
- Monitor system metrics using Prometheus & Grafana

---

## 🧱 Tech Stack

| Layer        | Tools & Technologies                            |
|--------------|-------------------------------------------------|
| Frontend     | Angular, TypeScript, HTML5, CSS3                |
| Backend      | Java 17, Spring Boot, Maven                     |
| Database     | MySQL                                           |
| CI/CD        | Git, GitHub, Jenkins, Docker, Docker Compose    |
| Code Quality | SonarQube                                       |
| Artifacts    | Nexus Repository Manager                        |
| Monitoring   | Prometheus, Grafana                             |

---

## 📁 Project Structure

```
achat-project/
├── front-achats/           # Angular application
│   └── Dockerfile
├── back-achats/            # Spring Boot application
│   ├── pom.xml
│   └── Dockerfile
├── docker-compose.yml
├── Jenkinsfile
└── README.md
```

---

## ⚙️ How to Run the Project

### 1. Prerequisites

- Node.js + Angular CLI
- Java 17 + Maven
- Docker & Docker Compose
- Jenkins + SonarQube + Nexus
- Prometheus + Grafana

### 2. Clone the Repository

```bash
git clone https://github.com/nouraboussaoud/achat-project.git
cd achat-project
```

### 3. Start All Services

```bash
docker-compose up --build
```

---

## 🐳 Docker Compose

## 🚀 Jenkins Pipeline

## 📊 Monitoring with Prometheus & Grafana

- Prometheus collects metrics from Spring Boot actuator endpoints (`/actuator/prometheus`)
- Grafana dashboards visualize the metrics (requests, memory, CPU, etc.)
- Access:
  - Prometheus: [http://localhost:9090](http://localhost:9090)
  - Grafana: [http://localhost:3000](http://localhost:3000) (admin/admin)

---

## ✅ SonarQube Integration

- Access: [http://localhost:9000](http://localhost:9000)
- Analyze backend code quality
- Displays bugs, code smells, security hotspots, etc.

---

## 📦 Nexus Repository

- Access: [http://localhost:8081](http://localhost:8081)
- Stores Maven artifacts (e.g., Spring Boot `.jar` files)
- Acts as private artifact registry for deployments

---

## ✍️ Author

**Nour Aboussaoud**    
[GitHub Profile](https://github.com/nouraboussaoud)

---

## ⭐ Support

If you found this project helpful, feel free to **star** it or **fork** it. Contributions are welcome!
