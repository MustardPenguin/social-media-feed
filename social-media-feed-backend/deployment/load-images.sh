#!/bin/bash

# Loads images into Kind cluster
# Assumes that the images are already built and tagged (run 'mvn clean install' at the root of the backend)

GROUP_ID=com.social.media.feed
VERSION=1.0-SNAPSHOT
CLUSTER_NAME=kind-control-plane

(docker image ls | grep $GROUP_ID) | while read -r line; do
  read -r -a words <<< "$line";
  image_name=${words[0]};
  image_version=${words[1]};
  image="$image_name:$image_version";
  echo "Loading image $image";
  kind load docker-image $image;
done

# Image needed in cluster for Debezium
docker pull curlimages/curl
kind load docker-image curlimages/curl

printf "\n Getting images from control plane... \n" | xargs
docker exec -it $CLUSTER_NAME crictl images | grep $GROUP_ID