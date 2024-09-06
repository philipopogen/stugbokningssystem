#!/bin/bash

# Variables
IMAGE_NAME="stugbokningssystem"
HOST_PORT1=80
CONTAINER_PORT1=80
HOST_PORT2=9090
CONTAINER_PORT2=9090 

# Step 1: Build the Docker image
echo "Building Docker image '${IMAGE_NAME}'..."
docker build -t $IMAGE_NAME .

# Check if the image build was successful
if [ $? -eq 0 ]; then
    echo "Image '${IMAGE_NAME}' built successfully."
else
    echo "Failed to build Docker image."
    exit 1
fi

# Step 2: Run the Docker container with two port mappings
echo "Running container '${IMAGE_NAME}' with port mappings ${HOST_PORT1}:${CONTAINER_PORT1} and ${HOST_PORT2}:${CONTAINER_PORT2}..."
docker run -p $HOST_PORT1:$CONTAINER_PORT1 -p $HOST_PORT2:$CONTAINER_PORT2 $IMAGE_NAME

# Check if the container ran successfully
if [ $? -eq 0 ]; then
    echo "Container '${IMAGE_NAME}' is running with port mappings ${HOST_PORT1}:${CONTAINER_PORT1} and ${HOST_PORT2}:${CONTAINER_PORT2}."
else
    echo "Failed to run Docker container."
    exit 1
fi
