#!/usr/bin/env bash

docker-compose -f consul-servers.yml up -d --build
docker-compose -f user-service.yml up -d --build
docker-compose -f demo-service.yml up -d --build

