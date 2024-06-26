#!/bin/sh
# Source: https://medium.com/@katyagorshkova/docker-compose-for-running-kafka-in-kraft-mode-20c535c48b1a
# Github: https://github.com/katyagorshkova/kafka-kraft/tree/main

file_path="/tmp/clusterID/clusterID"

# If file clusterID is empty, create a cluster id in the file
if [ ! -f "$file_path" ]; then
  /bin/kafka-storage random-uuid > /tmp/clusterID/clusterID
  echo "Cluster id has been  created..."
fi