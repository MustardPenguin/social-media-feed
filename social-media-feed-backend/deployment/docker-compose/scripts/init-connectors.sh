
# Gets IP address of Debezium Postgres container
DATABASE_HOSTNAME=$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' postgres-cdc)
DATABASE_USER="user"
DATABASE_PASSWORD="admin"
SLEEP_TIME=1

# Json config
post_created_json=$(jq -n \
  --arg dbh "$DATABASE_HOSTNAME" \
  --arg user "$DATABASE_USER" \
  --arg password "$DATABASE_PASSWORD" \
  '{
    "name": "post-created-events-connector",
    "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "tasks.max": "1",
      "database.hostname": $dbh,
      "database.user": $user,
      "database.password": $password,
      "database.dbname": "postgres",
      "table.include.list": "post.post_created_events",
      "topic.prefix": "post_created",
      "tombstones.on.delete" : "false",
      "slot.name": "post_created_slot",
      "plugin.name": "pgoutput"
    }
  }')

follow_created_json=$(jq -n \
  --arg dbh "$DATABASE_HOSTNAME" \
  --arg user "$DATABASE_USER" \
  --arg password "$DATABASE_PASSWORD" \
  '{
    "name": "follow-created-events-connector",
    "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "tasks.max": "1",
      "database.hostname": $dbh,
      "database.user": $user,
      "database.password": $password,
      "database.dbname": "postgres",
      "table.include.list": "account.follow_created_events",
      "topic.prefix": "follow_created",
      "tombstones.on.delete" : "false",
      "slot.name": "follow_created_slot",
      "plugin.name": "pgoutput"
    }
  }')

# Will create topics following the pattern: <topic.prefix>.<schema>.<table>
curl -X POST -H "Content-Type: application/json" --data "$post_created_json" http://localhost:8083/connectors
sleep $SLEEP_TIME;
curl -X POST -H "Content-Type: application/json" --data "$follow_created_json" http://localhost:8083/connectors
sleep $SLEEP_TIME;