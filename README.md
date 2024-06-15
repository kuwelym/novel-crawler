# Novel Crawler
![image](https://img.shields.io/badge/Vue%20js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D) ![image](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white) ![image](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)


This project is a web application designed to crawl and display novels from various sources. It is built with a Java backend using Spring Boot and a Vue.js frontend. The application is containerized using Docker.

## Table of Contents
1. [Technologies](#technologies)
2. [System Requirements](#requirements)
3. [Installation Guide](#installation)
4. [Running the Project](#running)
5. [Testing the Project](#testing)
6. [Troubleshooting](#troubleshooting)
7. [Contributing](#contributing)
8. [Contributors](#contributors)

## Technologies <a name="Technologies"></a>
* Frontend: Vue.js, HTML, CSS, JavaScript
* Backend: Java, Spring Boot, RESTful APIs
* Containerization: Docker, Docker Compose

## System Requirements <a name="requirements"></a>
### Software
* For CLI or IDE:
  * Java Development Kit (JDK)
  * System Variables JAVA_HOME
  * Node.js
* For Docker:
  * Docker

### Hardware
* Sufficient resources to run Docker containers

## Installation Guide <a name="installation"></a>
### Clone the Repository
You can clone the repository from GitHub using the following command:
```bash
git clone https://github.com/kuwelym/novel-crawler
```
Alternatively, you can download it as a .zip file and extract it to your local machine.
## Running the Project <a name="running"></a>
### Using Docker
1. Navigate to the project directory.
```bash
cd novel-crawler
```
2. Use Docker to build and run the application:
```bash
docker-compose up -d
```

If the process completes successfully, the application should be up and running.

### Using CLI
#### Backend
1. Navigate to the backend directory:
```bash
cd novel-crawler/back-end
```
2. Clean and install dependencies:
```bash
mvn clean install
```
3. Run the Spring Boot application:
```bash
mvn spring-boot:run
```

#### Frontend
1. Navigate to the frontend directory:
```bash
cd novel-crawler/front-end
```
2. Clean and install dependencies:
```bash
npm install
```
3. Run the Vue.js application:
```bash
npm run serve
```

## Testing the Project <a name="testing"></a>
1. Navigate to the backend directory:
```bash
cd novel-crawler/back-end
```
2. Run the tests:
```bash
mvn test -f pom.xml
```

## Troubleshooting <a name="troubleshooting"></a>
If you encounter issues while setting up or running the project, here are some common solutions:

### Docker Issues
* Docker not found: Ensure Docker is installed and running on your machine. Refer to the [Docker installation guide](https://docs.docker.com/engine/install/) for assistance.
* Port conflicts: Check if the ports used by the application (3000, 5050, 8080) are free. If not, modify the `docker-compose.yml` file to use different ports.
### Backend Issues
* JAVA_HOME not set: Ensure the `JAVA_HOME` environment variable is set correctly. You can set it by adding the following to your system's environment variables:
```bash
export JAVA_HOME=/path/to/your/jdk
```
* Maven build issues: Ensure Maven is installed and properly configured. Run `mvn -v` to check your Maven installation.
### Frontend Issues
* Node.js not found: Ensure Node.js is installed on your machine. Refer to the Node.js installation guide for assistance.
* Dependency issues: If `npm install` fails, try deleting the `node_modules` folder and `package-lock.json` file, then run `npm install` again.

## Contributing <a name="contributing"></a>
We welcome contributions to improve the Novel Crawler project! Here’s how you can contribute:

1. Fork the Repository: Click the "Fork" button at the top of this repository to create a copy in your GitHub account.
2. Clone Your Fork: Clone your forked repository to your local machine:
```bash
git clone https://github.com/kuwelym/novel-crawler
```
3. Create a Branch: Create a new branch for your feature or bugfix:
```bash
git checkout -b feature-or-bugfix-name
```
4. Make Changes: Implement your changes in the new branch.
5. Commit and Push: Commit your changes and push them to your forked repository:
```bash
git add .
git commit -m "Description of your changes"
git push origin feature-or-bugfix-name
```
6. Create a Pull Request: Open a pull request to the main branch of the original repository. Provide a clear description of your changes.
For major changes, please open an issue first to discuss what you would like to change.

Thank you for contributing!

## Contributors <a name="contributors"></a>

[![Contributors](https://contrib.rocks/image?repo=kuwelym/novel-crawler)](https://github.com/kuwelym/novel-crawler/graphs/contributors)
