#!/bin/zsh

SCRIPT_FULL_PATH="$1"
SCRIPT_NAME=$SCRIPT_FULL_PATH:t

#TERMINAL HINTS
T_NORMAL="\033[0m" # terminal to normal
T_BOLD="\033[1m"   # bold
T_ITALIC="\033[3m" # italic

FGRED="\033[31m"     # foreground to red
FGGREEN="\033[32m"   # foreground to green
FGYELLOW="\033[33m"  # background to yellow
FGMAGENTA="\033[35m" # foreground to magenta
FGCYAN="\033[36m"    # foreground to cyan
FGWHITE="\033[37m"   # foreground to white
FGBLUE="\u001b[34m" # foreground to blue

logIt() {
  echo "$1" >&2
}

logInfo() {
  echo -e "${T_BOLD}${FGCYAN}$1${T_NORMAL}" >&2
}
logError() {
  echo -e "${T_BOLD}${FGRED}$1${T_NORMAL}" >&2
}
logWarn() {
  echo -e "${T_BOLD}${FGYELLOW}$1${T_NORMAL}" >&2
}
logTodo() {
  echo -e "${T_BOLD}${FGMAGENTA}$1${T_NORMAL}" >&2
}
logTip() {
  echo -e "${T_BOLD}${FGBLUE}$1${T_NORMAL}" >&2
}

cmd() {
  echo -e "${T_BOLD}${T_ITALIC}${FGGREEN}> $1${T_NORMAL}" >&2
  eval "$1"
}

quit() {
  eval "exit $1"
}

# $1 matching regex
# $2 replacement string
# $3 filepath
replaceText() {
  cmd "sed -i '' 's|$1|$2|g' $3"
}

getPropertyFromFile() {
  PROP_KEY=$1
  PROP_FILE=$2
  PROP_VALUE=$(eval "cat $PROP_FILE | grep \"$PROP_KEY=\" | cut -d'=' -f2")
  echo "$PROP_VALUE"
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