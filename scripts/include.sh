#!/bin/zsh
. ./logger.sh

SCRIPT_FULL_PATH="$1"
SCRIPT_NAME=$SCRIPT_FULL_PATH:t

cmd() {
  echo -e "${T_BOLD}${T_ITALIC}${FGGREEN}> $1${T_NORMAL}" >&2
  eval "$1"
}

# $1 - Property Key
getProperty() {
  PROP_VALUE=$(getPropertyFromFile "$1" "$MY_PROPERTIES_FILE")
  if [[ ! $PROP_VALUE ]]; then
    PROP_VALUE=$(getPropertyFromFile "$1" "$SERVICE_PROPERTIES_FILE")
  fi
  if [[ $PROP_VALUE ]]; then
    logInfo "Your current property value for $1=$PROP_VALUE"
    #    logInfo "If you want to change it run: setMyProperty $1 {new_value}"
  else
    logWarn "Set Your Local Property using: setMyProperties $1 {value}"
  fi
  echo "$PROP_VALUE"
}

getPropertyFromFile() {
  PROP_KEY=$1
  PROP_FILE=$2
  PROP_VALUE=$(eval "cat $PROP_FILE | grep \"$PROP_KEY=\" | cut -d'=' -f2")
  echo "$PROP_VALUE"
}

updateMyProperties() {
  updateProperties "$MY_PROPERTIES_FILE" "$1" "$2"
}

updateProperties() {
  PROP_FILE="$1"
  PROP_KEY="$2"
  NEW_PROP_VALUE="$PROP_KEY=$3"
  CUR_PROP_VALUE=$(getPropertyFromFile "$PROP_KEY" "$PROP_FILE")

  if [[ ! $CUR_PROP_VALUE ]]; then
    cmd "echo \"\" >> $PROP_FILE"
    cmd "echo $NEW_PROP_VALUE >> $PROP_FILE"
  else
    replaceText "$PROP_KEY=.*" "$NEW_PROP_VALUE" "$PROP_FILE"
  fi
}

# $1 matching regex
# $2 replacement string
# $3 filepath
replaceText() {
  cmd "sed -i '' 's|$1|$2|g' $3"
}

quit() {
  eval "exit $1"
}

SERVICE_PROPERTIES_FILE="./default.properties"
MY_PROPERTIES_FILE="./my.properties"
#Create local file if not exists.
touch "$MY_PROPERTIES_FILE"