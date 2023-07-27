#!/bin/bash
if [[ ! -f ~/.mongoenv ]]; then
  echo 'Could not find mongodb environment variable file in home directory';
  echo 'Please make sure you have "MONGO_USER" and "MONGO_PASSWORD" environment variables properly set';
else
  source ~/.mongoenv;
fi

container_name=ktdemo-mongo
docker container inspect $container_name 2>&1 >/dev/null
if [[ $? -eq 0 ]]; then
  echo "Starting container '${container_name}'...";
  docker start $container_name;
else
  echo "Creating container '${container_name}'...";
  docker run -d --hostname $container_name --name $container_name -e MONGO_INITDB_ROOT_USERNAME="${MONGO_USER}" -e MONGO_INITDB_ROOT_PASSWORD="${MONGO_PASSWORD}" -v "$(pwd)/data:/data/db" -p 27017:27017 mongo
fi