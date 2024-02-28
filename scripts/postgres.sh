#!/bin/zsh

. ./include.sh "$1"

usage(){
  logError "USAGE: $SCRIPT_NAME [cmd = install | start | connect | stop | remove]"
  quit 1
}

if [[ ! $1 ]]; then
  usage
fi

#Variables/Config
RYNS_POSTGRES_CONTAINER_NAME=$(getProperty "ryns.postgres.container.name")
RYNS_POSTGRES_IMAGE_NAME=$(getProperty "ryns.postgres.image.name")
RYNS_POSTGRES_PORT=$(getProperty "ryns.postgres.port")
RYNS_POSTGRES_LOCAL_PORT=$(getProperty "ryns.postgres.local.port")
RYNS_POSTGRES_PWD=$(getProperty "ryns.postgres.pwd")
RYNS_POSTGRES_USER=$(getProperty "ryns.postgres.user")
RYNS_POSTGRES_DB=$(getProperty "ryns.postgres.db")

install(){
  C="docker create "
  C="$C --name $RYNS_POSTGRES_CONTAINER_NAME"
  C="$C -p $RYNS_POSTGRES_PORT:$RYNS_POSTGRES_LOCAL_PORT"
  C="$C -e POSTGRES_PASSWORD=$RYNS_POSTGRES_PWD"
  C="$C -e POSTGRES_USER=$RYNS_POSTGRES_USER"
  C="$C -e POSTGRES_DB=$RYNS_POSTGRES_DB"
  C="$C $RYNS_POSTGRES_IMAGE_NAME" #Postgres image name.


  logTodo "***** Next Steps ******"
  logTodo "1. Start the server using \"$SCRIPT_NAME start\""
#  logTodo "2. Create dbs using \"$SCRIPT_NAME createDbs\""

  cmd "$C"
}

start() {
  cmd "docker start $RYNS_POSTGRES_CONTAINER_NAME"
}

stop() {
  cmd "docker stop $RYNS_POSTGRES_CONTAINER_NAME"
}

remove(){
  cmd "docker container rm $RYNS_POSTGRES_CONTAINER_NAME"
}

connect() {
  logTip "***** Useful commands ***** "
  logTip "\\\\c <dbname> or \\\\connect <dbname> - Changes the database"
  logTip "\\\\q - quit"
  logTip "\\\\l or \\\\list - List databases"
  logTip "\\\\dt - List tables"

  cmd "docker exec -it $RYNS_POSTGRES_CONTAINER_NAME psql -U $RYNS_POSTGRES_USER -d $RYNS_POSTGRES_DB"
}

case "$1" in
    install)
        install
        ;;
    start)
        start
        ;;
    stop)
        stop
        ;;
    connect)
        connect
        ;;
    remove)
        remove
        ;;
    reInstall)
        stop
        remove
        install
        start
        ;;
    *)
        usage
        ;;
esac