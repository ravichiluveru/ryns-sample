#!/usr/bin/env bash
set -x

source ${WORK_DIR}/bin/include.sh

JAVA_PROPS=""

if [[ $VAR == *"dev"* ]]; then
  #Debug mode for JVM - should be enabled only for dev environments.
  JAVA_PROPS="${JAVA_PROPS} -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
fi

JAVA_PROPS="${JAVA_PROPS} -Xmx${APP_MAX_MEMORY} -Xms${APP_MIN_MEMORY}"
JAVA_PROPS="${JAVA_PROPS} -D${APPLICATION_NAME}.config.folder=${WORK_DIR}/conf"
JAVA_PROPS="${JAVA_PROPS} -Dio.ryns.platform.init.app.version=$RELEASE_VERSION"

exec java ${JAVA_PROPS} -jar "${WORK_DIR}"/libs/app-${APPLICATION_NAME}-${RELEASE_VERSION}-all.jar