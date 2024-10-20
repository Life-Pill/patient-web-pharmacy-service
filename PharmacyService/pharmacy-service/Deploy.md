# Deploying the LifePill Pharmacy Service

This document provides step-by-step instructions on how to build and deploy the `lifepill-pharmacy-service` application using Maven and Docker.

## Prerequisites

Ensure you have the following tools installed:
- [Maven](https://maven.apache.org/install.html)
- [Docker](https://docs.docker.com/get-docker/)

## Build and Package the Application

1. **Navigate to the project directory**:
   Open a terminal and navigate to the root of your project directory.

   ```bash
   cd /path/to/your/project
   ```

2. **Clean and build the project**:
   Run the following Maven command to clean the project and install the necessary dependencies while skipping the tests.

   ```bash
   mvn clean install -DskipTests
   ```

   This will generate the JAR file (`lifepill-pharmacy-service.jar`) inside the `target/` directory.

## Build the Docker Image

1. **Create a Docker image**:
   Once the JAR file is generated, build the Docker image using the following command:

   ```bash
   docker build -t lifepill-pharmacy-service .
   ```

   The Dockerfile should be present in the root of the project and will package the application into a container.

2. **Verify the image**:
   Check if the image was built successfully by running:

   ```bash
   docker images
   ```

   You should see `lifepill-pharmacy-service` in the list of Docker images.

## Run the Docker Container

1. **Run the container**:
   Start the container in detached mode (`-d` flag) and map port `8075` from the container to your local machine:

   ```bash
   docker run -p 8075:8075 lifepill-pharmacy-service -d
   ```

   This command will run the application in the background, and it will be accessible on `http://localhost:8075`.

2. **Check running containers**:
   To verify that the container is running, use the following command:

   ```bash
   docker ps
   ```

   You should see `lifepill-pharmacy-service` listed as one of the running containers.

## Stopping the Docker Container

If you need to stop the container, use the following command:

```bash
docker stop <container_id>
```

You can find the `<container_id>` by running `docker ps`.

---

This `deploy.md` file outlines the entire process of building, packaging, and deploying the application with the appropriate commands and structure.