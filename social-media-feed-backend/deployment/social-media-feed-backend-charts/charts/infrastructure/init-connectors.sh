
# Debezium variables
DEBEZIUM_HOSTNAME="http://debezium-service:8083/connectors"
SLEEP_TIME=1

# Database variables
ACCOUNT_DATABASE_HOSTNAME="account-postgres-0.postgres-headless.default.svc.cluster.local"
ACCOUNT_DATABASE_USER="user"
ACCOUNT_DATABASE_PASSWORD="admin"

POST_DATABASE_HOSTNAME="post-postgres-0.postgres-headless.default.svc.cluster.local"
POST_DATABASE_USER="user"
POST_DATABASE_PASSWORD="admin"

POST_DATABASE_HOSTNAME="post-postgres-0.postgres-headless.default.svc.cluster.local"
POST_DATABASE_USER="user"
POST_DATABASE_PASSWORD="admin"

post_created_json=$(cat <<EOF
{
  "name": "post-created-events-connector",
  "config": {
    "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
    "tasks.max": "1",
    "database.hostname": "$POST_DATABASE_HOSTNAME",
    "database.user": "$POST_DATABASE_USER",
    "database.password": "$POST_DATABASE_PASSWORD",
    "database.dbname": "postgres",
    "table.include.list": "post.post_created_events",
    "topic.prefix": "post_created",
    "tombstones.on.delete" : "false",
    "slot.name": "post_created_slot",
    "plugin.name": "pgoutput"
  }
}
EOF
)

follow_created_json=$(cat <<EOF
{
  "name": "follow-created-events-connector",
  "config": {
    "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
    "tasks.max": "1",
    "database.hostname": "$ACCOUNT_DATABASE_HOSTNAME",
    "database.user": "$ACCOUNT_DATABASE_USER",
    "database.password": "$ACCOUNT_DATABASE_PASSWORD",
    "database.dbname": "postgres",
    "table.include.list": "account.follow_created_events",
    "topic.prefix": "follow_created",
    "tombstones.on.delete" : "false",
    "slot.name": "follow_created_slot",
    "plugin.name": "pgoutput"
  }
}
EOF
)

# Will create topics following the pattern: <topic.prefix>.<schema>.<table>
curl -X POST -H "Content-Type: application/json" --data "$post_created_json" $DEBEZIUM_HOSTNAME
sleep $SLEEP_TIME;
curl -X POST -H "Content-Type: application/json" --data "$follow_created_json" $DEBEZIUM_HOSTNAME
sleep $SLEEP_TIME;