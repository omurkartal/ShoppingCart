#!/bin/bash

echo '*********************************************************************'
echo 'Installing app'
mvn -f pom.xml clean install -U

echo '*********************************************************************'
echo "remove containers and images created before..."
docker container rm -f $(docker ps -aq --filter="name=omurka/shopping-cart")
docker rmi -f $(docker images --format '{{.Repository}}:{{.Tag}}' | grep 'omurka/shopping-cart')

echo '*********************************************************************'
echo 'building image..'
docker build -t omurka/shopping-cart --no-cache .

echo '*********************************************************************'
echo 'Docker images built.'
