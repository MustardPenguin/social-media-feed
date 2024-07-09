# Social Media Feed

A real-time social media feed designed as event-driven microservices.

<h2>Table of contents</h2>

 * [Running locally for development](#running-locally-for-development)
 * [Running with Helm charts](#running-locally-with-helm-charts)


## Running locally for development

This project was tested and developed on Mac and Linux, thus the instructions may not work on other operating systems.

To start, run infrastructureV2.yaml with docker compose, found in social-media-feed-backend/deployment/docker-compose. 

InfrastructureV1.yaml works as well and uses Zookeeper, but is deprecated. V2 uses Kafka in Kafka Raft mode which is the recommended approach.

This should run the needed infrastructure necessary for the microservices to start, including -
 * 3 Kafka brokers
 * Confluent Schema registry
 * Kafka UI
 * Debezium
 * Postgres
 * Redis

Additionally, if V2 is used then it will also automatically generate a cluster id in the clusterID directory if it is empty. That cluster id can be used for the CLUSTER_ID environment variable in the docker compose file.

Afterwards, run the init-connectors.sh script found in the scripts directory, this should send POST requests to the running Debezium containers for Change Data Capture. Validate that the connectors are running at localhost:9000 at the connect-status topic.

The infrastructure should be ready at this point, so run the Eureka service, which can be accessed at localhost:8761.

Finally, run all the microservices in any order -
 * API Gateway
 * Account service
 * Post service
 * Feed service

The microservices should register itself with Eureka once it is finished, and the application is now ready for development.

## Running locally with Helm charts
