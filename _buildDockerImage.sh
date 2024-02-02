#!/bin/zsh
. ./scripts/include.sh "$0"
#========Do not change above===========

if [[ ! $1 ]] ; then
  logError "USAGE: $SCRIPT_NAME [app-name]"
  quit 1
fi

#Let's build and release to local.
. ./_localRelease.sh

APPLICATION_NAME=$1

VERSION="${releaseVersion}"
JAR_FILE="app-$APPLICATION_NAME/build/libs/app-$APPLICATION_NAME-$VERSION-all.jar"
DOCKER_IMAGE_TAG="local-$APPLICATION_NAME-docker-image"

if [ ! -f "$JAR_FILE" ]; then
  logError "$JAR_FILE does not exists, run below command"
  logTodo "./_localRelease.sh"
  quit 1
fi

CMD="docker build ."
#CMD="$CMD --no-cache"
CMD="$CMD --build-arg applicationName=$APPLICATION_NAME"
CMD="$CMD --build-arg releaseVersion=$VERSION"
CMD="$CMD --build-arg jarFilePath=$JAR_FILE"
CMD="$CMD --tag $DOCKER_IMAGE_TAG"
CMD="$CMD -f Docker/Dockerfile "

cmd "$CMD"

logInfo "Run below command to run the locally build docker image"
logTodo " docker run -p 8080:8080 $DOCKER_IMAGE_TAG"

#========Do not change below===========
quit 0