#!/usr/bin/env bash

docker-compose -f consul-servers.yml down
docker-compose -f user-service.yml down
docker-compose -f demo-service.yml down

