# Builds a docker image with the necessary jar files to work with Confluent schema registry
FROM debezium/connect:2.5

# Run 'docker build -t com.social.media.feed/debezium-connect:1.0-SNAPSHOT -f ./debezium.dockerfile .' at the docker-compose directory to build this image
RUN mkdir /kafka/connect/debezium-connector-schemaregistry
COPY ./debezium-dependencies /kafka/connect/debezium-connector-schemaregistry